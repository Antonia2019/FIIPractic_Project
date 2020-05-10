package fii.practic.health.entity.formatter;

import fii.practic.health.entity.model.Gender;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class GenderFormatter implements Formatter<Gender> {

    @Override
    public String print(Gender gender, Locale locale) {
        return gender.name();
    }

    @Override
    public Gender parse(String text, Locale locale) throws ParseException {
        return Gender.valueOf(text);
    }
}

