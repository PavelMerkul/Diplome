package data;

import com.github.javafaker.Faker;
import lombok.Data;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataHelper {
    private static final Faker faker = new Faker(new Locale("en"));

    private DataHelper() {
    }

    public static String getApprovedCardNumber() {
        return "4444 4444 4444 4441";
    }

    public static String getDeclinedCardNumber() {
        return "4444 4444 4444 4442";
    }

    public static String getRandomCardNumber() {
        return faker.business().creditCardNumber();
    }

    public static String generateMonth(int shift) {
        var date = LocalDate.now().plusMonths(shift).format(DateTimeFormatter.ofPattern("MM"));
        return date;
    }

    public static String generateYear(int shift) {
        var date = LocalDate.now().plusYears(shift).format(DateTimeFormatter.ofPattern("yy"));
        return date;
    }

    public static String generateOwner(String locale) {
        var faker = new Faker(new Locale(locale));
        return faker.name().lastName() + " " + faker.name().firstName();
    }

    public static String generateCVC() {
        var faker = new Faker(new Locale("en"));
        return faker.numerify("###");
    }

    public static String generateInvalidCVC() {
        var faker = new Faker(new Locale("en"));
        return faker.numerify("##");
    }

}