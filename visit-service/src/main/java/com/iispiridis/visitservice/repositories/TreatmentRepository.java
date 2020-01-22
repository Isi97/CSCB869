package com.iispiridis.visitservice.repositories;

import com.iispiridis.visitservice.models.Treatment;
import com.iispiridis.visitservice.models.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TreatmentRepository extends JpaRepository<Treatment, Integer>
{
    @Query(value = "SELECT * FROM treatments WHERE vid = ?1", nativeQuery = true)
    public Iterable<Treatment> getVisitTreatments(int vid);

    @Query(value = "SELECT * FROM treatments WHERE pid = ?1", nativeQuery = true)
    public Iterable<Treatment> getPatientTreatments(int pid);
}
