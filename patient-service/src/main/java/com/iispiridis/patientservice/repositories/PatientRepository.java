package com.iispiridis.patientservice.repositories;

import com.iispiridis.patientservice.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    Optional<Patient> findByegn(String egn);
    Optional<Patient> findByuid(int uid);
}
