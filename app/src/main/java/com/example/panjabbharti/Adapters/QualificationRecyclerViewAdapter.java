package com.example.panjabbharti.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.panjabbharti.R;

import java.util.ArrayList;

public class QualificationRecyclerViewAdapter extends RecyclerView.Adapter<QualificationRecyclerViewAdapter.ViewHolder> {


    public QualificationRecyclerViewAdapter(ArrayList<String> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    ArrayList<String> arrayList;
    Context context;

    //Important to keep it static to maintain its reference after updation of QualificationList through service
    public static String selectedQualification="";

    @NonNull
    @Override
    public QualificationRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.filter_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QualificationRecyclerViewAdapter.ViewHolder holder, int position) {

        if(!selectedQualification.isEmpty() && position==arrayList.indexOf(selectedQualification)){
            holder.btn.setTextColor(ContextCompat.getColor(context,R.color.white));
            holder.btn.setBackgroundDrawable(ContextCompat.getDrawable(context,R.drawable.btn_bg_2));
        }
        else{
            holder.btn.setTextColor(ContextCompat.getColor(context,R.color.black));
            holder.btn.setBackgroundDrawable(ContextCompat.getDrawable(context,R.drawable.btn_bg_1));
        }

        holder.btn.setText(arrayList.get(position));
        holder.btn.setOnClickListener(v -> onClickButton(holder.btn));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        AppCompatButton btn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btn=itemView.findViewById(R.id.filter_item);
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    void  onClickButton(AppCompatButton btn){
        String text=btn.getText().toString();

        if(selectedQualification.contains(text)){
            selectedQualification="";
        }else{
            selectedQualification=text;
        }
        notifyDataSetChanged();
    }
}
