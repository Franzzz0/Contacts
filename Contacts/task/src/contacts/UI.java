package contacts;

import java.util.List;
import java.util.Scanner;

public class UI {
    private final Scanner scanner;
    private final PhoneBook phoneBook;
    private final ContactManager contactManager;
    private final String fileName;

    public UI(String... filename) {
        this.scanner = new Scanner(System.in);
        this.phoneBook = filename.length == 0 ? new PhoneBook() : SerializationUtils.getPhoneBookFromFile(filename[0]);
        this.fileName = filename.length == 0 ? null : filename[0];
        this.contactManager = new ContactManager(this.scanner);
    }

    public void start() {
        while (true) {
            System.out.println("\n[menu] Enter action (add, list, search, count, exit):");
            String input = scanner.nextLine().toLowerCase().trim();
            switch (input) {
                case "add" -> addContact();
                case "list" -> listMenu();
                case "search" -> searchMenu();
                case "count" -> System.out.printf("The Phone Book has %d records.%n", phoneBook.getRecordsNumber());
                case "exit" -> {
                    if (fileName != null) SerializationUtils.serialize(this.phoneBook, fileName);
                    return;
                }
                default -> System.out.println("Unknown command.");
            }
        }
    }

    private void listMenu() {
        if (phoneBook.getRecordsNumber() == 0) {
            System.out.println("Phone book is empty");
            return;
        }
        int record;
        while (true) {
            this.phoneBook.listRecords();
            System.out.println("\n[list] Enter action ([number], back):");
            String input = scanner.nextLine();
            if (input.equals("back")) break;
            try {
                record = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Unknown command.");
                continue;
            }
            if (record > 0 && record <= this.phoneBook.getRecordsNumber()) {
                recordMenu(this.phoneBook.getContact(record - 1));
                break;
            }
            System.out.printf("No record with number %d!%n", record);
        }
    }

    private void searchMenu() {
        if (phoneBook.getRecordsNumber() == 0) {
            System.out.println("Phone book is empty");
            return;
        }
        List<Contact> matched = search();
        int index;
        while (true) {
            System.out.println(matched.isEmpty() ? "No results." : String.format("Found %d results:", matched.size()));
            phoneBook.listRecords(matched);
            System.out.println("\n[search] Enter action ([number], back, again):");
            String input = scanner.nextLine();
            if (input.equals("back")) return;
            if (input.equals("again")) {
                matched = search();
                continue;
            }
            try {
                index = Integer.parseInt(input) - 1;
                if (index >= 0 && index < matched.size()) {
                    break;
                }
                System.out.println("Unknown command.");
            } catch (NumberFormatException e) {
                System.out.println("Unknown command.");
            }
        }
        recordMenu(matched.get(index));
    }

    private void recordMenu(Contact record) {
        while (true) {
            System.out.println();
            System.out.println(record);
            System.out.println("[record] Enter action (edit, delete, menu):");
            String input = scanner.nextLine();
            if (input.equals("menu")) break;
            if (input.equals("edit")) {
                contactManager.editContact(record);
                System.out.println("The record updated!");
                continue;
            }
            if (input.equals("delete")) {
                this.phoneBook.removeContact(record);
                System.out.println("The record removed!");
                break;
            }
            System.out.println("Unknown command.");
        }
    }

    private List<Contact> search() {
        System.out.println("Enter search query: ");
        return phoneBook.search(scanner.nextLine());
    }

    private void addContact() {
        Contact contact = this.contactManager.getContact();
        this.phoneBook.add(contact);
        System.out.println("The record added.");
    }
}
