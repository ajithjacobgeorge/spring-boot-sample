package com.example.demo;

import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/demo")
public class PersonController {

    @PostMapping(value = "/{maxAge}/filter", consumes = "application/json", produces = "application/json")
    public List<Person> filterPerson(@PathVariable("maxAge") Integer maxAge, @RequestBody List<Person> personList) {

        List<Person> result = Collections.EMPTY_LIST;
        if (!CollectionUtils.isEmpty(personList)) {
            result = personList
                    .stream()
                    .filter(person -> person.getAge() < maxAge)
                    .sorted(Comparator.comparing(Person::getAge))
                    .collect(Collectors.toList());
        }
        return result;

    }
}
