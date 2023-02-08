
class Problem {
    public static void main(String[] args) {
        if (args.length != 0) {
            for (int i = 0 ; i < args.length; i += 2) {
            System.out.printf("%s=%s%n", args[i], args[i + 1]);
            }
        }
    }
}