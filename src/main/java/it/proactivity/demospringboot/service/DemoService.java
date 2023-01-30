package it.proactivity.demospringboot.service;

import org.springframework.stereotype.Service;

@Service
public class DemoService {

    public String nameToUpperCase(String name) {
        if(name != null && !name.isEmpty())
            return name.toUpperCase();
        else
            return "no parameter";
    }

    public String nameSurnameToUpperCase(String name, String surname) {
        if(name != null && !name.isEmpty() && surname != null && !surname.isEmpty())
            return name.toUpperCase().concat(" ").concat(surname.toUpperCase());
        else
            return "no parameter";
    }
}
