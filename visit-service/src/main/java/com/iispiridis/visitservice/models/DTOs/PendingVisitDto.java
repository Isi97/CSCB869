package com.iispiridis.visitservice.models.DTOs;

import com.iispiridis.visitservice.models.Visit;

public class PendingVisitDto
{
    Visit visit;
    String pname;
    boolean diagnosed;
    String dname;

    public PendingVisitDto(){}

    public PendingVisitDto(Visit visit, String pname) {
        this.visit = visit;
        this.pname = pname;
    }

    public Visit getVisit() {
        return visit;
    }

    public void setVisit(Visit visit) {
        this.visit = visit;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public boolean isDiagnosed() {
        return diagnosed;
    }

    public void setDiagnosed(boolean diagnosed) {
        this.diagnosed = diagnosed;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }
}
