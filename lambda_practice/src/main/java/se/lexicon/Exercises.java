package se.lexicon;

import se.lexicon.data.DataStorage;
import se.lexicon.model.Gender;
import se.lexicon.model.Person;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Exercises {

    private final static DataStorage storage = DataStorage.INSTANCE;

    /*
       1.	Find everyone that has firstName: “Erik” using findMany().
    */
    public static void exercise1(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> person = p -> p.getFirstName().equals("Erik");
        List<Person> persons = storage.findMany(person);
        persons.forEach(System.out::println);
        System.out.println("----------------------");
    }

    /*
        2.	Find all females in the collection using findMany().
     */
    public static void exercise2(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> person = p -> p.getGender().equals(Gender.FEMALE);
        List<Person> persons = storage.findMany(person);
        persons.forEach(System.out::println);
        System.out.println("----------------------");
    }

    /*
        3.	Find all who are born after (and including) 2000-01-01 using findMany().
     */
    public static void exercise3(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> person = p -> p.getBirthDate().isAfter(LocalDate.parse("2019-12-31"));
        List<Person> persons = storage.findMany(person);
        persons.forEach(System.out::println);
        System.out.println("----------------------");
    }

    /*
        4.	Find the Person that has an id of 123 using findOne().
     */
    public static void exercise4(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> person = p -> p.getId() == 123;
        Person personID = storage.findOne(person);
        System.out.println(personID);
        System.out.println("----------------------");
    }

    /*
        5.	Find the Person that has an id of 456 and convert to String with following content:
            “Name: Nisse Nilsson born 1999-09-09”. Use findOneAndMapToString().
     */
    public static void exercise5(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> person = p -> p.getId() == 456;
        Function<Person, String> personToString = p -> "Name: " + p.getFirstName()
                + " " + p.getLastName() + " born " + p.getBirthDate();
        String personToStringID = storage.findOneAndMapToString(person, personToString);
        System.out.println(personToStringID);
        System.out.println("----------------------");
    }

    /*
        6.	Find all male people whose names start with “E” and convert each to a String using findManyAndMapEachToString().
     */
    public static void exercise6(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> person = p -> p.getGender().equals(Gender.MALE) && p.getFirstName().startsWith("E");
        Function<Person, String> personToString = p -> "Name: " + p.getFirstName() + " " +
                p.getLastName() + " Gender " + p.getGender() + " born " + p.getBirthDate();
        List<String> personToStringList = storage.findManyAndMapEachToString(person, personToString);
        personToStringList.forEach(System.out::println);
        System.out.println("----------------------");
    }

    /*
        7.	Find all people who are below age of 10 and convert them to a String like this:
            “Olle Svensson 9 years”. Use findManyAndMapEachToString() method.
     */
    public static void exercise7(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> person = p -> LocalDate.now().getYear() - p.getBirthDate().getYear() < 10;
        Function<Person, String> personToString = p -> p.getFirstName() + " " +
                p.getLastName() + " " + (LocalDate.now().getYear() - p.getBirthDate().getYear()) + " years";
        List<String> personToStringList = storage.findManyAndMapEachToString(person, personToString);
        personToStringList.forEach(System.out::println);
        System.out.println("----------------------");
    }

    /*
        8.	Using findAndDo() print out all people with firstName “Ulf”.
     */
    public static void exercise8(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> person = p -> p.getFirstName().equals("Ulf");
        Consumer<Person> consumer = p -> System.out.println(p);
        storage.findAndDo(person, consumer);
        System.out.println("----------------------");
    }

    /*
        9.	Using findAndDo() print out everyone who have their lastName contain their firstName.
     */
    public static void exercise9(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> person = p -> p.getLastName().contains(p.getFirstName());
        Consumer<Person> consumer = p -> System.out.println(p);
        storage.findAndDo(person, consumer);
        System.out.println("----------------------");
    }

    /*
        10.	Using findAndDo() print out the firstName and lastName of everyone whose firstName is a palindrome.
     */
    public static void exercise10(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> person = p -> p.getFirstName().toLowerCase().equals(new StringBuilder(p.getFirstName()).reverse().toString().toLowerCase());
        Consumer<Person> consumer = p -> System.out.println(p.getFirstName() + " " + p.getLastName());
        storage.findAndDo(person, consumer);
        System.out.println("----------------------");
    }

    /*
        11.	Using findAndSort() find everyone whose firstName starts with A sorted by birthdate.
     */
    public static void exercise11(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> person = p -> p.getFirstName().startsWith("A");
        Comparator<Person> comparator = Comparator.comparing(p -> p.getBirthDate());
        List<Person> personToSort = storage.findAndSort(person, comparator);
        personToSort.forEach(System.out::println);
        System.out.println("----------------------");
    }


    /*
        12.	Using findAndSort() find everyone born before 1950 sorted reversed by lastest to earliest.
     */
    public static void exercise12(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> person = p -> p.getBirthDate().getYear()<1950;
        Comparator<Person> comparator = Comparator.comparing(p -> p.getBirthDate());
        List<Person> personToSort =storage.findAndSort(person, comparator);
        personToSort.forEach(System.out::println);
        System.out.println("----------------------");
    }

    /*
        13.	Using findAndSort() find everyone sorted in following order: lastName > firstName > birthDate.
     */
    public static void exercise13(String message) {
        System.out.println(message);
        //Write your code here
        Comparator<Person> coLastName = Comparator.comparing(Person::getLastName);
        Comparator<Person> coFirstName = Comparator.comparing(Person::getFirstName);
        Comparator<Person> coBirthDate= Comparator.comparing(Person::getBirthDate);

        Comparator<Person> comparator = coLastName.thenComparing(coFirstName).thenComparing(coBirthDate);
        List<Person> personToSort =storage.findAndSort(comparator);
        personToSort.forEach(System.out::println);
        System.out.println("----------------------");
    }
}
