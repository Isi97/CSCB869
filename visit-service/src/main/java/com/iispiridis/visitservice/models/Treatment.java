package com.iispiridis.visitservice.models;

import javax.persistence.*;

@Entity
@Table(name="treatments")
public class Treatment
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    String description;

    int vid;
    int pid;
    int did;

    public Treatment()
    {

    }

    public Treatment(int id, String description, int vid, int pid, int did) {
        this.id = id;
        this.description = description;
        this.vid = vid;
        this.pid = pid;
        this.did = did;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getVid() {
        return vid;
    }

    public void setVid(int vid) {
        this.vid = vid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }
}
