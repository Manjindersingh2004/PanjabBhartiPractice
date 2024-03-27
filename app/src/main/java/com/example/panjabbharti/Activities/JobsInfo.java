package com.example.panjabbharti.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.panjabbharti.Adapters.JobsInfoRecycler;
import com.example.panjabbharti.Classes.JobsRecyclerData;
import com.example.panjabbharti.Constants.Constant;
import com.example.panjabbharti.R;
import com.example.panjabbharti.Services.FetchData;

import java.util.ArrayList;

public class JobsInfo extends AppCompatActivity {
   public static RecyclerView jobsInfo;
   public static ArrayList<JobsRecyclerData> dataset;
     JobsInfoRecycler adapter;
    Intent getIntentValues;
    public static Context context;
    Intent serviceIntent;
    public static TextView noData;

   public static ProgressBar progressBar;
   AppCompatButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_jobs_info);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        back=findViewById(R.id.backBtn_job);
        context=this;
        jobsInfo=findViewById(R.id.jobsInfo);
        progressBar=findViewById(R.id.progressBar);
        noData=findViewById(R.id.noData);
        dataset = new ArrayList<>();
        adapter=new JobsInfoRecycler(this,dataset);
        noData.setVisibility(View.INVISIBLE);

        //Getting intent values and passing further to service for comparison
        getIntentValues = getIntent();
        serviceIntent = new Intent(this, FetchData.class);
        serviceIntent.putExtra(Constant.SELECTED_DEPARTMENT_SERVICE,getIntentValues.getStringExtra(Constant.SELECTED_DEPARTMENT));
        serviceIntent.putExtra(Constant.SELECTED_QUALIFICATION_SERVICE,getIntentValues.getStringExtra(Constant.SELECTED_QUALIFICATION));
        serviceIntent.putExtra(Constant.SELECTED_PANJABI_VALUE_SERVICE,getIntentValues.getStringExtra(Constant.SELECTED_PANJABI_VALUE));
        serviceIntent.putExtra(Constant.SELECTED_DATE_OF_BIRTH_SERVICE,getIntentValues.getStringExtra(Constant.SELECTED_DATE_OF_BIRTH));
        startService(serviceIntent);
        //Setting recycler view adapter and layout
        jobsInfo.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        jobsInfo.setAdapter(adapter);


        back.setOnClickListener(v -> finish());
    }
}