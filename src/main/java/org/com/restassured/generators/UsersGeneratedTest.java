package org.com.restassured.generators;

import java.util.Random;

public class UsersGeneratedTest {

    private static final String[] names = {"Alice", "Bob", "Carol", "David", "Eva", "Frank", "Grace", "Hannah", "Ivy", "Jack"};
    private static final String[] surnames = {"Smith", "Johnson", "Brown", "Davis", "Lee", "Garcia", "Clark", "White", "Moore", "Hall"};

    public static String gerarNomeAleatorio() {
        Random rand = new Random();
        String name = names[rand.nextInt(names.length)];
        String surname = surnames[rand.nextInt(surnames.length)];

        return name + " " + surname;
    }
}
