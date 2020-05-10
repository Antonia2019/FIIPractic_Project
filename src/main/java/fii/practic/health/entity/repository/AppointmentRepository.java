package fii.practic.health.entity.repository;

import fii.practic.health.entity.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, Long> {


    Appointment findOne(long appointmentId);
}
