package fii.practic.health.entity.repository;

import fii.practic.health.entity.model.Doctor;
import fii.practic.health.entity.model.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor, Long> {


    //Spring Data
    List<Doctor> findDoctorsByPatientsFirstName(String firstName);

    Doctor findOne(Long id);

    @Query("SELECT d FROM Doctor d WHERE d.specialization = :spec")
    List<Doctor> findDoctorBySpecialization(@Param(value = "spec") Specialization spec);

    @Query("SELECT d FROM Doctor d INNER JOIN d.user u WHERE u.email = :email")
    Doctor findDoctorByEmail(@Param(value = "email") String email);

    @Query("SELECT d FROM Doctor d INNER JOIN d.user u WHERE d.id = :doctorId")
    Doctor findDoctorById(@Param(value = "doctorId") long doctorId);
}

