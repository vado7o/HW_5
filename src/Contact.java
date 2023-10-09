public class Contact {
    public String name;
    public String surname = "";
    public String number;
    public String email = "";

    public Contact(String name, String surname, String number, String email) {
        this.name = name;
        this.surname = surname;
        this.number = number;
        this.email = email;
    }

    public Contact(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public Contact(String name, String surname, String number) {
        this.name = name;
        this.surname = surname;
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public void setPhone(String number) {
        this.number = number;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
