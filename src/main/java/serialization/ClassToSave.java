package serialization;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class ClassToSave implements Externalizable {
    private String name;
    private String secondName;
    private boolean isMale;

    public ClassToSave() {
    }

    public ClassToSave(String name, String secondName, boolean isMale) {
        this.name = name;
        this.secondName = secondName;
        this.isMale = isMale;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(name);
        out.writeObject(secondName);
        out.writeBoolean(isMale);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = (String) in.readObject();
        secondName = (String) in.readObject();
        isMale = in.readBoolean();
    }

    @Override
    public String toString() {
        return "serialization.ClassToSave{" +
                "name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", isMale=" + isMale +
                '}';
    }
}
