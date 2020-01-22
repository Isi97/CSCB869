package com.iispiridis.visitservice.models;

public class Doctor {
    private String name;
    private String type;
    private int uid;

    public Doctor()
    {

    }

    public Doctor(int id, String name, String type) {
        this.name = name;
        this.type = type;
        this.uid = uid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
