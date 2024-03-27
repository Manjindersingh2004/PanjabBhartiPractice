package com.example.panjabbharti.Services;

import android.app.Service;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.panjabbharti.Activities.JobsInfo;
import com.example.panjabbharti.Adapters.JobsInfoRecycler;
import com.example.panjabbharti.Classes.DataModal;
import com.example.panjabbharti.Classes.JobsRecyclerData;
import com.example.panjabbharti.Constants.Constant;
import com.example.panjabbharti.R;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;

import kotlinx.coroutines.Job;

public class FetchData extends Service {
    FirebaseFirestore db;
    LocalDate dob;
    String qualification,department;
    Boolean panjabiKnown;
    String pbiKnown;

    int age;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        JobsInfo.progressBar.setVisibility(View.VISIBLE);
        Log.d("MYTAG","Started");
        //Checking if internet is available
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(CONNECTIVITY_SERVICE);
        Network network = connectivityManager.getActiveNetwork();
        if (network!=null) {
            boolean isConnected = connectivityManager.getNetworkCapabilities(network).hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET);
            if (isConnected) {
                db = FirebaseFirestore.getInstance();
                qualification = intent.getStringExtra(Constant.SELECTED_QUALIFICATION_SERVICE);
                department = intent.getStringExtra(Constant.SELECTED_DEPARTMENT_SERVICE);
                Log.d("MYTAG", qualification);
                Log.d("MYTAG", department);

                Calendar c = Calendar.getInstance();
                int c_year = c.get(Calendar.YEAR);
                int c_month = c.get(Calendar.MONTH);
                int c_day = c.get(Calendar.DAY_OF_MONTH);
                dob = LocalDate.parse(intent.getStringExtra(Constant.SELECTED_DATE_OF_BIRTH_SERVICE));
                LocalDate today = LocalDate.of(c_year, c_month, c_day);
                double age_temp =  Duration.between(dob.atStartOfDay(),today.atStartOfDay()).toDays();
                Log.d("MYTAG", String.valueOf(age_temp));
                age=(int)Math.ceil( (age_temp/(double) 365));
                Log.d("MYTAG", String.valueOf(age));
                pbiKnown = intent.getStringExtra(Constant.SELECTED_PANJABI_VALUE_SERVICE);
                if (pbiKnown.equals("YES")) {
                    panjabiKnown = true;
                } else {
                    panjabiKnown = false;
                }

                db.collection(String.valueOf(department).trim()).get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        JobsInfo.dataset.clear();
                        //Iterating over all posts and matching attributes one by one
                        for (DocumentSnapshot dm : task.getResult()
                        ) {
                            if (dm != null && dm.exists()) {
                                Log.d("MYTAG", "EXISTS");
//                        Converting firestore data structure to local java class
                                DataModal dataObj = dm.toObject(DataModal.class);
                                if (age >= dataObj.getAgeMin() && age <= dataObj.getAgeMax()) {
                                    for (String s : dataObj.getQualification().values()) {
                                        if (s.equals(qualification.trim())) {
                                            //Not known but required
                                            Log.d("MYTAG", panjabiKnown + "  " + dataObj.isPunjabiRequired());
                                            if ((panjabiKnown == false) && dataObj.isPunjabiRequired()) {
                                                Toast.makeText(this, "Panjabi is Compulsary", Toast.LENGTH_SHORT).show();
                                            } else {
                                                Log.d("MYTAG", "Available Jobs are:\n" + dm.getId() + "\n" + dataObj.getStartDate().toDate() + "\n" + dataObj.getEndDate().toDate());
                                                //Updating Dataset
                                                JobsInfo.dataset.add(new JobsRecyclerData(dm.getId(), dataObj.getStartDate().toDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), dataObj.getEndDate().toDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),dataObj.getNotificationURL(),dataObj.getWebsiteURL()));
                                            }
                                        }
                                    }
                                }
                                //Updating Recycler View
                                JobsInfo.jobsInfo.setAdapter(new JobsInfoRecycler(JobsInfo.context, JobsInfo.dataset));
                                JobsInfo.progressBar.setVisibility(View.INVISIBLE);
                                JobsInfo.noData.setVisibility(View.INVISIBLE);
                                if (JobsInfo.dataset.isEmpty()) {
                                    JobsInfo.noData.setText(R.string.no_data);
                                    JobsInfo.noData.setVisibility(View.VISIBLE);
                                }
                            } else {
                                Log.d("MYTAG", "Null");
                            }
                        }
                    } else {
                        Log.d("MYTAG", task.getException().getMessage());
                    }
                });

            }
        }else{
            JobsInfo.progressBar.setVisibility(View.INVISIBLE);
            JobsInfo.noData.setText(R.string.no_connection);
            JobsInfo.noData.setVisibility(View.VISIBLE);

        }
        return Service.START_STICKY;
    }



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
