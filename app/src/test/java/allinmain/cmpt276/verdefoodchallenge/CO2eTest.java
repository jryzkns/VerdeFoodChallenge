package allinmain.cmpt276.verdefoodchallenge;

import static org.junit.Assert.*;

import java.util.HashMap;
import org.junit.Test;

public class CO2eTest {

  @Test
  public void userCO2estartsoutasNaN(){
    CO2e bareobject = new CO2e(new HashMap<String, Double>());
    assertEquals(Double.NaN,bareobject.getUserCO2e(),0);
  }

  @Test
  public void calculationAlgorithmWorksOnEmptyData(){

    CO2e emptytest = new CO2e(new HashMap<String, Double>());

    double result = emptytest.calculate(emptytest.getUserCurrentDiet());

    assertEquals(Double.NaN, result,0);

  }

  @Test
  public void calculatedAlgorithmWorksNormally(){

    HashMap<String,Double> user_values = new HashMap<>();

    user_values.put("LENTILS",1d);

    CO2e run = new CO2e(user_values);

    run.setUserCO2e(run.calculate(user_values));

    assertEquals(0.9,run.getUserCO2e(),0);
  }

}