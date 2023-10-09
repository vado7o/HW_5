import java.util.ArrayList;

public class UI {

    public static void main(String[] args) {

        PhoneBook phoneBook = new PhoneBook(new ArrayList<>());
        Printer printer = new Printer();

        phoneBook.addContact(new Contact("Vasiliy", "Ivanov", "89138499225", "vasIvanov@mail.ru"));
        phoneBook.addContact(new Contact("Ivan", "Petrov", "891385099944", "ivPetrov@mail.ru"));
        phoneBook.addContact(new Contact("Artem", "Saprikin", "89234337788", "artSapr@mail.ru"));
        phoneBook.addContact(new Contact("Petya", "89029598213"));
        phoneBook.addContact(new Contact("Vitaliy", "Larionov", "89049556677"));
        printer.print(phoneBook);

        phoneBook.deleteContact("Ivan", "Petrov");
        phoneBook.deleteContact("Petya");
        printer.print(phoneBook);

        phoneBook.editContact(1, "Vasya", null, null, "vasya007@rambler.ru");
        printer.print(phoneBook);

    }
}