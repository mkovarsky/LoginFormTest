package dataGenertor;

import com.github.javafaker.Faker;
import data.RegistrationData;

import java.util.Locale;

public class RegistrationDataGenerator {

    public static RegistrationData generateUnregisteredUser() {
        Faker faker = new Faker(new Locale("en"));
        RegistrationData registrationData = new RegistrationData(faker.internet().emailAddress(), faker.internet().password());
        return registrationData;
    }
    public static RegistrationData generateUserWithCyrillicInEmail() {
        Faker faker = new Faker(new Locale("ru"));
        RegistrationData registrationData = new RegistrationData(faker.name().firstName(), faker.internet().password());
        return registrationData;
    }
    public static RegistrationData generateUserWithNumbersInEmail() {
        Faker faker = new Faker(new Locale("en"));
        RegistrationData registrationData = new RegistrationData(Integer.toString(faker.number().numberBetween(1, 9999)),faker.internet().password());
        return registrationData;
    }
    public static RegistrationData generateUserWithIncorrectEmail() {
        Faker faker = new Faker(new Locale("en"));
        RegistrationData registrationData = new RegistrationData(faker.name().firstName(),faker.internet().password());
        return registrationData;
    }
}
