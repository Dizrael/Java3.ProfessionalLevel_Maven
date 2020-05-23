package lesson6.myTest.test;

import lesson6.myTest.MyTestClass;
import org.junit.Assert;
import org.junit.Test;

public class Tester {
    MyTestClass mtc = new MyTestClass();
    int[] mass1 = {1,2,3,4, 5,6,7,8,9,10};
    int[] answ1 = {5,6,7,8,9,10};
    int[] mass2 = {1,1,1,1,1,1,1,1};
    int[] mass3 = {1,2,3,4,5,6,7,8,9,10,11,12, 4, 4, 15, 16, 17, 18};
    int[] answ3 = {15, 16, 17, 18};

    int[] arr1 = {1, 2, 3, 4, 5, 6, 7};
    int[] arr2 = {2, 2, 3, 4, 5, 6, 7};
    int[] arr3 = {1, 2, 3, 5, 5, 6, 7};
    int[] arr4 = {0, 2, 3, 0, 5, 6, 7};


    @Test
    public void testScissors1(){
        Assert.assertArrayEquals(answ1, mtc.scissor(mass1));
    }

    @Test
    public void testScissors2(){
        Assert.assertArrayEquals(answ3, mtc.scissor(mass3));
    }

    @Test(expected = RuntimeException.class)
    public void testScissors3() {
        mtc.scissor(mass2);
    }

    @Test
    public void test1or4_1(){
        Assert.assertTrue(mtc.have1or4(arr1));
    }

    @Test
    public void test1or4_2(){
        Assert.assertTrue(mtc.have1or4(arr2));
    }

    @Test
    public void test1or4_3(){
        Assert.assertTrue(mtc.have1or4(arr3));
    }

    @Test
    public void test1or4_4(){
        Assert.assertFalse(mtc.have1or4(arr4));
    }

}
