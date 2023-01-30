package it.proactivity.demospringboot.service;

import it.proactivity.demospringboot.dto.CompanyDto;
import it.proactivity.demospringboot.model.Company;
import it.proactivity.demospringboot.utility.CompanyUtility;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    CompanyUtility companyUtility = new CompanyUtility();

    public List<String> getCompaniesList() {
        List<Company> companyList = companyUtility.getAll();

        return companyList.stream().map(x -> x.getName()).collect(Collectors.toList());
    }

    public List<CompanyDto> companyDetailsList() {
        List<Company> companyList = companyUtility.getAll();

        return companyList.stream().map(x -> new CompanyDto(x.getId(), x.getName())).collect(Collectors.toList());
    }
}
