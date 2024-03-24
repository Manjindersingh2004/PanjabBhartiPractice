package com.example.panjabbharti.Adapters;

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
import com.example.panjabbharti.Activities.MainActivity;
import com.example.panjabbharti.Constants.Constant;
import com.example.panjabbharti.R;

import java.util.List;

public class NewAdapter extends RecyclerView.Adapter<NewAdapter.ViewHolder> {
    List<String> department;
    List<Integer> images;
    LayoutInflater layoutInflater;
    Context context;
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
        holder.new_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(context, ApplyFilterActivity.class);
                intent.putExtra(Constant.SELECTED_DEPARTMENT,department.get(holder.getAdapterPosition()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return department.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
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
