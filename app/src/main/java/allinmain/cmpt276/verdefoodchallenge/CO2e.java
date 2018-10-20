package allinmain.cmpt276.verdefoodchallenge;

import java.util.HashMap;
import java.util.Map;

public class CO2e {

  private HashMap<String,Double> foodTable;
  private HashMap<String,Double> userCurrentDiet;
  private HashMap<String,Double> suggestion;

  private Double userCO2e;

  // number taken from:
  // https://en.wikipedia.org/wiki/Metro_Vancouver_Regional_District#Demographics
  private static final int metroVanPopln = 2463431;

  CO2e(HashMap<String,Double> userData){

    // values in userData SHOULD be in yearly units!!!!!!!!
    userCurrentDiet = new HashMap<>(userData);
    suggestion = new HashMap<>();
    foodTable = new HashMap<>();

    //might do file i/o someday
    foodTable.put("LAMB",39.2);
    foodTable.put("BEEF",27.0);
    foodTable.put("CHEESE",13.5);
    foodTable.put("PORK",12.1);
    foodTable.put("FARMED SALMON",11.9);
    foodTable.put("TURKEY",10.9);
    foodTable.put("CHICKEN",6.9);
    foodTable.put("CANNED TUNA",6.1);
    foodTable.put("EGGS",4.8);
    foodTable.put("POTATOES",2.9);
    foodTable.put("RICE",2.7);
    foodTable.put("PEANUT BUTTER",2.5);
    foodTable.put("NUTS",2.3);
    foodTable.put("YOGURT",2.2);
    foodTable.put("BROCCOLI",2.0);
    foodTable.put("TOFU",2.0);
    foodTable.put("DRY BEANS",2.0);
    foodTable.put("MILK 2%",1.9);
    foodTable.put("TOMATO",1.1);
    foodTable.put("LENTILS",0.9);

    //set to NaN so we have a sentinel value for catching errors/exceptions
    userCO2e = Double.NaN;

  }

  //As Per 3.1.4a; used both for user data and suggestion data
  public Double calculate(HashMap<String,Double> in_table){

    Double consumerCO2e = 0d;

    if (in_table.size() == 0){

      consumerCO2e = Double.NaN;

    } else {

      for (Map.Entry<String,Double> food_item : in_table.entrySet()){

        String item_name = food_item.getKey();
        Double consume_value = food_item.getValue();

        if (foodTable.containsKey(item_name)){
          consumerCO2e += consume_value*foodTable.get(item_name);
        }

      }

    }

    return consumerCO2e;

  }

  //As per requirement 3.1.5b
  public HashMap<String,Double> makeDietChangeTable(String policy){
    //TODO: suggestion algorithm for minimizing CO2e output;
    //outputs a hashmap of ***changes*** that should be made:
    //-have the ability to introduce new foods
    //-give negative values to foods that one should eat less
    //-give positive values to foods that one should eat more
    //-give 0 to foods that one should stay having the same amount

    HashMap<String,Double> suggestion = new HashMap<>();

    switch (policy.toUpperCase()){
      case "CARNIVORE":
      case "LESSMEAT":
      case "VEGETARIAN":
      case "VEGAN":
      //other ideas
    }

    return suggestion;

  }

  //As per requirement 3.1.5a
  public Double calculateDietSavings(){ return calculate(suggestion); }

  //As per requirement 3.1.6a
  public Double calculateMetroVanSavings(){ return metroVanPopln*calculateDietSavings();}

  //DEFAULT GETTERS AND SETTERS

  public Double getUserCO2e(){
    return userCO2e;
  }
  public void setUserCO2e(Double userCO2e) {
    this.userCO2e = userCO2e;
  }


  public HashMap<String, Double> getUserCurrentDiet() {
    return userCurrentDiet;
  }
  public void setUserCurrentDiet(HashMap<String, Double> userCurrentDiet) {
    // is this a shallow copy? do we only need a shallow copy?
    this.userCurrentDiet = userCurrentDiet;
  }

  public HashMap<String, Double> getSuggestion() {
    return suggestion;
  }
  public void setSuggestion(HashMap<String, Double> suggestion) {
    this.suggestion = suggestion;
  }


}
