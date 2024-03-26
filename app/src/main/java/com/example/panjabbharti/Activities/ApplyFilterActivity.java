package com.example.panjabbharti.Activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.panjabbharti.Adapters.QualificationRecyclerViewAdapter;
import com.example.panjabbharti.Constants.Constant;
import com.example.panjabbharti.R;
import com.example.panjabbharti.databinding.ActivityApplyFilterBinding;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ApplyFilterActivity extends AppCompatActivity {

     ActivityApplyFilterBinding binder;

    //independent Static reference of recycler view  Needed for updating data in service

    public static RecyclerView recyclerView;
    public static ArrayList<String> qualificationList=new ArrayList<>();
    public  QualificationRecyclerViewAdapter qualificationRecyclerViewAdapter;
    String[] monthNames = {
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
    };
    String selectedDob="";
    String selectedPanjabi="";
    public static String selectedQualification="";
    String selectedDept="";
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
        recyclerView=binder.QualificationRecyclerView;
        Intent i=getIntent();
        selectedDept=i.getStringExtra(Constant.SELECTED_DEPARTMENT);
        Toast.makeText(this,selectedDept,Toast.LENGTH_SHORT).show();

//        putDataInArrayList();
        binder.QualificationRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.HORIZONTAL));
        qualificationRecyclerViewAdapter=new QualificationRecyclerViewAdapter(qualificationList,getApplicationContext());
        binder.QualificationRecyclerView.setAdapter(qualificationRecyclerViewAdapter);

        clickListners();
    }

    private void clickListners() {

        binder.backBtn.setOnClickListener(v -> finish());

        binder.applyBtn.setOnClickListener(v -> {
            selectedQualification= QualificationRecyclerViewAdapter.selectedQualification;
            if(selectedQualification.isEmpty()){
                Toast.makeText(this, "Please Select Qualification", Toast.LENGTH_SHORT).show();
            }
            else if(selectedDob.isEmpty()){
                Toast.makeText(this, "Please Select Dob", Toast.LENGTH_SHORT).show();
            }
            else if(selectedPanjabi.isEmpty()){
                Toast.makeText(this, "Please Select Panjabi is qualified or not", Toast.LENGTH_SHORT).show();
            }
            else{
                Intent i= new Intent(ApplyFilterActivity.this,JobsInfo.class);
                i.putExtra(Constant.SELECTED_DEPARTMENT,selectedDept);
                i.putExtra(Constant.SELECTED_QUALIFICATION,selectedQualification);
                i.putExtra(Constant.SELECTED_PANJABI_VALUE,selectedPanjabi);
                i.putExtra(Constant.SELECTED_DATE_OF_BIRTH,selectedDob);
                startActivity(i);

                Toast.makeText(this, selectedQualification+" "+selectedDob+" "+selectedPanjabi, Toast.LENGTH_SHORT).show();
            }
        });

        binder.ageButton.setOnClickListener(v -> {
            if(!binder.ageButton.getText().toString().equals("SELECT DOB")){
                binder.ageButton.setTextColor(ContextCompat.getColor(this,R.color.black));
                binder.ageButton.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_bg_1));
                binder.ageButton.setText(R.string.select_dob);
                selectedDob ="";
            }
            else{

                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        this,
                        (view, year1, month1, dayOfMonth1) -> {
                            month1 += 1;
                            binder.ageButton.setText(MessageFormat.format("{0} {1} {2}", dayOfMonth1, monthNames[month1 - 1], year1));
                            binder.ageButton.setTextColor(ContextCompat.getColor(this,R.color.white));
                            binder.ageButton.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_bg_2));

                            String formattedDay = (dayOfMonth1 < 10) ? "0" + dayOfMonth1 : String.valueOf(dayOfMonth1);
                            String formattedMonth = (month1 < 10) ? "0" + month1 : String.valueOf(month1);

                            selectedDob= year1 + "-" + formattedMonth + "-" + formattedDay;
                        },
                        year, month, dayOfMonth);
                // Show the date picker dialog
                datePickerDialog.show();
            }
        });

        binder.PunjabiYesBtn.setOnClickListener(v -> {
            if (selectedPanjabi.equals("YES")) {
                setPunjabiSelectionSwap(0);
            }else{
                setPunjabiSelectionSwap(1);
            }
        });
        binder.PunjabiNoBtn.setOnClickListener(v -> {
            if (selectedPanjabi.equals("NO")) {
                setPunjabiSelectionSwap(0);
            }
            else {
                setPunjabiSelectionSwap(2);
            }
        });

    }
    void setPunjabiSelectionSwap(int n){
        if(n==0){
            binder.PunjabiYesBtn.setTextColor(ContextCompat.getColor(this,R.color.black));
            binder.PunjabiYesBtn.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_bg_1));
            binder.PunjabiNoBtn.setTextColor(ContextCompat.getColor(this,R.color.black));
            binder.PunjabiNoBtn.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_bg_1));
            selectedPanjabi="";
        }
        else if(n==1){
            binder.PunjabiYesBtn.setTextColor(ContextCompat.getColor(this,R.color.white));
            binder.PunjabiYesBtn.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_bg_2));
            binder.PunjabiNoBtn.setTextColor(ContextCompat.getColor(this,R.color.black));
            binder.PunjabiNoBtn.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_bg_1));
            selectedPanjabi="YES";
        }
        else{
            binder.PunjabiNoBtn.setTextColor(ContextCompat.getColor(this,R.color.white));
            binder.PunjabiNoBtn.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_bg_2));
            binder.PunjabiYesBtn.setTextColor(ContextCompat.getColor(this,R.color.black));
            binder.PunjabiYesBtn.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_bg_1));
            selectedPanjabi="NO";
        }
    }

}