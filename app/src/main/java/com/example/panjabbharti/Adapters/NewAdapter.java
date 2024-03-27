package com.example.panjabbharti.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.panjabbharti.Activities.ApplyFilterActivity;
import com.example.panjabbharti.Constants.Constant;
import com.example.panjabbharti.R;
import com.example.panjabbharti.Services.FetchQualifications;

import java.util.List;

public class NewAdapter extends RecyclerView.Adapter<NewAdapter.ViewHolder> {
    List<String> department;
    List<Integer> images;
    LayoutInflater layoutInflater;
    Context context;
    @SuppressLint("NotifyDataSetChanged")
    public void setFilteredList(List<String> filterList){
        department=filterList;
        notifyDataSetChanged();
    }
    public NewAdapter(Context context, List<String> department, List<Integer> images){
        this.department=department;
        this.images=images;
        this.layoutInflater=LayoutInflater.from(context);
        this.context=context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.card_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.department.setText(department.get(position));
        holder.images.setImageResource(images.get(position));
        holder.new_card.setOnClickListener(v -> {
            Intent intent =  new Intent(context, ApplyFilterActivity.class);
            intent.putExtra(Constant.SELECTED_DEPARTMENT,department.get(holder.getAbsoluteAdapterPosition()));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            //Starting service to fetch qualifications according to department
            Intent intent1 = new Intent(context, FetchQualifications.class);
            intent1.putExtra(String.valueOf(R.string.deptName),department.get(position));
            context.startService(intent1);
        });
    }

    @Override
    public int getItemCount() {
        return department.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView department;
        ImageView images;
        CardView new_card;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            department=itemView.findViewById(R.id.department);
            images=itemView.findViewById(R.id.departmentimg);
            new_card=itemView.findViewById(R.id.department_item_layout);

        }
    }
}
