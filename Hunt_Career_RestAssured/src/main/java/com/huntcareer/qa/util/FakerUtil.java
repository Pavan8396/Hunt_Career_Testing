package com.huntcareer.qa.util;

import com.github.javafaker.Faker;

public class FakerUtil {

    private static final Faker faker = new Faker();

    public static String generateUserEmail() {
        return faker.internet().emailAddress();
    }

    public static String generateUserFirstName() {
        return faker.name().firstName();
    }

    public static String generateUserLastName() {
        return faker.name().lastName();
    }

    public static String generateEmployerEmail() {
        return faker.internet().emailAddress();
    }

    public static String generateCompanyName() {
        return faker.company().name();
    }

    public static String generateJobTitle() {
        return faker.job().title();
    }
}
