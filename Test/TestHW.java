import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.assertj.core.api.AssertionsForClassTypes.*;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import java.util.ArrayList;
import java.util.Comparator;

public class TestHW {
    PhoneBook phoneBook = new PhoneBook(new ArrayList<>());

    /**
     * 1. СНАЧАЛА ПРОВОДИМ СЕРИЮ UNIT-ТЕСТОВ ДЛЯ НАШЕГО СОЗДАННОГО СПРАВОЧНИКА!
     */

    @Test
    /**
     * Тестируем метод добавления контакта. При этом проверяем количество контактов в справочнике (phonebook.size)
     */
    void testAddingContact() {
        phoneBook.addContact(new Contact("Vasiliy", "Ivanov", "89138499225", "vasIvanov@mail.ru"));
        phoneBook.addContact(new Contact("Ivan", "Petrov", "891385099944", "ivPetrov@mail.ru"));
        assertThat(phoneBook.phonebook.size()).isEqualTo(2);

        phoneBook.addContact(new Contact("Artem", "Saprikin", "89234337788", "artSapr@mail.ru"));
        assertThat(phoneBook.phonebook.size()).isEqualTo(3);
    }
    @Test
    /**
     * Проверяем правильность добавления данных контакта (например, имя или телефонный номер).
     */
    void checkContactData () {
        phoneBook.addContact(new Contact("Vasiliy", "Ivanov", "89138499225", "vasIvanov@mail.ru"));
        phoneBook.addContact(new Contact("Ivan", "Petrov", "891385099944", "ivPetrov@mail.ru"));
        assertThat(phoneBook.phonebook.get(1).name).isEqualTo("Vasiliy");
        assertThat(phoneBook.phonebook.get(0).number).isEqualTo("891385099944");
    }

    @Test
    /**
     * Проверяем правильность исполнения метода удаления контакта. При этом проверяем количество контактов в справочнике (phonebook.size)
     * и данные оставшегося контакта по индексу.
     */
    void checkDeleteContact() {
        phoneBook.addContact(new Contact("Petya", "89029598213"));
        phoneBook.addContact(new Contact("Vitaliy", "Larionov", "89049556677"));
        phoneBook.addContact(new Contact("Artem", "Saprikin", "89234337788", "artSapr@mail.ru"));
        phoneBook.deleteContact("Petya");
        assertThat(phoneBook.phonebook.size()).isEqualTo(2);
        assertThat(phoneBook.phonebook.get(1).name).isEqualTo("Vitaliy");
    }

    @Test
    /**
     * Тестируем правильность выполнения метода редактирования контакта. При этом проверяем корректность изменения имени!
     */
    void checkEdittingContact() {
        phoneBook.addContact(new Contact("Vasiliy", "Ivanov", "89138499225", "vasIvanov@mail.ru"));
        phoneBook.editContact(0, "Vasya", null, null, "vasya007@rambler.ru");
        assertThat(phoneBook.phonebook.get(0).name).isEqualTo("Vasya");
    }

    @Test
    /**
     * Проводим юнит-тест на соответствие объекта определённому Классу (в нашем случае классу Contact).
     */
    void checkClassInstance() {
        phoneBook.addContact(new Contact("Petya", "89029598213"));
        assertThat(phoneBook.phonebook.get(0)).isInstanceOf(Contact.class);
    }

    @Test
    /**
     * Тестируем правильность выбрасывания исключения при обращении по несуществующему индексу массива Контактов
     * (IndexOutOfBoundsException).
     */
    public void checkException() {
        phoneBook.addContact(new Contact("Vasiliy", "Ivanov", "89138499225", "vasIvanov@mail.ru"));
        assertThrows(IndexOutOfBoundsException.class, () -> phoneBook.phonebook.get(1));
    }

    @Test
    /**
     * Проводим Тестирование при помощи MOCK-заглушки. В этом случае заглушкой заменяем Интерфейс ChangePhoneBook.
     */
    void testInterfaceMock() {
        phoneBook.addContact(new Contact("Vasiliy", "Ivanov", "89138499225", "vasIvanov@mail.ru"));
        phoneBook.addContact(new Contact("Petya", "89029598213"));

        ChangePhoneBook changePhoneBook = mock(ChangePhoneBook.class);
        when(changePhoneBook.deleteContac(anyString())).thenReturn(phoneBook.phonebook.remove(1));

        changePhoneBook.deleteContac("ANY");
        assertEquals(1, phoneBook.phonebook.size());
    }

    /**
     * 2. ПРОВОДИМ ИНТЕГРАЦИОННОЕ ТЕСТИРОВАНИЕ НАШЕГО СПРАВОЧНИКА!
     */
    @Test
    void testIntegrationCheckData() {
        /**
         * Сначала создаём несколько экземпляров класса Contact
        */
        Contact contact4 = new Contact("Vitaliy", "Larionov", "89049556677");
        Contact contact1 = new Contact("Artem", "Saprikin", "89234337788", "artSapr@mail.ru");
        Contact contact3 = new Contact("Vasiliy", "Ivanov", "89138499225", "vasIvanov@mail.ru");
        Contact contact2 = new Contact("Ivan", "Petrov", "891385099944", "ivPetrov@mail.ru");

        /**
         * и формируем из них список phonebook1. Список данный сортируем по имени.
         */
        ArrayList<Contact> phonebook1 = new ArrayList<>();
        phonebook1.add(contact4);
        phonebook1.add(contact1);
        phonebook1.add(contact3);
        phonebook1.add(contact2);
        phonebook1.sort(Comparator.comparing(o -> o.name));

        /**
         * Затем формируем экземпляр класса PhoneBook (phonebook2) и добавляем в него такие же экземпляры класса Contact,
         * но в этот раз через имеющийся в классе метод addContact!!!
         */
        PhoneBook phoneBook2 = new PhoneBook(new ArrayList<>());
        phoneBook2.addContact(contact4);
        phoneBook2.addContact(contact1);
        phoneBook2.addContact(contact3);
        phoneBook2.addContact(contact2);

        /**
         * Теперь проверяем, что к примеру последний экземпляр класса phonebook2 соответствует последнему экземпляру в
         * отсортированном списке phonebook1 (заметьте, что метод addContact класса PhoneBook также сортирует список
         * при своей реализации!!!).
         */
        assertEquals(phonebook1.get(phonebook1.size() - 1).name, phoneBook2.phonebook.get(phoneBook2.phonebook.size() - 1).name);
    }

    @Test
    /**
     * Ещё один интеграционный тест. Проверяем, что модуль Printer правильно распечатывает список контактов из модуля
     * PhoneBook. Для этого проверяем имеются ли ключевые слова (имя, фамилия, телефон и т.д.) в нашей распечатке.
     */
    void testPrinterWork() {
        PhoneBook phoneBook = new PhoneBook(new ArrayList<>());
        phoneBook.addContact(new Contact("Vitaliy", "Larionov", "89049556677"));
        phoneBook.addContact(new Contact("Artem", "Saprikin", "89234337788", "artSapr@mail.ru"));
        phoneBook.addContact(new Contact("Vasiliy", "Ivanov", "89138499225", "vasIvanov@mail.ru"));

        Printer printer = new Printer();

        assertTrue(printer.print(phoneBook).toString().contains("Vitaliy"));
        assertTrue(printer.print(phoneBook).toString().contains("Saprikin"));
        assertTrue(printer.print(phoneBook).toString().contains("89138499225"));
    }
}

