package com.example.panjabbharti.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.panjabbharti.Activities.JobNotification;
import com.example.panjabbharti.Activities.JobsInfo;
import com.example.panjabbharti.Classes.JobsRecyclerData;
import com.example.panjabbharti.R;

import java.util.ArrayList;

public class JobsInfoRecycler extends RecyclerView.Adapter<JobsInfoRecycler.ViewHolder> {
    Context context;
    ArrayList<JobsRecyclerData> dataset;

    public JobsInfoRecycler(Context context, ArrayList<JobsRecyclerData> dataset) {
        this.context = context;
        this.dataset = dataset;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.info_card,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.getJobName().setText(dataset.get(position).getPost());
            holder.getStartDate().setText("Start Date : "+String.valueOf(dataset.get(position).getStartDate()));
            holder.getEndDate().setText("End Date : "+String.valueOf(dataset.get(position).getEndDate()));
            holder.itemView.setOnClickListener(v -> {
                Intent i = new Intent(context, JobNotification.class);
                i.putExtra(String.valueOf(R.string.key1_notifyData),dataset.get(position).getPost());
                i.putExtra(String.valueOf(R.string.key2_notifyData),String.valueOf(dataset.get(position).getStartDate()));
                i.putExtra(String.valueOf(R.string.key3_notifyData),String.valueOf(dataset.get(position).getEndDate()));
                i.putExtra(String.valueOf(R.string.key4_notifyData),String.valueOf(dataset.get(position).getNotifyUrl()));
                i.putExtra(String.valueOf(R.string.key5_notifyData),String.valueOf(dataset.get(position).getWebUrl()));

                context.startActivity(i);
            });
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView jobName=itemView.findViewById(R.id.jobName);
        TextView startDate=itemView.findViewById(R.id.startDate);
        TextView endDate=itemView.findViewById(R.id.endDate);

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public TextView getJobName() {
            return jobName;
        }

        public void setJobName(TextView jobName) {
            this.jobName = jobName;
        }

        public TextView getStartDate() {
            return startDate;
        }

        public void setStartDate(TextView startDate) {
            this.startDate = startDate;
        }

        public TextView getEndDate() {
            return endDate;
        }

        public void setEndDate(TextView endDate) {
            this.endDate = endDate;
        }
    }
}
