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

public class PanjabiRecyclerViewAdapter extends RecyclerView.Adapter<PanjabiRecyclerViewAdapter.ViewHolder> {

    PanjabiRecyclerViewAdapter(){

    }

    public PanjabiRecyclerViewAdapter(ArrayList<String> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    ArrayList<String> arrayList;
    Context context;

    @NonNull
    @Override
    public PanjabiRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.filter_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PanjabiRecyclerViewAdapter.ViewHolder holder, int position) {

        if(!StaticSelectedData.selectedPanjabi.isEmpty() && position==arrayList.indexOf(StaticSelectedData.selectedPanjabi.get(0))){
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

        if(StaticSelectedData.selectedPanjabi.contains(text)){
            StaticSelectedData.selectedPanjabi.remove(text);
        }else{
            if(!StaticSelectedData.selectedPanjabi.isEmpty()){
                StaticSelectedData.selectedPanjabi.clear();
            }
            StaticSelectedData.selectedPanjabi.add(text);
        }
        notifyDataSetChanged();
    }
}