package com.example.panjabbharti.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.panjabbharti.R;

import java.util.List;

public class NewAdapter extends RecyclerView.Adapter<NewAdapter.ViewHolder> {
    List<String> department;
    List<Integer> images;
    LayoutInflater layoutInflater;
    public void setFilteredList(List<String> filterList){
        department=filterList;
        notifyDataSetChanged();
    }
    public NewAdapter(Context context, List<String> department, List<Integer> images){
        this.department=department;
        this.images=images;
        this.layoutInflater=LayoutInflater.from(context);
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
    }

    @Override
    public int getItemCount() {
        return department.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView department;
        ImageView images;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            department=itemView.findViewById(R.id.department);
            images=itemView.findViewById(R.id.departmentimg);

        }
    }
}
