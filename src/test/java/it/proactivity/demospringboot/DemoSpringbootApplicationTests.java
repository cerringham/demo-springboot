package it.proactivity.demospringboot;

import it.proactivity.demospringboot.dto.CustomerInformationDto;
import it.proactivity.demospringboot.dto.HumanResourceDto;
import it.proactivity.demospringboot.dto.ProjectCustomerDto;
import it.proactivity.demospringboot.model.HumanResource;
import it.proactivity.demospringboot.service.HumanResourceService;
import it.proactivity.demospringboot.utility.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DemoSpringbootApplicationTests {

    @Autowired
    HumanResourceService humanResourceService;
    CustomerUtility customerUtility = new CustomerUtility();
    ProjectUtility projectUtility = new ProjectUtility();


    HumanResourceValidator humanResourceValidator = new HumanResourceValidator();
    HumanResourceUtility humanResourceUtility = new HumanResourceUtility();

    @Test
    void contextLoads() {
    }

    @Test
    public void validateCustomerNamePositiveTest() {
        assertTrue(CustomerValidator.validateName("Prova"));
        assertTrue(CustomerValidator.validateName("Azienda S.R.L."));
        assertTrue(CustomerValidator.validateName("prova 2"));
    }

    @Test
    public void validateCustomerNameNegativeTest() {
        assertFalse(CustomerValidator.validateName("@.. "));
        assertFalse(CustomerValidator.validateName("Prova ?"));
        assertFalse(CustomerValidator.validateName("Prova2 !! "));
    }

    @Test
    public void validateCustomerNameEmptyNegativeTest() {
        assertFalse(CustomerValidator.validateName(""));
    }

    @Test
    public void validateCustomerNameNullNegativeTest() {
        assertFalse(CustomerValidator.validateName(null));
    }

    @Test
    public void validateCustomerEmailPositiveTest() {
        assertTrue(CustomerValidator.validateEmail("prova@prova.it"));
        assertTrue(CustomerValidator.validateEmail("prova2@prova2.it"));
    }

    @Test
    public void validateCustomerEmailNegativeTest() {
        assertFalse(CustomerValidator.validateEmail("provaprova.it"));
        assertFalse(CustomerValidator.validateEmail("prova2 @prova2.it"));
        assertFalse(CustomerValidator.validateEmail("prova2@prova2it"));
    }

    @Test
    public void validateCustomerEmptyEmailNegativeTest() {
        assertFalse(CustomerValidator.validateEmail(""));
    }

    @Test
    public void validateCustomerNullEmailNegativeTest() {
        assertFalse(CustomerValidator.validateEmail(null));
    }

    @Test
    public void validateCustomerPhoneNumberPositiveTest() {
        assertTrue(CustomerValidator.validatePhoneNumber("+7364747883"));
        assertTrue(CustomerValidator.validatePhoneNumber("7364747883"));
    }

    @Test
    public void validateCustomerPhoneNumberNegativeTest() {
        assertFalse(CustomerValidator.validatePhoneNumber("+736474a7883"));
        assertFalse(CustomerValidator.validatePhoneNumber("+736474 7883"));
    }

    @Test
    public void validateCustomerEmptyPhoneNumberNegativeTest() {
        assertFalse(CustomerValidator.validatePhoneNumber(""));
    }

    @Test
    public void validateCustomerNullPhoneNumberNegativeTest() {
        assertFalse(CustomerValidator.validatePhoneNumber(null));
    }

    @Test
    public void validateProjectNamePositiveTest() {
        assertTrue(ProjectValidator.validateProjectName("project"));
        assertTrue(ProjectValidator.validateProjectName("project 2"));
        assertTrue(ProjectValidator.validateProjectName("Project"));
        assertTrue(ProjectValidator.validateProjectName("project2"));
    }

    @Test
    public void validateProjectNameNegativeTest() {
        assertFalse(ProjectValidator.validateProjectName("Prova !!"));
    }

    @Test
    public void validateProjectEmptyNameNegativeTest() {
        assertFalse(ProjectValidator.validateProjectName(""));
    }

    @Test
    public void validateProjectNullNameNegativeTest() {
        assertFalse(ProjectValidator.validateProjectName(null));
    }

    @Test
    public void validateProjectEndDatePositiveTest() {
        assertTrue(ProjectValidator.validateEndDate("2023-01-01"));
    }

    @Test
    public void validateProjectEndDateWrongFormatNegativeTest() {
        assertFalse(ProjectValidator.validateEndDate("01-01-2023"));
    }

    @Test
    public void validateProjectEndDateEmptyDateNegativeTest() {
        assertFalse(ProjectValidator.validateEndDate(""));
    }

    @Test
    public void validateProjectEndDateNullDateNegativeTest() {
        assertFalse(ProjectValidator.validateEndDate(null));
    }

    @Test
    public void validateReportingIdProjectPositiveTest() {
        assertTrue(ProjectValidator.validateReportingId("nbhvg837677784"));
        assertTrue(ProjectValidator.validateReportingId("dhhdhjjskkd"));
        assertTrue(ProjectValidator.validateReportingId("837484893"));
    }

    @Test
    public void validateReportingIdProjectNegativeTest() {
        assertFalse(ProjectValidator.validateReportingId("nbhvg 837677784"));
        assertFalse(ProjectValidator.validateReportingId("dhhdhjjskkd !!!"));
    }

    @Test
    public void validateReportingIdProjectEmptyValueNegativeTest() {
        assertFalse(ProjectValidator.validateReportingId(""));
    }

    @Test
    public void validateReportingIdProjectNullValueNegativeTest() {
        assertFalse(ProjectValidator.validateReportingId(null));
    }

    @Test
    public void insertCompliteProjectNewCustomerPositiveTest() {

        Integer numberOfCustomerBeforeInsert = customerUtility.getAllCustomer().size();
        Integer numberOfProjectBeforeInsert = projectUtility.getAllProject().size();

        ProjectCustomerDto dto = new ProjectCustomerDto("Milanello project", "2023-09-28",
                "61111a", "Milan A.C.", "milan@milan.com",
                "009423938452", "Milan");

        //insert new project with new customer
        projectUtility.insertCompliteProject(dto);

        Integer numberOfCustomerAfterInsert = customerUtility.getAllCustomer().size();
        Integer numberOfProjectAfterInsert = projectUtility.getAllProject().size();

        assertTrue(numberOfCustomerBeforeInsert < numberOfCustomerAfterInsert);
        assertTrue(numberOfProjectBeforeInsert < numberOfProjectAfterInsert);
    }

    @Test
    public void insertCompliteProjectOldCustomerPositiveTest() {

        Integer numberOfCustomerBeforeInsert = customerUtility.getAllCustomer().size();
        Integer numberOfProjectBeforeInsert = projectUtility.getAllProject().size();

        ProjectCustomerDto dto = new ProjectCustomerDto("Team Staff project", "2023-09-28",
                "61111a", "Milan A.C.", "milan@milan.com",
                "009423938452", "Milan");

        //insert new project with old customer
        projectUtility.insertCompliteProject(dto);

        Integer numberOfCustomerAfterInsert = customerUtility.getAllCustomer().size();
        Integer numberOfProjectAfterInsert = projectUtility.getAllProject().size();

        assertEquals(numberOfCustomerBeforeInsert, numberOfCustomerAfterInsert);
        assertTrue(numberOfProjectBeforeInsert < numberOfProjectAfterInsert);
    }

    @Test
    public void insertCompliteProjectNullDtoNegativeTest() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            projectUtility.insertCompliteProject(null);
        });

        String message = "The ProjectCustomerDto is null";
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void insertCompliteProjectNullEndDateNegativeTest() {

        ProjectCustomerDto dto = new ProjectCustomerDto("Nuovo progetto", null,
                "11112er", "Luftanza S.P.A", "luftanza@luftanza2.com",
                "+992387652", "Airways");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            projectUtility.insertCompliteProject(dto);
        });

        String message = "The parameters cannot be null";
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void insertCompliteProjectWrongFormatEndDateNegativeTest() {

        ProjectCustomerDto dto = new ProjectCustomerDto("Nuovo progetto", "23-12-2023",
                "11112er", "Luftanza S.P.A", "luftanza@luftanza2.com",
                "+992387652", "Airways");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            projectUtility.insertCompliteProject(dto);
        });

        String message = "Cannot parse the endDate " + dto.getEndDate();
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void insertCompliteProjectNullNameNegativeTest() {

        ProjectCustomerDto dto = new ProjectCustomerDto(null, "2023-01-19",
                "11112er", "Luftanza S.P.A", "luftanza@luftanza2.com",
                "+992387652", "Airways");

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            projectUtility.insertCompliteProject(dto);
        });

        String message = "Cannot create project";
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void insertCompliteProjectEmptyNameNegativeTest() {

        ProjectCustomerDto dto = new ProjectCustomerDto("", "2023-01-19",
                "11112er", "Luftanza S.P.A", "luftanza@luftanza2.com",
                "+992387652", "Airways");

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            projectUtility.insertCompliteProject(dto);
        });

        String message = "Cannot create project";
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void insertCompliteProjectEmptyReportingIdNegativeTest() {

        ProjectCustomerDto dto = new ProjectCustomerDto("Somethig", "2023-01-19",
                "", "Luftanza S.P.A", "luftanza@luftanza2.com",
                "+992387652", "Airways");

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            projectUtility.insertCompliteProject(dto);
        });

        String message = "Cannot create project";
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void insertCompliteProjectNullReportingIdNegativeTest() {

        ProjectCustomerDto dto = new ProjectCustomerDto("Somethig", "2023-01-19",
                null, "Luftanza S.P.A", "luftanza@luftanza2.com",
                "+992387652", "Airways");

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            projectUtility.insertCompliteProject(dto);
        });

        String message = "Cannot create project";
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void getCustomersInformations() {
        CustomerInformationDto[] projectList = projectUtility.getAllProjectForeachCustomer();
        for (CustomerInformationDto dto : projectList) {
            System.out.println(dto.getCustomerName() + "\n" + dto.getProjects());
        }
    }


    @Test
    public void validateVatCodePositiveTest() {
        assertTrue(humanResourceValidator.validateVatCode("fcssss99l90h090f"));
    }

    @Test
    public void validateVatCodeNegativeTest() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            humanResourceValidator.validateVatCode("fcs0IF89L71C000F");

        });
        String message = "Vat code not valid";
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void validateNullVatCodeNegativeTest() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            humanResourceValidator.validateVatCode(null);

        });
        String message = "VatCode can't be null or empty";
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void validateEmptyVatCodeNegativeTest() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            humanResourceValidator.validateVatCode("");

        });
        String message = "VatCode can't be null or empty";
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void validateShortVatCodeNegativeTest() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            humanResourceValidator.validateVatCode("fcss99l90h090f");

        });
        String message = "The vat code is to short";
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void validateLongVatCodeNegativeTest() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            humanResourceValidator.validateVatCode("fcss99l90h090ffff");

        });
        String message = "The vat code is to long";
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void validateSpecialCharVatCodeNegativeTest() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            humanResourceValidator.validateVatCode("fcssss99l90h090f@@");

        });
        String message = "The vat code must be alphaNumeric";
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void validateNamePositiveTest() {
        assertTrue(humanResourceValidator.validateName("Alessio"));
    }

    @Test
    public void validateNullNameNegativeTest() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            humanResourceValidator.validateName(null);
        });
        String message = "Name can't be null or empty";
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void validateEmptyNameNegativeTest() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            humanResourceValidator.validateName("");
        });
        String message = "Name can't be null or empty";
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void validateWrongNameNegativeTest() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            humanResourceValidator.validateName("ale9876@@@");
        });
        String message = "The name must contains only characters";
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void validateEmail() {
        assertTrue(humanResourceValidator.validateEmail("mail@mail.it"));
    }

    @Test
    public void validateEmailNullMailNegativeTest() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            humanResourceValidator.validateEmail(null);
        });
        String message = "The email can't be null or empty";
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void validateEmailEmptyMailNegativeTest() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            humanResourceValidator.validateEmail("");
        });
        String message = "The email can't be null or empty";
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void validateSurnamePositiveTest() {
        assertTrue(humanResourceValidator.validateSurname("cassarino"));
    }

    @Test
    public void validateNullSurnameNegativeTest() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            humanResourceValidator.validateSurname(null);
        });

        String message = "Surname can't be null or empty";
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void validateEmptySurnameNegativeTest() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            humanResourceValidator.validateSurname("");
        });

        String message = "Surname can't be null or empty";
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void validateWrongSurnameNegativeTest() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            humanResourceValidator.validateSurname("cas566@");
        });

        String message = "The surname must contains only characters";
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void insertHumanResourcePositiveTest() {
        HumanResourceDto humanResourceDto = new HumanResourceDto("Paolo", "Maldini", "maldini@maldini.it",
                "+393376548987", "lppsll87s26r769c", false, true);
        assertTrue(humanResourceService.insertHumanResource(humanResourceDto));
    }

    @Test
    public void deleteHumanResource() {
        assertTrue(humanResourceService.deleteHumanResource(7l));
    }

    @Test
    public void findHumanResourceFromNameAndSurnameTest() {
        System.out.println(humanResourceService.getHumanResourceFromNameAndSurname("Michele", "Marrone"));
    }

    @Test
    public void findHumanResourcefromIdTest() {
        System.out.println(humanResourceService.getHumanResourceFromId(5l));
    }

    @Test
    public void findCdaTest() {
        List<HumanResourceDto> humanResourceList = humanResourceService.getCda();
        for (HumanResourceDto humanResource : humanResourceList) {
            System.out.println(humanResource);
        }
    }
}
