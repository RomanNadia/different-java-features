package create.instance.of.generic;

public class Main {
    public static void main(String[] args) {
        Register<String> register = new Register<>(String.class);
        String instanceOfGeneric = register.createE();
    }
}
