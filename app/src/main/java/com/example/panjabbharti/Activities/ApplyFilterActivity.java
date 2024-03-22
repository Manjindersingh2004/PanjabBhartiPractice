package com.example.panjabbharti.Activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.panjabbharti.Adapters.QualificationRecyclerViewAdapter;
import com.example.panjabbharti.R;
import com.example.panjabbharti.StaticData.StaticSelectedData;
import com.example.panjabbharti.databinding.ActivityApplyFilterBinding;

import java.util.ArrayList;

public class ApplyFilterActivity extends AppCompatActivity {

    ActivityApplyFilterBinding binder;
    ArrayList<String> qualificationList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binder=ActivityApplyFilterBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binder.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        StaticSelectedData.resetData();

        putDataInArrayList();
        binder.QualificationRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, LinearLayoutManager.HORIZONTAL));

        QualificationRecyclerViewAdapter qualificationRecyclerViewAdapter=new QualificationRecyclerViewAdapter(qualificationList,getApplicationContext());
        binder.QualificationRecyclerView.setAdapter(qualificationRecyclerViewAdapter);

        clickListners();
    }

    private void clickListners() {

        binder.backBtn.setOnClickListener(v -> { finish();});
        binder.applyBtn.setOnClickListener(v -> {
            if(StaticSelectedData.selectedQualification.isEmpty()){
                Toast.makeText(this, "Please Select Qualification", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, StaticSelectedData.selectedQualification.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    void putDataInArrayList(){
        qualificationList.add("10th");
        qualificationList.add("High School Diploma");
        qualificationList.add("Associate's Degree");
        qualificationList.add("Bachelor's Degree");
        qualificationList.add("Master's Degree");
        qualificationList.add("Ph.D.");
        qualificationList.add("GED (General Educational Development)");
        qualificationList.add("Vocational Certificate");
        qualificationList.add("Professional Certification");
        qualificationList.add("Postgraduate Diploma");
        qualificationList.add("Technical Training Certification");
    }
}