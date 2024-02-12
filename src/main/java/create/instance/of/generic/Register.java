package create.instance.of.generic;

public class Register<E> {
    private E data;
    private Class<E> kind;

    public Register(Class<E> kind) {
        try {
            data = kind.newInstance();
            this.kind = kind;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public E createE() {
        try {
            E something = kind.newInstance();
            return something;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
