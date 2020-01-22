package com.iispiridis.doctorservice.models;


import javax.persistence.*;

@Entity
@Table(name="doctors")
public class Doctor
{
    @Id
    private int uid;

    private String name;
    private String type;

    public Doctor()
    {

    }

    public Doctor(int uid, String name, String type) {
        this.uid = uid;
        this.name = name;
        this.type = type;
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

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
