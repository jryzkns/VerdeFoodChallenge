package allinmain.cmpt276.verdefoodchallenge;

import java.util.HashMap;
import java.util.Map;

public class CO2e {

  private HashMap<String,Double> foodTable;
  private HashMap<String,Double> userCurrentDiet;

  private Double userCO2e;

  CO2e(HashMap<String,Double> userData, HashMap<String,Double> FoodData){

    userCurrentDiet = new HashMap<>(userData);
    foodTable = new HashMap<>(FoodData);

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

  public Double getUserCO2e(){
    return userCO2e;
  }

  public HashMap<String,Double> suggestDietChange(String policy){
    //TODO: suggestion algorithm for minimizing CO2e output;
    //outputs a hashmap of ***changes*** that should be made:
    //-have the ability to introduce new foods
    //-give negative values to foods that one should eat less
    //-give positive values to foods that one should eat more
    //-give 0 to foods that one should stay having the same amount

    HashMap<String,Double> suggestion = new HashMap<>();

    switch (policy){
      case "": // different suggestions depending on the policy
    }

    return suggestion;

  }
}
