package com.example.panjabbharti.Activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.panjabbharti.Adapters.NewAdapter;
import com.example.panjabbharti.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView datalist;
    androidx.appcompat.widget.SearchView searchView;
    List<String> department;
    List<Integer> newimage;
    NewAdapter newAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        datalist=findViewById(R.id.recylerview);
        searchView=findViewById(R.id.editText);
        department=new ArrayList<>();
        newimage=new ArrayList<>();
        department.add("AGRICULTURE");
        department.add("EDUCATION");
        department.add("HEALTH");
        department.add("POLICE");
        department.add("REVENUE");
        newimage.add(R.drawable.agriculture);
        newimage.add(R.drawable.learning);
        newimage.add(R.drawable.health);
        newimage.add(R.drawable.police);
        newimage.add(R.drawable.revenue);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterText(newText);
                return true;
            }
        });
        newAdapter =new NewAdapter(this,department,newimage);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        datalist.setLayoutManager(gridLayoutManager);
        datalist.setAdapter(newAdapter);
    }
    private void filterText(String newText) {
        List<String> filteredList = new ArrayList<>();
        for(String newdepartment : department){
            if(newdepartment.toLowerCase().contains(newText.toLowerCase())){
                filteredList.add(newdepartment);
            }
        }
        if(filteredList.isEmpty()){
            Toast.makeText(this,"No Data",Toast.LENGTH_SHORT).show();
        }else{
            newAdapter.setFilteredList(filteredList);
        }
    }
}