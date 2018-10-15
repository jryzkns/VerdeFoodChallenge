package allinmain.cmpt276.verdefoodchallenge;

import static org.junit.Assert.*;

import java.util.HashMap;
import org.junit.Test;

public class CO2eTest {

  @Test
  public void userCO2estartsoutasNaN(){
    CO2e bareobject = new CO2e(new HashMap<String, Double>(),new HashMap<String, Double>());
    assertEquals(Double.NaN,bareobject.getUserCO2e(),0);
  }

  @Test
  public void calculationAlgorithmWorksOnEmptyData(){

    CO2e emptytest = new CO2e(new HashMap<String, Double>(),new HashMap<String, Double>());

    emptytest.calculate();
    double result = emptytest.getUserCO2e();

    assertEquals(Double.NaN, result,0);

  }

  @Test
  public void calculatedAlgorithmWorksNormally(){

    HashMap<String,Double> ref_values = new HashMap<>();
    HashMap<String,Double> user_values = new HashMap<>();

    ref_values.put("test1",1d);
    ref_values.put("test2",1d);
    ref_values.put("test3",1d);
    ref_values.put("test4",1d);
    ref_values.put("test5",1d);

    user_values.put("test1",1d);
    user_values.put("test3",1d);
    user_values.put("test6",1d);

    CO2e run = new CO2e(ref_values,user_values);

    run.calculate();

    assertEquals(2d,run.getUserCO2e(),0);
  }

}