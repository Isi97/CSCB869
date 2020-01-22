package com.iispiridis.visitservice.models;


public class Patient
{
    String name;
    String egn;
    int did;
    int uid;
    boolean hasPayedInsurance;
    boolean completed;

    public Patient() {

    }

    public Patient(String name, String egn, int did, int uid, boolean hasPayedInsurance, boolean completed) {
        this.name = name;
        this.egn = egn;
        this.did = did;
        this.uid = uid;
        this.hasPayedInsurance = hasPayedInsurance;
        this.completed = completed;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEgn() {
        return egn;
    }

    public void setEgn(String egn) {
        this.egn = egn;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public boolean isHasPayedInsurance() {
        return hasPayedInsurance;
    }

    public void setHasPayedInsurance(boolean hasPayedInsurance) {
        this.hasPayedInsurance = hasPayedInsurance;
    }
}
