import java.util.List;

public class Printer {
    String head = "+------+-----------------+-----------------+-----------------+-----------------------+" +
            "\n|      |       Имя       |     Фамилия     |      НОМЕР      |         EMAIL         |" +
            "\n+------+-----------------+-----------------+-----------------+-----------------------+";
    public StringBuilder print(PhoneBook phoneBook) {
        List<Contact> phonebook = phoneBook.phonebook;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nВНИМАНИЕ!!! ПЕЧАТАЮ ИНФОРМАЦИЮ ИЗ СПРАВОЧНИКА!!!!\n");
        stringBuilder.append(head);
        for (int i = 0; i < phonebook.size(); i++) {
            stringBuilder.append("\n|" + (i+1) + " ".repeat(6 - String.valueOf(i+1).length()) + "|" +
                    phonebook.get(i).name + " ".repeat(17 - phonebook.get(i).name.length()) + "|" +
                    phonebook.get(i).surname + " ".repeat(17 - phonebook.get(i).surname.length()) + "|" +
                    phonebook.get(i).number + " ".repeat(17 - phonebook.get(i).number.length()) +"|" +
                    phonebook.get(i).email + " ".repeat(23 - phonebook.get(i).email.length()) + "|" +
                    "\n+------+-----------------+-----------------+-----------------+-----------------------+");
        }
        System.out.println(stringBuilder);
        return stringBuilder;
    }
}
