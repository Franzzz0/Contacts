package contacts;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public abstract class Contact implements Serializable {
    private String phoneNumber;
    private final LocalDateTime timeCreated;
    private LocalDateTime timeEdited;

    public Contact(String phoneNumber) {
        setPhoneNumber(phoneNumber);
        this.timeCreated = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        this.timeEdited = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
    }

    public boolean hasNumber() {
        return !this.phoneNumber.equals("");
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (isNumberCorrect(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        } else {
            this.phoneNumber = "";
        }
        this.editTimeUpdate();
    }

    public void editTimeUpdate() {
        this.timeEdited = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
    }

    public String getSuperstring() {
        return this.phoneNumber;
    }

    public static boolean isNumberCorrect(String number) {
        String regex = "([+]?[(][a-zA-Z0-9]+[)]|[+]?[a-zA-Z0-9]+[ -][(][a-zA-Z0-9]{2,}[)]|[+]?[a-zA-Z0-9]+)" +
                "([ -][a-zA-Z0-9]{2,})*";
        return number.matches(regex);
    }

    @Override
    public String toString() {
        return String.format("Number: %s%nTime created: %s%nTime last edit: %s%n",
                this.hasNumber() ? getPhoneNumber() : "[no number]",
                timeCreated.toString(),
                timeEdited.toString());
    }

    abstract String getInfo();
}
