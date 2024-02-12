package test.framework;

import string.matcher.SomeClass;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
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


    public static void applayMethodsWithGooseAndHoveDoneInName() {
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


    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Test1 test1 = new Test1();
        ArrayList<Method> testMethods = new ArrayList<>();
        ArrayList<Method> setUpAfterMethods = new ArrayList<>();
        ArrayList<Method> setUpAfterAllMethods = new ArrayList<>();
        ArrayList<Method> setUpBeforeAllMethods = new ArrayList<>();
        ArrayList<Method> setUpBeforeMethods = new ArrayList<>();
        Method[] methods = Test1.class.getMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class)) {
                testMethods.add(method);
            } else if (method.isAnnotationPresent(SetUpAfter.class)) {
                setUpAfterMethods.add(method);
            } else if (method.isAnnotationPresent(SetUpAfterAll.class)) {
                setUpAfterAllMethods.add(method);
            } else if (method.isAnnotationPresent(SetUpBeforAll.class)) {
                setUpBeforeAllMethods.add(method);
            } else if (method.isAnnotationPresent(SetUpBefore.class)) {
                setUpBeforeMethods.add(method);
            }
        }


        invokeAllMethodFromArrayList(setUpBeforeAllMethods, test1, "\t start setUpBeforeAll",
                "\t end setUpBeforeAll");


        for (Method m: testMethods) {
            System.out.println("\t start test");
            invokeAllMethodFromArrayList(setUpBeforeMethods, test1, "\t\t start setUpBefore",
                    "\t\t end setUpBefore");

            Test testAnnotation = m.getAnnotation(Test.class);
            Class<? extends Throwable> expectedException = testAnnotation.expected();
            long time = 0;

            try {
                long startTime = System.currentTimeMillis();
                m.invoke(test1);
                long endTime = System.currentTimeMillis();
                time = endTime - startTime;
                if (expectedException.equals(Test.None.class)) {
                    System.out.println("method " + m.getName() + " was successful");
                } else {
                    System.out.println("method " + m.getName() + " was not successful");
                }
            } catch (Exception e) {
                if (expectedException.isInstance(e.getCause())) {
                    System.out.println("method " + m.getName() + " was successful");
                } else {
                    System.out.println("method " + m.getName() + " was not successful");
                    e.printStackTrace();
                }
            }

            if (m.isAnnotationPresent(CalculateTime.class)) {
                System.out.println("\t calculate time \n time - " + time + "\n\t end test");
            }

            invokeAllMethodFromArrayList(setUpAfterMethods, test1, "\t\t start setUpAfter",
                    "\t\t end setUpAfter");
            System.out.println("\t end test \n");
        }


        invokeAllMethodFromArrayList(setUpAfterAllMethods, test1, "\t start test.framework.SetUpAfterAll",
                "\t end test.framework.SetUpAfterAll");
    }


    public static void invokeAllMethodFromArrayList(ArrayList<Method> methods, Test1 test1, String startWord,
                                                    String endWord) throws InvocationTargetException, IllegalAccessException {
        for(Method m: methods) {
            System.out.println(startWord + "\n\t in " + m.getName());
            m.invoke(test1);
            System.out.println(endWord + "\n");
        }
    }


}
