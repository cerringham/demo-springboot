package it.proactivity.demospringboot;

import it.proactivity.demospringboot.dto.CustomerInformationDto;
import it.proactivity.demospringboot.dto.ProjectCustomerDto;
import it.proactivity.demospringboot.utility.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DemoSpringbootApplicationTests {

    CustomerUtility customerUtility = new CustomerUtility();
    ProjectUtility projectUtility = new ProjectUtility();
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

        ProjectCustomerDto dto = new ProjectCustomerDto("Milanello project","2023-09-28",
                "61111a","Milan A.C.", "milan@milan.com",
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

        ProjectCustomerDto dto = new ProjectCustomerDto("Team Staff project","2023-09-28",
                "61111a","Milan A.C.", "milan@milan.com",
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
        Exception exception = assertThrows(IllegalArgumentException.class, () ->{
            projectUtility.insertCompliteProject(null);
        });

        String message = "The ProjectCustomerDto is null";
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void insertCompliteProjectNullEndDateNegativeTest() {

        ProjectCustomerDto dto = new ProjectCustomerDto("Nuovo progetto",null,
                "11112er","Luftanza S.P.A", "luftanza@luftanza2.com",
                "+992387652", "Airways");

        Exception exception = assertThrows(IllegalArgumentException.class, () ->{
            projectUtility.insertCompliteProject(dto);
        });

        String message = "The parameters cannot be null";
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void insertCompliteProjectWrongFormatEndDateNegativeTest() {

        ProjectCustomerDto dto = new ProjectCustomerDto("Nuovo progetto","23-12-2023",
                "11112er","Luftanza S.P.A", "luftanza@luftanza2.com",
                "+992387652", "Airways");

        Exception exception = assertThrows(IllegalArgumentException.class, () ->{
            projectUtility.insertCompliteProject(dto);
        });

        String message = "Cannot parse the endDate " + dto.getEndDate();
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void insertCompliteProjectNullNameNegativeTest() {

        ProjectCustomerDto dto = new ProjectCustomerDto(null,"2023-01-19",
                "11112er","Luftanza S.P.A", "luftanza@luftanza2.com",
                "+992387652", "Airways");

        Exception exception = assertThrows(IllegalStateException.class, () ->{
            projectUtility.insertCompliteProject(dto);
        });

        String message = "Cannot create project";
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void insertCompliteProjectEmptyNameNegativeTest() {

        ProjectCustomerDto dto = new ProjectCustomerDto("","2023-01-19",
                "11112er","Luftanza S.P.A", "luftanza@luftanza2.com",
                "+992387652", "Airways");

        Exception exception = assertThrows(IllegalStateException.class, () ->{
            projectUtility.insertCompliteProject(dto);
        });

        String message = "Cannot create project";
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void insertCompliteProjectEmptyReportingIdNegativeTest() {

        ProjectCustomerDto dto = new ProjectCustomerDto("Somethig","2023-01-19",
                "","Luftanza S.P.A", "luftanza@luftanza2.com",
                "+992387652", "Airways");

        Exception exception = assertThrows(IllegalStateException.class, () ->{
            projectUtility.insertCompliteProject(dto);
        });

        String message = "Cannot create project";
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void insertCompliteProjectNullReportingIdNegativeTest() {

        ProjectCustomerDto dto = new ProjectCustomerDto("Somethig","2023-01-19",
                null,"Luftanza S.P.A", "luftanza@luftanza2.com",
                "+992387652", "Airways");

        Exception exception = assertThrows(IllegalStateException.class, () ->{
            projectUtility.insertCompliteProject(dto);
        });

        String message = "Cannot create project";
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void getCustomersInformations() {
      List<CustomerInformationDto> projectList = projectUtility.getAllProjectForeachCustomer();
      projectList.stream().forEach(e ->
              System.out.println(e.getCustomerName() + "\n" + e.getProjects()));
    }

}
