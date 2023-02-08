import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.util.stream.IntStream;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfElements = scanner.nextInt();
        Deque<Integer> stack = new ArrayDeque<>();
        IntStream.range(0, numberOfElements).forEach(i -> stack.push(scanner.nextInt()));
        stack.forEach(System.out::println);
    }
}