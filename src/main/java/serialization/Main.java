package serialization;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        ClassToSave1 classToSave1 = new ClassToSave1("Class to save", 1, 5);
        ClassToSave classToSave2 = new ClassToSave("Anna", "Ribko", false);

        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("object.ser"));
            outputStream.writeObject(classToSave1);
            outputStream.close();

            ObjectOutputStream outputStream2 = new ObjectOutputStream(new FileOutputStream("object2.ser"));
            outputStream2.writeObject(classToSave2);
            outputStream2.close();

            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("object.ser"));
            ClassToSave1 classToSave11 = (ClassToSave1) inputStream.readObject();
            System.out.println(classToSave11.toString());
            inputStream.close();

            ObjectInputStream inputStream2 = new ObjectInputStream(new FileInputStream("object2.ser"));
            ClassToSave classToSave22 = (ClassToSave) inputStream2.readObject();
            System.out.println(classToSave22.toString());
            inputStream2.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
