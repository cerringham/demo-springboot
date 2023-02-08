package it.proactivity.demospringboot.utility;

import it.proactivity.demospringboot.model.HumanResource;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.List;

public class HumanResourceValidator {
//tutti i campi valorizzati
//non ci deve essere più di un CEO sul database
//non ci possono essere più di 4 persone nel CDA
//validazione della email
//validazione del codice fiscale (cercare sul web una funzione che fa questa cosa)
//validazione del numero di telefono (il + deve diventare 00 esempio +39345678 diventa 0039345678)
    public Boolean validateName(String name) {
        if (name == null || name.isEmpty())
            return false;
        return StringUtils.isAlpha(name);
    }

    public Boolean validateSurname(String surname) {
        if (surname == null || surname.isEmpty())
            return false;
        return StringUtils.isAlpha(surname);
    }

    public Boolean validateEmail(String email) {
        if (email == null || email.isEmpty())
            return false;
        EmailValidator emailValidator = EmailValidator.getInstance();
        return emailValidator.isValid(email);
    }

    public Boolean validateVatCode(String vatCode) {
        if (vatCode == null || vatCode.isEmpty())
            return false;
        if (vatCode.length() == 16)
            return true;
        if (StringUtils.isAlpha(vatCode.substring(0,5)))
            return true;
        if (StringUtils.isNumeric(vatCode.substring(12, 14)))
            return true;
        if (StringUtils.isAlpha(vatCode.substring(15)))
            return true;
        return false;
    }

    public Boolean validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty())
            return false;
        if (phoneNumber.startsWith("+"))
            phoneNumber.replace("+", "00");

        return StringUtils.isNumeric(phoneNumber);
    }

    public Boolean validateCeo(Boolean isCeo) {
        if (isCeo == null) {
            return false;
        }

    }
}
