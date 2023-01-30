package it.proactivity.demospringboot.controller;

import it.proactivity.demospringboot.dto.CompanyDto;
import it.proactivity.demospringboot.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @GetMapping("/company/list")
    public List<String> companyNameList() {
        return companyService.getCompaniesList();
    }

    @GetMapping("/company/details-list")
    public List<CompanyDto> companyDetailsList() {
        return companyService.companyDetailsList();
    }
}
