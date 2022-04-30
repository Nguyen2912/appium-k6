package Test.lesson_20;

import org.testng.annotations.*;

public class TestNGHook02 {
    @BeforeSuite
    public void beforeSuite(){
        System.out.println("Before Suite");
    }
    @BeforeTest
    public void beforeTest(){
        System.out.println("\t--> Before Test");
    }
    @BeforeClass
    public void beforeClass(){
        System.out.println("\t\t--> Before class");
    }
    @BeforeMethod
    public void beforeMethod(){
        System.out.println("\t\t\t--> Before Method");
    }
    //===============================================
    @AfterSuite
    public void afterSuite(){
        System.out.println("After Suite");
    }
    @AfterTest
    public void afterTest(){
        System.out.println("\t--> After Test");
    }

    @AfterTest
    public void afterClass(){
        System.out.println("\t\t--> After class");
    }
    @AfterMethod
    public void afterMethod(){
        System.out.println("\t\t\t--> After Method");
    }

    @Test
    public void testA(){
        System.out.println("\t\t\t\t-->Test A_class 02");
    }
    @Test
    public void testB(){
        System.out.println("\t\t\t\t-->Test B_class 02 ");
    }
}
