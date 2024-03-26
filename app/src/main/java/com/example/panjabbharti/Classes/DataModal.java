package com.example.panjabbharti.Classes;
import com.google.firebase.Timestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

public class DataModal {



        String NotificationURL,WebsiteURL;
        HashMap<String, String> Qualification;
        int ageMin, ageMax;

        public boolean isPunjabiRequired() {
            return PunjabiRequired;
        }

        public void setPunjabiRequired(boolean punjabiRequired) {
            PunjabiRequired = punjabiRequired;
        }

        boolean PunjabiRequired;
        Timestamp StartDate, EndDate;

        public HashMap<String, String> getQualification() {
            return Qualification;
        }

        public void setQualification(HashMap<String, String> qualification) {
            Qualification = qualification;
        }


        public int getAgeMin() {
            return ageMin;
        }

        public void setAgeMin(int ageMin) {
            this.ageMin = ageMin;
        }

        public int getAgeMax() {
            return ageMax;
        }

        public void setAgeMax(int ageMax) {
            this.ageMax = ageMax;
        }



        public Timestamp getStartDate() {
            return StartDate;
        }

        public void setStartDate(Timestamp startDate) {
            StartDate = startDate;
        }

        public Timestamp getEndDate() {
            return EndDate;
        }

        public void setEndDate(Timestamp endDate) {
            EndDate = endDate;
        }

    public String getNotificationURL() {
        return NotificationURL;
    }

    public void setNotificationURL(String notificationURL) {
        NotificationURL = notificationURL;
    }

    public String getWebsiteURL() {
        return WebsiteURL;
    }

    public void setWebsiteURL(String websiteURL) {
        WebsiteURL = websiteURL;
    }
}

