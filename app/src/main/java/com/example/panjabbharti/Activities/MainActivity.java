package com.example.panjabbharti.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
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
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        datalist=findViewById(R.id.recylerview);
        searchView=findViewById(R.id.editText);
        textView=findViewById(R.id.newtext);
        department=new ArrayList<>();
        newimage=new ArrayList<>();
        department.add("Punjab Subordinate Service Selection Board");
        department.add("Education");
        department.add("Punjab Police");
        department.add("Punjab State Power Corporation Limited");
        department.add("Baba Farid University");
        newimage.add(R.drawable.revenue);
        newimage.add(R.drawable.education);
        newimage.add(R.drawable.police);
        newimage.add(R.drawable.electricity);
        newimage.add(R.drawable.school);
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
            newAdapter.setFilteredList(filteredList);
        if(filteredList.isEmpty()){
            textView.setVisibility(View.VISIBLE);
        }else{
            textView.setVisibility(View.GONE);
        }
    }
}