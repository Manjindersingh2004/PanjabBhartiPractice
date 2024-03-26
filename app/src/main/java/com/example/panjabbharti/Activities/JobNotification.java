package com.example.panjabbharti.Activities;

import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.panjabbharti.R;

import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;

public class JobNotification extends AppCompatActivity {
    Intent receiver;
    TextView postTitle,startTime,endTime,daysLeft;
    String title,notifyUrl,webUrl;
    int days;
    LocalDate start,end;

    Button download,openWeb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_job_notification);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        postTitle=findViewById(R.id.postTitle);
        startTime=findViewById(R.id.startTime);
        endTime=findViewById(R.id.endTime);
        daysLeft=findViewById(R.id.daysLeft);
        download=findViewById(R.id.download);
        openWeb=findViewById(R.id.openWeb);

        //Getting values from intent
        receiver=getIntent();
        title=receiver.getStringExtra(String.valueOf(R.string.key1_notifyData));
        start=LocalDate.parse(receiver.getStringExtra(String.valueOf(R.string.key2_notifyData)));
        end=LocalDate.parse(receiver.getStringExtra(String.valueOf(R.string.key3_notifyData)));
        notifyUrl=receiver.getStringExtra(String.valueOf(R.string.key4_notifyData));
        webUrl=receiver.getStringExtra(String.valueOf(R.string.key5_notifyData));

        //Calculate no. of days left
//        days= Period.between(LocalDate.now(),end).getDays();
        days= Integer.parseInt(String.valueOf(Duration.between(LocalDate.now().atStartOfDay(),end.atStartOfDay()).toDays()));
        Log.d("MYTAG", String.valueOf(days));
        Log.d("MYTAG", String.valueOf(LocalDate.now()));
        Log.d("MYTAG", String.valueOf(end));
        if (days<0){
            days*=-1;
        }
        //Setting values
        postTitle.setText(title);
        startTime.setText("Start Date : "+start);
        endTime.setText("End Date : "+end);
        daysLeft.setText( days +" days left ");

        download.setOnClickListener(v -> {
          //Add Notification Download
            DownloadManager downloadManager = getSystemService(DownloadManager.class);
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(notifyUrl)).setTitle("Notification.pdf").setDescription("File is downloading").setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED).setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "Notification.pdf");

            downloadManager.enqueue(request);
        });
        openWeb.setOnClickListener(v -> {
          //Redirect to website
            startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(webUrl)));
        });
    }
}