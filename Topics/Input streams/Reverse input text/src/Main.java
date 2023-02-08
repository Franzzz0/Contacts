import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        char[] chars = new char[50];
        int n = reader.read(chars);
        for (int i = n - 1; i >= 0; i--) {
            System.out.print(chars[i]);
        }
        // start coding here
        reader.close();
    }
}