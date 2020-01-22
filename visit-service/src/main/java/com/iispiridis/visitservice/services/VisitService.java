package com.iispiridis.visitservice.services;

import com.iispiridis.visitservice.models.Visit;
import com.iispiridis.visitservice.repositories.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class VisitService
{
    @Autowired
    VisitRepository visitRepository;

    public void save(Visit visit)
    {
        visitRepository.save(visit);
    }

    public Iterable<Visit> getDoctorPendingVisits(int uid)
    {
        Iterable<Visit> visits = visitRepository.getPendingDoctorVisits(uid);
        return visits;
    }

    public Visit getVisit(int vid)
    {
        return visitRepository.findByid(vid);
    }

    public Iterable<Visit> getPatientVisits(int pid)
    {
        Iterable<Visit> visits = visitRepository.getPatientVists(pid);
        return visits;
    }

    public Iterable<Visit> getDiagnosedWith(String d)
    {
        Iterable<Visit> visits = visitRepository.getDiagnosedWith(d);
        return visits;
    }

    public int getDiagnosedWithCount(String d)
    {
        return ((Collection<?>)getDiagnosedWith(d)).size();
    }

}
