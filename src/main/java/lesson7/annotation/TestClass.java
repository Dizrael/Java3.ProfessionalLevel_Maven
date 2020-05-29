package lesson7.annotation;



public class TestClass {
    @BeforeSuite
    public static void start() {
        System.out.println("Before Suite method");
    }

    @Test(priority = 9)
    public static  void test1() {
        System.out.println("test1");
    }
    @Test(priority = 6)
    public static  void test2() {
        System.out.println("test2");
    }
    @Test(priority = 3)
    public static  void test3() {
        System.out.println("test3");
    }
    @AfterSuite
    public static void off() {
        System.out.println("After Suite method");
    }

    public static  void neverCallMeth() {
        System.out.println("Nononono no no no no no noooooooo!");
    }

//    @AfterSuite
//    public static void off2() {
//        System.out.println("After Suite method2");
//    }

}
