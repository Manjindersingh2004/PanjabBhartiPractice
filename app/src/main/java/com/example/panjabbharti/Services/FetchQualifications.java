package com.example.panjabbharti.Services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import androidx.annotation.Nullable;

import com.example.panjabbharti.Activities.ApplyFilterActivity;
import com.example.panjabbharti.Adapters.QualificationRecyclerViewAdapter;
import com.example.panjabbharti.Classes.DataModal;
import com.example.panjabbharti.R;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class FetchQualifications extends Service {
    FirebaseFirestore db;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        db=FirebaseFirestore.getInstance();
        String department=intent.getStringExtra(String.valueOf(R.string.deptName));
        db.collection(String.valueOf(department).trim()).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Set<String> uniqueQualifications = new HashSet<>();
                ApplyFilterActivity.qualificationList.clear();
                //Iterating over all posts and matching attributes one by one
                for (DocumentSnapshot dm : task.getResult()
                ) {
                    if (dm != null && dm.exists()) {
                        DataModal dataObj = dm.toObject(DataModal.class);
                        assert dataObj != null;
                        uniqueQualifications.addAll(dataObj.getQualification().values());
                        ApplyFilterActivity.qualificationList=new ArrayList<>(uniqueQualifications);
                    }else {
                        Log.d("MY_TAG", "Null");
                    }
                }
                ApplyFilterActivity.recyclerView.setAdapter(new QualificationRecyclerViewAdapter(ApplyFilterActivity.qualificationList,getApplicationContext()));
            }
            else {
                Log.d("MY_TAG", Objects.requireNonNull(Objects.requireNonNull(task.getException()).getMessage()));
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
