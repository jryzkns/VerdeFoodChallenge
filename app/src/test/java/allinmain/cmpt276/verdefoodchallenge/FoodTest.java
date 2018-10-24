package allinmain.cmpt276.verdefoodchallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class FoodTest {
    /*
    Set a Testing Food object
    check each getting function return correctly
    */
    private String name="Test";
    private int resid=1;
    private float co2=3f;
    private String info="k";
    Food testing=new Food( name, resid, co2, info);
    @Test
    public void getName() {
        assertEquals("Test",testing.getName());
    }

    @Test
    public void getResid() {
        assertEquals(1,testing.getResid());
    }

    @Test
    public void getCo2() {
        assertNotEquals(0f,testing.getCo2());
    }

    @Test
    public void getInfo() {
        assertEquals("k",testing.getInfo());
    }
}