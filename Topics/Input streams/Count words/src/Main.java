import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input = reader.readLine().trim();

        System.out.println(input.isBlank() ? 0 : input.split(" +").length);

        reader.close();
    }
}