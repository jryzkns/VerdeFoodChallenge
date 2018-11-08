package allinmain.cmpt276.verdefoodchallenge;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

public class DataCenterTest {
    private static DataCenter testing=DataCenter.getInstance();;
    private ArrayList<Food> mFoodLst;
    private HashMap<Integer,Float> dietInfo;

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
    public void getDietItem() {
        //Since we didn't assume the adding Diet Item
        //The default is non-0f answer.
        assertNotEquals(0f,testing.getDietItem(0));
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