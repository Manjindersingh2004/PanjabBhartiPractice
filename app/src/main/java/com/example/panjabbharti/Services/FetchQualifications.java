package com.example.panjabbharti.Services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.panjabbharti.Activities.ApplyFilterActivity;
import com.example.panjabbharti.Activities.JobsInfo;
import com.example.panjabbharti.Adapters.JobsInfoRecycler;
import com.example.panjabbharti.Adapters.QualificationRecyclerViewAdapter;
import com.example.panjabbharti.Classes.DataModal;
import com.example.panjabbharti.Classes.JobsRecyclerData;
import com.example.panjabbharti.R;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.ZoneId;

public class FetchQualifications extends Service {
    FirebaseFirestore db;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        db=FirebaseFirestore.getInstance();
        String department=intent.getStringExtra(String.valueOf(R.string.deptName));
        db.collection(String.valueOf(department).trim()).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                ApplyFilterActivity.qualificationList.clear();
                //Iterating over all posts and matching attributes one by one
                for (DocumentSnapshot dm : task.getResult()
                ) {
                    if (dm != null && dm.exists()) {
                        DataModal dataObj = dm.toObject(DataModal.class);
                        assert dataObj != null;
                        ApplyFilterActivity.qualificationList.addAll(dataObj.getQualification().values());
                    }else {
                        Log.d("MYTAG", "Null");
                    }
                }

                ApplyFilterActivity.recyclerView.setAdapter(new QualificationRecyclerViewAdapter(ApplyFilterActivity.qualificationList,getApplicationContext()));
            }
            else {
                Log.d("MYTAG", task.getException().getMessage());
            }
        });
        return Service.START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
