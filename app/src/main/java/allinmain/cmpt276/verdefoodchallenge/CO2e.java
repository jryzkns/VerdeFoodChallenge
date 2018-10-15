package allinmain.cmpt276.verdefoodchallenge;

import java.util.HashMap;
import java.util.Map;

public class CO2e {

  private HashMap<String,Double> foodTable;
  private HashMap<String,Double> userCurrentDiet;

  private Double userCO2e;


  CO2e(HashMap<String,Double> userData, HashMap<String,Double> FoodData){

    foodTable = new HashMap<>(FoodData);
    userCurrentDiet = new HashMap<>(userData);

    //set to NaN so we have a sentinel value for catching errors/exceptions
    userCO2e = Double.NaN;
  }

  public void calculate(){

    if (userCurrentDiet.size() == 0){
      return;
    } else {
      userCO2e = 0d;
    }

    for (Map.Entry<String,Double> food_item : userCurrentDiet.entrySet()){

      String item_name = food_item.getKey();
      Double consume_value = food_item.getValue();

      if (foodTable.containsKey(item_name)){
        userCO2e += consume_value*foodTable.get(item_name);
      }

    }

  }

  //default getter
  public Double getUserCO2e(){

    return userCO2e;

  }
}
