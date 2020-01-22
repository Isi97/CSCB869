package com.iispiridis.visitservice.repositories;

import com.iispiridis.visitservice.models.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VisitRepository extends JpaRepository<Visit, Integer>
{
    @Query(value = "SELECT * FROM visits WHERE did = ?1", nativeQuery = true)
    public Iterable<Visit> getPendingDoctorVisits(int did);

    @Query(value = "SELECT * FROM visits WHERE pid = ?1", nativeQuery = true)
    public Iterable<Visit> getPatientVists(int pid);

    @Query(value = "SELECT * FROM visits WHERE diagnosis = ?1", nativeQuery = true)
    public Iterable<Visit> getDiagnosedWith(String d);

    public Visit findByid(int id);
}
