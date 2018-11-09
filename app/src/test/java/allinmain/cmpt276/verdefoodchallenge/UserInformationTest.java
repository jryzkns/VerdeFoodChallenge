package allinmain.cmpt276.verdefoodchallenge;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class UserInformationTest {

    private String NAME="Jim";
    private String LOCATION="Burnaby";
    private String ID="0000000";
    private String PLEDGEAMOUNT="testing";
    //set the personal information
    UserInformation testing=new UserInformation(NAME,LOCATION,ID,PLEDGEAMOUNT);

    @Test
    public void getNAME() {
        assertEquals("Jim",testing.getNAME());
    }

    @Test
    public void getLOCATION() {
        assertEquals("Burnaby",testing.getLOCATION());
    }

    @Test
    public void getID() {
        assertEquals("0000000",testing.getID());
    }

    @Test
    public void getPLEDGEAMOUNT() {
        assertEquals("testing",testing.getPLEDGEAMOUNT());
    }

    @Test
    public void setNAME() {
        String Name="Tester";
        testing.setNAME(Name);
        assertEquals(Name,testing.getNAME());
    }

    @Test
    public void setLOCATION() {
        String location="Vancouver";
        testing.setLOCATION(location);
        assertEquals(location,testing.getLOCATION());
    }

    @Test
    public void setID() {
        String ID="1111111111";
        testing.setID(ID);
        assertEquals(ID,testing.getID());
    }

    @Test
    public void setPLEDGEAMOUNT() {
        String P="TESTING";
        testing.setPLEDGEAMOUNT(P);
        assertEquals(P,testing.getPLEDGEAMOUNT());
    }
}