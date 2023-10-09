
public interface ChangePhoneBook {
    Contact deleteContac(String name);
    void addContact(Contact contact);
    void deleteContact(String name, String surname);
    void editContact(int i, String name, String surname, String number, String email);
}
