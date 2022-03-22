package com.example.BarangayServices.models;

public class Log {
    private String adminRFID, residentRFID, adminName, residentName, event;
    private long timestamp;
    private String dateTime;

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getResidentName() {
        return residentName;
    }

    public void setResidentName(String residentName) {
        this.residentName = residentName;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getAdminRFID() {
        return adminRFID;
    }

    public void setAdminRFID(String adminRFID) {
        this.adminRFID = adminRFID;
    }

    public String getResidentRFID() {
        return residentRFID;
    }

    public void setResidentRFID(String residentRFID) {
        this.residentRFID = residentRFID;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
