package it.proactivity.demospringboot.service;

import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Service
public class DemoService {

    public String nameToUpperCase(String name) {
        if(name != null && !name.isEmpty())
            return name.toUpperCase();
        else
            return "no parameter";
    }

    public String nameSurnameToUpperCase(String name, String surname) {
        StringBuilder sb = new StringBuilder();

        if(name != null && !name.isEmpty()) {
            sb.append(name.toUpperCase());
        }
        if(surname != null && !surname.isEmpty()) {
            if(!sb.isEmpty()) {
                sb.append(" ");
            }
            sb.append(surname.toUpperCase());
        }

        if(sb.isEmpty())
            return "no parameters";
        else
            return sb.toString();
    }

    public List<String> createFakeList() {
        return Arrays.asList("Come as you are", "Nevermind", "Smells like ten spirit");
    }
}
