package allinmain.cmpt276.verdefoodchallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class DataCenterTest {
    private static DataCenter testing=DataCenter.getInstance();;


    @Test
    public void getInstance() {
        //In this case the Instance need to have something inside the mDataCenter
        //The return must be non-Null result

        assertNotSame(testing.getFoodsSize(),0);
    }

    @Test
    public void getFoodsSize() {
        //As the project started, the data set in Foodlist already be set
        //The Size Should not be 0
        assertNotSame(testing.getFoodsSize(),0);
    }

    @Test
    public void getFood() {
        //Well get the return the info of food
        //Consider the co2 info is not empty
        int size = testing.getFoodsSize();
        for (int i =  0 ; i<size;i++)
            assertNotSame("", (testing.getFood(i)).toString());
    }

    @Test
    public void addDietItem() {
       // int size = testing.getFoodsSize();
        //testing.addDietItem();
    }

    @Test
    public void getDietItem() {
        //assertNotNull(testing.getDietInfo());
    }

    @Test
    public void resetDiet() {
    }


    @Test
    public void calDietCo2() {
        assertNotEquals(0.0,testing.calDietCo2());
    }

    @Test
    public void getDietInfo() {
        assertNotNull(testing.getDietInfo());
    }
}