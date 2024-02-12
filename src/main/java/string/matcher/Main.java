package string.matcher;

import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static String getMatchingString(String str) {
        String output = "";

        Pattern pattern = Pattern.compile("^(.*?)([-_*+%&$])(.*?)([-_*+%&$])(\\D*)(.*)");
        Matcher matcher = pattern.matcher(str);

        if (matcher.find()) {

            if (matcher.group(1) != null)
                output = matcher.group(1);

            if (!matcher.group(5).equals("")) {
                if (!output.equals(""))
                    output = output + " ";
                output = output + matcher.group(5);
            }
        }
        return output;
    }

    public static void applyMethodsWithGooseAndHoveDoneInName() {
        try {
            SomeClass someClass = new SomeClass();

            Class<? extends SomeClass> c = someClass.getClass();
            Method[] m = c.getDeclaredMethods();

            for (Method e : m) {
                String mName = e.getName();
                if(mName.toLowerCase().contains("goose") || mName.toLowerCase().contains("haveDone".toLowerCase())) {
                    System.out.println(mName + "()");
                    e.setAccessible(true);
                    e.invoke(someClass);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        applyMethodsWithGooseAndHoveDoneInName();

        System.out.println(getMatchingString("12-xk-vv999"));
    }
}
