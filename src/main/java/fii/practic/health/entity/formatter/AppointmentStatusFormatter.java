package fii.practic.health.entity.formatter;

import fii.practic.health.entity.model.AppointmentStatus;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class AppointmentStatusFormatter implements Formatter<AppointmentStatus> {

    @Override
    public String print(AppointmentStatus appointmentStatus, Locale locale) {
        return appointmentStatus.name();
    }

    @Override
    public AppointmentStatus parse(String text, Locale locale) throws ParseException {
        return AppointmentStatus.valueOf(text);
    }
}