package com.iispiridis.visitservice.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "visits")
public class Visit
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int pid;
    private int did;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date visitDate;

    private boolean visitCompleted;

    private boolean sickLeave;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date sickLeaveEnd;

    private String diagnosis;

    public Visit()
    {}

    public Visit(int id, int pid, int did, Date visitDate, boolean visitCompleted, boolean sickLeave, Date sickLeaveEnd, String diagnosis) {
        this.id = id;
        this.pid = pid;
        this.did = did;
        this.visitDate = visitDate;
        this.visitCompleted = visitCompleted;
        this.sickLeave = sickLeave;
        this.sickLeaveEnd = sickLeaveEnd;
        this.diagnosis = diagnosis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public boolean isVisitCompleted() {
        return visitCompleted;
    }

    public void setVisitCompleted(boolean visitCompleted) {
        this.visitCompleted = visitCompleted;
    }

    public boolean isSickLeave() {
        return sickLeave;
    }

    public void setSickLeave(boolean sickLeave) {
        this.sickLeave = sickLeave;
    }

    public Date getSickLeaveEnd() {
        return sickLeaveEnd;
    }

    public void setSickLeaveEnd(Date sickLeaveEnd) {
        this.sickLeaveEnd = sickLeaveEnd;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
}
