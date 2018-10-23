package allinmain.cmpt276.verdefoodchallenge;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class CO2e {

  private HashMap<String,Double> foodTable;
  private HashMap<String,Double> userCurrentDiet;
  private HashMap<String,Double> suggestion;

  private Double userCO2e;

  // number taken from:
  // https://en.wikipedia.org/wiki/Metro_Vancouver_Regional_District#Demographics
  private static final int metroVanPopln = 2463431;

  private Random picker; // for helping with picking food choices randomly

  private HashSet meats;
  private HashSet altProtein;
  private HashSet vegetables;

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

    picker = new Random();
    meats = new HashSet<>(Arrays.asList(  "LAMB", "BEEF", "PORK", "FARMED SALMON",
                                          "TURKEY", "CHICKEN", "CANNED TUNA"));
    altProtein = new HashSet<>(Arrays.asList( "NUTS","DRIED BEANS","LENTILS",
                                              "TOFU","PEANUT BUTTER"));
    vegetables = new HashSet<>(Arrays.asList("BROCCOLI","TOMATO"));

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

        // general idea is to just eat less of all the meats and have more chicken instead
        // KFC for life

        for(Map.Entry<String,Double> food_item : userCurrentDiet.entrySet()){
          String item_name = food_item.getKey();
          Double currentConsumeValue = food_item.getValue();

            if ( item_name != "CHICKEN" && meats.contains(item_name) ){

              if (!suggestion.containsKey(item_name)){
                suggestion.put(item_name,-currentConsumeValue*0.3);
              }

              if (!suggestion.containsKey("CHICKEN")) {
                suggestion.put("CHICKEN",currentConsumeValue*0.3);
              } else {
                suggestion.put("CHICKEN",suggestion.get("CHICKEN")+currentConsumeValue*0.3);
              }

            }

        }

      case "LESSMEAT":

        //if it's meat, let's switch some of it to beans and tofu

        for(Map.Entry<String,Double> food_item : userCurrentDiet.entrySet()){
          String item_name = food_item.getKey();
          Double currentConsumeValue = food_item.getValue();

          if (meats.contains(item_name)){

            Iterator<String> altProts= altProtein.iterator();
            for (int i = 0; i < picker.nextInt(altProtein.size()) - 1; i++){
              altProts.next();
            }

            String chosenAltProt = altProts.next();

            if (!suggestion.containsKey(item_name)) {
              suggestion.put(item_name, -currentConsumeValue * 0.3);
            }

            if (!suggestion.containsKey(chosenAltProt)) {
              suggestion.put(chosenAltProt,currentConsumeValue*0.3);
            } else {
              suggestion.put(chosenAltProt,suggestion.get(chosenAltProt)+currentConsumeValue*0.3);
            }

          }

        }

      case "VEGETARIAN":

        // LESSMEAT policy but more severe and encourages eggs

        for(Map.Entry<String,Double> food_item : userCurrentDiet.entrySet()){
          String item_name = food_item.getKey();
          Double currentConsumeValue = food_item.getValue();

          if (meats.contains(item_name)){

            HashSet<String> altsWithEggs = altProtein;
            altsWithEggs.add("EGGS");
            Iterator<String> altProts= altsWithEggs.iterator();
            for (int i = 0; i < picker.nextInt(altProtein.size()+1) - 1; i++){
              altProts.next();
            }

            String chosenAltProt = altProts.next();

            if (!suggestion.containsKey(item_name)) {
              suggestion.put(item_name, -currentConsumeValue);
            }

            if (!suggestion.containsKey(chosenAltProt)) {
              suggestion.put(chosenAltProt,currentConsumeValue*0.7);
            } else {
              suggestion.put(chosenAltProt,suggestion.get(chosenAltProt)+currentConsumeValue*0.3);
            }

          }

        }

      case "VEGAN":

        // policy "VEGETARIAN" but with no eggs

        for(Map.Entry<String,Double> food_item : userCurrentDiet.entrySet()){
          String item_name = food_item.getKey();
          Double currentConsumeValue = food_item.getValue();

          if (meats.contains(item_name)){

            Iterator<String> altProts= altProtein.iterator();
            for (int i = 0; i < picker.nextInt(altProtein.size()) - 1; i++){
              altProts.next();
            }

            String chosenAltProt = altProts.next();

            if (!suggestion.containsKey(item_name)) {
              suggestion.put(item_name, -currentConsumeValue);
            }

            if (!suggestion.containsKey(chosenAltProt)) {
              suggestion.put(chosenAltProt,currentConsumeValue);
            } else {
              suggestion.put(chosenAltProt,suggestion.get(chosenAltProt)+currentConsumeValue*0.3);
            }

          } else if (item_name == "EGGS"){

            if (!suggestion.containsKey("EGGS")) {
              suggestion.put("EEGS", -currentConsumeValue);
            }

          }

        }

      //other ideas?
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
