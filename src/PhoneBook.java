import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class PhoneBook implements ChangePhoneBook {
    public List<Contact> phonebook;

    PhoneBook(List<Contact> phonebook) {
        this.phonebook = phonebook;
    }

    public void addContact(Contact contact) {
        phonebook.add(contact);
        phonebook.sort(Comparator.comparing(o -> o.name));
    }

    public void deleteContact(String name, String surname) {
        for (int i = 0; i < phonebook.size() - 1; i++) {
            if (Objects.equals(phonebook.get(i).name, name) && Objects.equals(phonebook.get(i).surname, surname)) {
                phonebook.remove(i);
                break;
            }
        }
    }

    public void deleteContact(String name) {
        for (int i = 0; i < phonebook.size() - 1; i++) {
            if (Objects.equals(phonebook.get(i).name, name)) {
                phonebook.remove(i);
                break;
            }
        }
    }

    public void editContact(int i, String name, String surname, String number, String email) {
        if (i <= phonebook.size()) {
            if (name != null) phonebook.get(i).setName(name);
            if (surname != null) phonebook.get(i).setSurname(surname);
            if (number != null) phonebook.get(i).setPhone(number);
            if (email != null) phonebook.get(i).setEmail(email);
        }
        phonebook.sort(Comparator.comparing(o -> o.name));
    }

    @Override
    public Contact deleteContac(String name) {
        return null;
    }
}
