package contacts;

import java.time.LocalDate;
import java.util.Scanner;

public class ContactManager {
    private final Scanner scanner;

    public ContactManager(Scanner scanner) {
        this.scanner = scanner;
    }

    public void editContact(Contact contact) {
        while (true) {
            try {
                if (contact.getClass() == Person.class) {
                    editPerson(contact);
                    break;
                } else if (contact.getClass() == Organization.class) {
                    editOrganization(contact);
                    break;
                }
            } catch (Exception e) {
                System.out.println("unknown command.");
            }
        }
    }

    public Contact getContact() {
        System.out.println("Enter the type (person, organization): ");
        Contact contact;
        while (true) {
            String input = scanner.nextLine().toLowerCase();
            if (input.equals("person")) {
                contact = getPerson();
                break;
            } else if (input.equals("organization")) {
                contact = getOrganization();
                break;
            } else {
                System.out.println("Unknown command.");
            }
        }
        return contact;
    }

    private void editOrganization(Contact contact) throws Exception {
        Organization organization = (Organization) contact;
        System.out.println("Select a field (name, address, number):");
        String field = scanner.nextLine().toLowerCase().trim();
        switch (field) {
            case "name" -> organization.setName(getSimpleString("organization name"));
            case "address" -> organization.setAddress(getSimpleString("address"));
            case "number" -> organization.setPhoneNumber(getNumber());
            default -> throw new Exception("Unknown command.");
        }
    }

    private void editPerson(Contact contact) throws Exception{
        Person person = (Person) contact;
        System.out.println("Select a field (name, surname, birth, gender, number):");
        String field = scanner.nextLine().toLowerCase().trim();
        switch (field) {
            case "name" -> person.setName(getSimpleString("name"));
            case "surname" -> person.setSurname(getSimpleString("surname"));
            case "birth" -> person.setBirthDate(getBirthDate());
            case "gender" -> person.setGender(getGender());
            case "number" -> person.setPhoneNumber(getNumber());
            default -> throw new Exception("Unknown command.");
        }
    }

    private Contact getOrganization() {
        String name = getSimpleString("organization name");
        String address = getSimpleString("address");
        String phoneNumber = getNumber();
        return new Organization(phoneNumber, name, address);
    }

    private Contact getPerson() {
        String name = getSimpleString("name");
        String surname = getSimpleString("surname");
        LocalDate birthDate = getBirthDate();
        Gender gender = getGender();
        String number = getNumber();
        return new Person(number, name, surname, birthDate, gender);
    }

    private String getNumber() {
        System.out.println("Enter the number:");
        String number = scanner.nextLine();
        if (!Contact.isNumberCorrect(number)) {
            System.out.println("Wrong number format!");
        }
        return number;
    }

    private Gender getGender() {
        Gender gender = null;
        System.out.println("Enter the gender (M, F):");
        String input = scanner.nextLine();
        switch (input.toUpperCase()) {
            case "M" -> gender = Gender.M;
            case "F" -> gender = Gender.F;
            default -> System.out.println("Bad gender!");
        }
        return gender;
    }

    private LocalDate getBirthDate() {
        LocalDate birthDate = null;
        System.out.println("Enter the birth date:");
        try {
            birthDate = LocalDate.parse(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Bad birth date!");
        }
        return birthDate;
    }

    private String getSimpleString(String message) {
        System.out.printf("Enter the %s:%n", message);
        return scanner.nextLine();
    }
}
