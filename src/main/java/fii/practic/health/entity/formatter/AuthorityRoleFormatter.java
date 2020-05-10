package fii.practic.health.entity.formatter;

import fii.practic.health.entity.model.AuthorityRole;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class AuthorityRoleFormatter implements Formatter<AuthorityRole> {

    @Override
    public String print(AuthorityRole authorityRole, Locale locale) {
        return authorityRole.name();
    }

    @Override
    public AuthorityRole parse(String text, Locale locale) throws ParseException {
        return AuthorityRole.valueOf(text);
    }
}