package allinmain.cmpt276.verdefoodchallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class FoodTest {
    private String name="Jim";
    private int resid=1;
    private float co2=3f;
    private String info="k";
    Food testing=new Food( name, resid, co2, info);
    @Test
    public void getName() {
        assertEquals("Jim",testing.getName());
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