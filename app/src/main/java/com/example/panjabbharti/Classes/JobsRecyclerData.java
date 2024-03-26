package com.example.panjabbharti.Classes;

import java.time.LocalDate;

public class JobsRecyclerData {
    String post;
    LocalDate startDate,endDate;

    String notifyUrl,webUrl;

//    public JobsRecyclerData(String post, LocalDate startDate, LocalDate endDate) {
//        this.post = post;
//        this.startDate = startDate;
//        this.endDate = endDate;
//    }

    public JobsRecyclerData(String post, LocalDate startDate, LocalDate endDate, String notifyUrl, String webUrl) {
        this.post = post;
        this.startDate = startDate;
        this.endDate = endDate;
        this.notifyUrl = notifyUrl;
        this.webUrl = webUrl;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }
}
