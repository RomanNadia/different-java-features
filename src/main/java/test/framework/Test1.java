package test.framework;

import test.framework.*;

public class Test1 {

    @SetUpBeforAll
    public void beforeAll() {
        System.out.println("do beforeAll");
    }

    @SetUpBefore
    public void beforeTest() {
        System.out.println("do beforeTest");
    }

    @SetUpAfter
    public void afterTest() {
        System.out.println("do afterTest");
    }

    @Test
    public void method1() {
        int a = 1 + 1;
        assert a == 2;
    }

    @Test
    @CalculateTime
    public void method2() {
        int a = 1 + 2;
        assert a == 3;
    }


    @Test(expected = RuntimeException.class)
    public void method3() {
        throw new RuntimeException();
    }


    @Test(expected = RuntimeException.class)
    public void method4() {
        int a = 2 + 10;
    }


    @SetUpAfterAll
    public void afterAll() {
        System.out.println("do afterAll");
    }

}
