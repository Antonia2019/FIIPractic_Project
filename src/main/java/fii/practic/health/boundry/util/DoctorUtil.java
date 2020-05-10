package fii.practic.health.boundry.util;

import fii.practic.health.entity.model.Appointment;
import fii.practic.health.entity.model.AppointmentStatus;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;

public class DoctorUtil {

    private DoctorUtil() {
        // prevent instantiate
    }

    public static String getEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }


    public static String hashPassword(String rawPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return ((BCryptPasswordEncoder) passwordEncoder).encode(rawPassword);
    }

    public static Map<Date, List<Appointment>> mapAppointmentFromList(List<Appointment> appointmentList,
                                                                      boolean isPast) {

        if (appointmentList.isEmpty()) {
            return Collections.emptyMap();
        }

        Map<Date, List<Appointment>> appointmentMap = new HashMap<>();

        Date now = getCurrentDate();

        for (Appointment appointment : appointmentList) {
            List<Appointment> appointments = null;
            if (appointmentMap.containsKey(appointment.getDate())) {
                appointments = appointmentMap.get(appointment.getDate());
            } else {
                appointments = new ArrayList<>();
            }

            if (!AppointmentStatus.CANCELED.equals(appointment.getStatus())
                    && (now.after(appointment.getDate()) == isPast)) {
                appointments.add(appointment);
                appointmentMap.put(appointment.getDate(), appointments);
            }
        }

        return appointmentMap;
    }

    public static Date getCurrentDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
}
