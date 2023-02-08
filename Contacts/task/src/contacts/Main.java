package contacts;

public class Main {
    public static void main(String[] args) {
        UI ui = args.length == 0 ? new UI() : new UI(args[0]);
        ui.start();
    }
}
