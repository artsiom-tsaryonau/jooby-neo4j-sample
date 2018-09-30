package com.epam.project;

import javax.inject.Singleton;

@Singleton
public class PersonRepository  {
    public Person findByName(String name) {
        return new Person();
    }
}
