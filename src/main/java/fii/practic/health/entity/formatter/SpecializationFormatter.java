package fii.practic.health.entity.formatter;

import fii.practic.health.entity.model.Specialization;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class SpecializationFormatter implements Formatter<Specialization> {

    @Override
    public String print(Specialization specialization, Locale locale) {
        return specialization.name();
    }

    @Override
    public Specialization parse(String text, Locale locale) throws ParseException {
        return Specialization.valueOf(text);
    }
}