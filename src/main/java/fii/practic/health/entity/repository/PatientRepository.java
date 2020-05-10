package fii.practic.health.entity.repository;

import fii.practic.health.entity.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Long> {

    @Query("SELECT p FROM Patient p INNER JOIN p.user u WHERE u.email = :email")
    Patient findPatientByEmail(@Param(value = "email") String email);
}
