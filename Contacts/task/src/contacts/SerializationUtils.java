package contacts;

import java.io.*;

public class SerializationUtils {

    public static PhoneBook getPhoneBookFromFile(String filename) {
        PhoneBook book = (PhoneBook) SerializationUtils.deserialize(filename);
        if (book == null) {
            book = new PhoneBook();
        }
        return book;
    }

    public static void serialize(Object obj, String fileName) {
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Object deserialize(String fileName) {
        Object obj = null;
        try {
            FileInputStream fis = new FileInputStream(fileName);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);
            obj = ois.readObject();
            ois.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return obj;
    }
}
