package contacts;

import java.io.Serializable;
import java.time.LocalDate;

public class Person extends Contact implements Serializable {
    private String name;
    private String surname;
    private LocalDate birthDate;
    private Gender gender;

    public Person(String phoneNumber, String name, String surname, LocalDate birthDate, Gender gender) {
        super(phoneNumber);
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public void setName(String name) {
        this.name = name;
        super.editTimeUpdate();
    }

    public void setSurname(String surname) {
        this.surname = surname;
        super.editTimeUpdate();
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        super.editTimeUpdate();
    }

    public void setGender(Gender gender) {
        this.gender = gender;
        super.editTimeUpdate();
    }

    @Override
    public String getSuperstring() {
        return name + surname + super.getSuperstring();
    }

    @Override
    public String toString() {
        return String.format("Name: %s%nSurname: %s%nBirth date: %s%nGender: %s%n%s",
                name,
                surname,
                birthDate != null ? birthDate.toString() : "[no data]",
                gender != null ? gender.name() : "[no data]",
                super.toString());
    }

    @Override
    public String getInfo() {
        return String.format("%s %s", name, surname);
    }
}
