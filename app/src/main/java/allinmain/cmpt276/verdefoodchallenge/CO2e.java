package allinmain.cmpt276.verdefoodchallenge;

import java.util.HashMap;

public class CO2e {

  private HashMap<String,Double> foodTable;
  private HashMap<String,Double> userCurrentDiet;

  //set to NaN so we have a sentinel value for catching errors/exceptions
  private Double userCO2e = Double.NaN;


  CO2e(/*maybe take in a hashmap here, i don't know*/){
    // TODO: read datafile and/or fill the foodTable with official data
    // TODO: take in userinput.export(), write into userCurrentDiet
  }

  private Double calculate(){
    // TODO: improvise Algorithm
    // current idea: cycle through the user table
  }

  public Double getUserCO2e(){
    return userCO2e;
  }
}
