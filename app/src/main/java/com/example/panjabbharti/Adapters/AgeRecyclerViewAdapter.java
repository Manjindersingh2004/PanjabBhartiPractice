package com.example.panjabbharti.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.panjabbharti.R;
import com.example.panjabbharti.StaticData.StaticSelectedData;

import java.util.ArrayList;

public class AgeRecyclerViewAdapter extends RecyclerView.Adapter<AgeRecyclerViewAdapter.ViewHolder> {

    AgeRecyclerViewAdapter(){

    }

    public AgeRecyclerViewAdapter(ArrayList<String> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    ArrayList<String> arrayList;
    Context context;

    @NonNull
    @Override
    public AgeRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.filter_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AgeRecyclerViewAdapter.ViewHolder holder, int position) {

        if(!StaticSelectedData.selectedAge.isEmpty() && position==arrayList.indexOf(StaticSelectedData.selectedAge.get(0))){
            holder.btn.setTextColor(context.getResources().getColor(R.color.white));
            holder.btn.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.btn_bg_2));
        }
        else{
            holder.btn.setTextColor(context.getResources().getColor(R.color.black));
            holder.btn.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.btn_bg_1));
        }

        holder.btn.setText(arrayList.get(position));
        holder.btn.setOnClickListener(v -> {
            onClickButton(holder.btn,holder.getAdapterPosition());
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        AppCompatButton btn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btn=itemView.findViewById(R.id.filter_item);

        }
    }
    void  onClickButton(AppCompatButton btn,int pos){
        String text=btn.getText().toString();

        if(StaticSelectedData.selectedAge.contains(text)){
            StaticSelectedData.selectedAge.remove(text);
        }else{
            if(!StaticSelectedData.selectedAge.isEmpty()){
                StaticSelectedData.selectedAge.clear();
            }
            StaticSelectedData.selectedAge.add(text);
        }
        notifyDataSetChanged();
    }
}