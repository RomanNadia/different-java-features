package serialization;

import java.io.Serializable;

public class ClassToSave1 implements Serializable {
    private String name;
    private int a;
    private int b;

    public ClassToSave1(String name, int a, int b) {
        this.name = name;
        this.a = a;
        this.b = b;
    }

    @Override
    public String toString() {
        return "serialization.ClassToSave1{" +
                "name='" + name + '\'' +
                ", a=" + a +
                ", b=" + b +
                '}';
    }
}
