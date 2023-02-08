package it.proactivity.demospringboot.utility;


import it.proactivity.demospringboot.dto.HumanResourceDto;
import it.proactivity.demospringboot.model.HumanResource;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.List;

public class HumanResourceValidator {
    HumanResourceUtility humanResourceUtility = new HumanResourceUtility();

    public Boolean validateEmail(String email) {
        if (StringUtils.isEmpty(email)) {
            throw new IllegalArgumentException("The email can't be null or empty");
        }
        EmailValidator emailValidator = EmailValidator.getInstance();
        return emailValidator.isValid(email);
    }

    public Boolean validateName(String name) {
        if (StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("Name can't be null or empty");
        }
        if (StringUtils.isAlpha(name)) {
            return true;
        } else {
            throw new IllegalArgumentException("The name must contains only characters");
        }
    }

    public Boolean validateSurname(String surname) {
        if (StringUtils.isEmpty(surname)) {
            throw new IllegalArgumentException("Surname can't be null or empty");
        }
        if (StringUtils.isAlpha(surname)) {
            return true;
        } else {
            throw new IllegalArgumentException("The surname must contains only characters");
        }
    }

    /*
    public Boolean validateVatCode(String vatCode) {
        if (StringUtils.isEmpty(vatCode)) {
            throw new IllegalArgumentException("VatCode can't be null or empty");
        }
        if (!StringUtils.isAlphanumeric(vatCode)) {
            throw new IllegalArgumentException("The vat code must be alphaNumeric");
        }
        if (vatCode.length() < 16) {
            throw new IllegalArgumentException("The vat code is to short");
        }
        if (vatCode.length() > 16) {
            throw new IllegalArgumentException("The vat code is to long");
        }

        boolean isValid = false; // Variabile booleana per la convalida

        for (int i = 0; i < vatCode.length(); i++) {
            char cifra = vatCode.charAt(i);

            if (Character.isLetter(cifra)) {
                // Se la cifra è una lettera, verifica se è nella posizione corretta
                if (i < 6 || i == 8 || i == 11 || i == 15) {
                    // Se la cifra non si trova nella posizione corretta, impostare la variabile 'valido' a false
                    isValid = true;

                } else {
                    isValid = false;
                    break;
                }
                // Verifica se la cifra corrente è un numero
            } else if (Character.isDigit(cifra)) {
                // Se la cifra è un numero, verifica se è nella posizione corretta
                if (i > 5 && i != 8 && i != 11 && i != 15) {
                    // Se la cifra non si trova nella posizione corretta, impostare la variabile 'valido' a false
                    isValid = true;

                } else {
                    isValid = false;
                    break;
                }
            }
        }
        if (isValid) {
            return true;
        } else {
            throw new IllegalArgumentException("Vat code not valid");
        }
    }

     */

    public Boolean validatePhoneNumber(String phoneNumber) {
        if (StringUtils.isEmpty(phoneNumber)) {
            throw new IllegalArgumentException("PhoneNumber can't be null or empty");
        }
        if (StringUtils.startsWith(phoneNumber, "+")) {
            phoneNumber = phoneNumber.replace("+", "00");
        }
        if (!StringUtils.isNumeric(phoneNumber)) {
            throw new IllegalArgumentException("The phone number must be numerical type");
        }
        return true;
    }


    public Boolean validateVatCode(String vatCode) {
        if (StringUtils.isEmpty(vatCode)) {
            throw new IllegalArgumentException("VatCode can't be null or empty");
        }
        if (!StringUtils.isAlphanumeric(vatCode)) {
            throw new IllegalArgumentException("The vat code must be alphaNumeric");
        }
        if (vatCode.length() != 16) {
            throw new IllegalArgumentException("The vat code is to short or to long");
        }

        Boolean isValid = true;
        if (!StringUtils.isAlpha(vatCode.substring(0,6))) {
            isValid = false;
        } else if (!StringUtils.isNumeric(vatCode.substring(6, 8))) {
            isValid = false;
        } else if (!StringUtils.isAlpha(vatCode.substring(8,9))) {
            isValid = false;
        } else if (!StringUtils.isNumeric(vatCode.substring(9, 11))) {
            isValid = false;
        } else if (!StringUtils.isAlpha(vatCode.substring(11,12))) {
            isValid = false;
        } else if (!StringUtils.isNumeric(vatCode.substring(12, 15))) {
            isValid = false;
        } else if (!StringUtils.isAlpha(String.valueOf(vatCode.charAt(15)))) {
            isValid = false;
        }
        if (!isValid) {
            throw new IllegalArgumentException("Vat code not valid");

        }
        return true;
    }


    /*
    public Boolean validateVatCode(String vatCode) {

        ApiClient defaultClient = Configuration.getDefaultApiClient();

        ApiKeyAuth Apikey = (ApiKeyAuth) defaultClient.getAuthentication("Apikey");
        Apikey.setApiKey("YOUR API KEY");

        VatApi apiInstance = new VatApi();

        VatLookupRequest input = new VatLookupRequest(); // VatLookupRequest | Input VAT code
        input.vatCode(vatCode);


        try {
            VatLookupResponse result = apiInstance.vatVatLookup(input);

             return result.isIsValid();


        } catch (ApiException e) {
            throw  new IllegalArgumentException("Vat code error");
        }
    }

     */

    public Boolean validateNumberOfCeoOrCdaForInsert(Boolean isCeo, Boolean isCda) {
        if (isCeo) {
            List<HumanResource> ceo = humanResourceUtility.getCeo();

            Long numberOfCeo = ceo.stream()
                    .count();
            if (numberOfCeo < 1) {
                return true;
            } else {
                throw new IllegalStateException("There is already a ceo");
            }
        }

        if (isCda) {
            List<HumanResource> cdaList = humanResourceUtility.getCda();

            Long numberOfCda = cdaList.stream()
                    .count();

            if (numberOfCda < 4) {
                return true;
            } else {
                throw new IllegalStateException("The number of Cda can't be more than 4");
            }
        }
        return true;
    }

    public Boolean validateCdaOrCeoForDelete(Long id) {
        HumanResource humanResource = humanResourceUtility.getHumanResourceFromId(id);
        if (humanResource == null) {
            throw new IllegalStateException("HumanResource not found");
        }
        if (humanResource.getIsCda()) {
            List<HumanResource> humanResourceList = humanResourceUtility.getCda();
            Long numberOfCda = humanResourceList.stream()
                    .count();
            if (numberOfCda > 1) {
                return true;
            } else {
                throw new IllegalArgumentException("The number of Cda can't be less than 1");
            }
        } else if (humanResource.getIsCeo()) {
            throw new IllegalArgumentException("The Ceo can't be deleted");
        }
        return true;
    }

    public Boolean validateAllHumanResourceDtoParametersForInsert(HumanResourceDto humanResourceDto) {

        return validateName(humanResourceDto.getName()) && validateSurname(humanResourceDto.getSurname()) &&
                validateEmail(humanResourceDto.getEmail()) && validateVatCode(humanResourceDto.getVatCode()) &&
                validateNumberOfCeoOrCdaForInsert(humanResourceDto.getIsCda(), humanResourceDto.getIsCeo()) &&
                validatePhoneNumber(humanResourceDto.getPhoneNumber());
    }
}