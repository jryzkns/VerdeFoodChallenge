package allinmain.cmpt276.verdefoodchallenge;

import android.content.Context;

import org.junit.Test;

import static org.junit.Assert.*;

public class GridViewAdapterTest {
    private DataCenter DC_testing =DataCenter.getInstance();
    private Context mContext;
    GridViewAdapter testing = new GridViewAdapter(mContext);


    @Test
    public void getCount() {
        //Since getInstance applied before, the getCount function is non-empty
        //assert the value is non-0
        assertNotEquals(0,DC_testing.getFoodsSize());

    }

    @Test
    public void getItem() {
        //number will return number of variable in return
        //assert several item <-- need check with teammate
        for (int i = 0 ; i < testing.getCount(); i ++)
            assertEquals(DC_testing.getFood(i),testing.getItem(i));
    }

    @Test
    public void getItemId() {
        //Item ID must same as the integer input
        for (int i = 0 ; i <DC_testing.getFoodsSize();i++) {
            long k = i;
            assertEquals(k,testing.getItemId(i));
        }
    }

}