package it.proactivity.demospringboot.utility;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

public class CustomerValidator {

    public static Boolean validateName(String name) {
        if (StringUtils.isEmpty(name)) {
            return false;
        }

        if (StringUtils.isAlphanumericSpace(name.replace(".", ""))) {
            return true;
        }
        return false;
    }

    public static Boolean validateEmail(String email) {
        if (StringUtils.isEmpty(email)) {
            return false;
        }
        EmailValidator emailValidator = EmailValidator.getInstance();
        return emailValidator.isValid(email);
    }

    public static Boolean validatePhoneNumber(String phoneNumber) {
        if (StringUtils.isEmpty(phoneNumber)) {
            return false;
        }
        if (StringUtils.startsWith(phoneNumber, "+")) {
            phoneNumber = phoneNumber.replace("+", "00");
        }
        if (StringUtils.isNumeric(phoneNumber)) {
            return true;
        }
        return false;
    }

    public static void validateAllParameters(String name, String email, String phoneNumber) {
        if (!validateName(name)) {
            throw new IllegalArgumentException("Name incorrect : " + name);
        }
        if (!validateEmail(email)) {
            throw new IllegalArgumentException("Email incorrect : " + email);
        }
        if (!validatePhoneNumber(phoneNumber)) {
            throw new IllegalArgumentException("PhoneNumber incorrect : " + phoneNumber);
        }
    }
}
