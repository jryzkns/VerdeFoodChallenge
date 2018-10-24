package allinmain.cmpt276.verdefoodchallenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class DataCenter {
    private static DataCenter mDataCenter=null;
    private ArrayList<Food> mFoodLst;
    private HashMap<Integer,Float> dietInfo;

    private HashMap<Integer,Float> suggestion;

    // number taken from:
    // https://en.wikipedia.org/wiki/Metro_Vancouver_Regional_District#Demographics
    private static final int metroVanPopln = 2463431;

    private Random picker; // for helping with picking food choices randomly

    private HashSet meats;
    private HashSet altProtein;
    private HashSet vegetables;

    private DataCenter()
    {

        init();
    }
    public static DataCenter getInstance()
    {
        if(mDataCenter==null){
            mDataCenter=new DataCenter();
        }
    return mDataCenter;
    }

    private void init()
    {
        mFoodLst=new ArrayList<Food>();
        dietInfo=new  HashMap<>();
        suggestion=new HashMap<>();

        mFoodLst.add(new Food("Beef",R.mipmap.beef,27f,""));
        mFoodLst.add(new Food("Lentils",R.mipmap.biandou,0.9f,""));
        mFoodLst.add(new Food("Cheese",R.mipmap.cheese,13.5f,""));
        mFoodLst.add(new Food("Chicken",R.mipmap.chickens,6.9f,""));
        mFoodLst.add(new Food("Tofu",R.mipmap.tofu,2f,""));
        mFoodLst.add(new Food("Eggs",R.mipmap.egg,4.8f,""));
        mFoodLst.add(new Food("Tuna",R.mipmap.tunafish,6.1f,""));
        mFoodLst.add(new Food("Dry Beans",R.mipmap.gandou,2f,""));
        mFoodLst.add(new Food("PeanutButter",R.mipmap.huashengjiang,2.5f,""));
        mFoodLst.add(new Food("Turkey",R.mipmap.huoji,10.9f,""));
        mFoodLst.add(new Food("Lamb",R.mipmap.lamb,39.2f,""));
        mFoodLst.add(new Food("Milk",R.mipmap.milk,1.9f,""));
        mFoodLst.add(new Food("Nuts",R.mipmap.nuts,2.3f,""));
        mFoodLst.add(new Food("Pork",R.mipmap.pork,12.1f,""));
        mFoodLst.add(new Food("Potato",R.mipmap.potato,2.9f,""));
        mFoodLst.add(new Food("Rice",R.mipmap.rice,2.7f,""));
        mFoodLst.add(new Food("Salmon",R.mipmap.salmon,11.9f,""));
        mFoodLst.add(new Food("Yogurt",R.mipmap.suannai,2.2f,""));
        mFoodLst.add(new Food("Tomato",R.mipmap.tomato,1.1f,""));
        mFoodLst.add(new Food("Broccoli",R.mipmap.xilanhua,2f,""));

        picker = new Random();
        meats = new HashSet<>(Arrays.asList(  "LAMB", "BEEF", "PORK", "FARMED SALMON",
                "TURKEY", "CHICKEN", "CANNED TUNA"));
        altProtein = new HashSet<>(Arrays.asList( "NUTS","DRIED BEANS","LENTILS",
                "TOFU","PEANUT BUTTER"));
        vegetables = new HashSet<>(Arrays.asList("BROCCOLI","TOMATO"));

    }

    public int getFoodsSize()
    {
        return mFoodLst.size();
    }
    public Food getFood(int foodid)
    {
        return mFoodLst.get(foodid);
    }
    public void addDietItem(int foodid,float kg)
    {
        dietInfo.put(foodid,kg);
    }
    public float getDietItem(int foodid)
    {
       if(dietInfo.containsKey(foodid))
       {
           return dietInfo.get(foodid);
       }
       return -1f;

    }
    public void resetDiet()
    {
        dietInfo.clear();
        suggestion.clear();
    }

    //try to merge these two functions so it's more abstractable;
    //we running short on time here

    public float calDietCo2()
    {
        float result=0;
        for(Entry<Integer,Float> item:dietInfo.entrySet())
        {
            result+=mFoodLst.get(item.getKey()).getCo2()*item.getValue();
        }

        return result;
    }

    //As per requirement 3.1.5a
    public float calSuggestCo2()
    {
        float result=0;
        for(Entry<Integer,Float> item:suggestion.entrySet())
        {
            result+=mFoodLst.get(item.getKey()).getCo2()*item.getValue();
        }

        return result;
    }
    public String getDietInfo()
    {
        String result="";

//        potentially getting rid of this in the end product
        for(Entry<Integer,Float> item:dietInfo.entrySet())
        {
            result+=mFoodLst.get(item.getKey()).getName()+":"+item.getValue()+" kg/wk\n";
        }

        result+="With your current diet, you will generate around ";
        result+=(int)calDietCo2()*52;
        result+="kg of CO2e in a year";

        return result;
    }


    public String getSuggestionInfo(){

        String result="";

//        potentially getting rid of this in the end product
        for(Entry<Integer,Float> item:suggestion.entrySet())
        {
            result+=mFoodLst.get(item.getKey()).getName()+":"+item.getValue()+" kg/wk\n";
        }

        result+="With a change in diet, you could be saving ";
        result+=(int)calSuggestCo2()*52;
        result+="kg of CO2e in a year\n\n";
        result+="The Metro Vancouver area can collectively save ";
        result+=(int)calculateMetroVanSavings();
        result+="kg of CO2e in a year if everyone took this change in diet!";
        return result;
    }

    //As per requirement 3.1.5b
    public HashMap<Integer, Float> makeDietChangeTable(String policy){

        HashMap<Integer,Float> suggestion = new HashMap<>();

        switch (policy.toUpperCase()){
            case "CARNIVORE":

                // general idea is to just eat less of all the meats and have more chicken instead
                // KFC for life
//
                for(Map.Entry<Integer,Float> food_item : dietInfo.entrySet()){

                    Integer item_id = food_item.getKey();
                    Float currentConsumeValue = food_item.getValue();

//                    current use: mFoodLst.get(item.getKey()).getCo2()
//
//                    if ( item_name != "CHICKEN" && meats.contains(item_name) ){
//
//                        if (!suggestion.containsKey(item_name)){
//                            suggestion.put(item_name,-currentConsumeValue*0.3);
//                        }
//
//                        if (!suggestion.containsKey("CHICKEN")) {
//                            suggestion.put("CHICKEN",currentConsumeValue*0.3);
//                        } else {
//                            suggestion.put("CHICKEN",suggestion.get("CHICKEN")+currentConsumeValue*0.3);
//                        }
//
//                    }
//
                }

            case "LESSMEAT":

                //if it's meat, let's switch some of it to beans and tofu
//
//                for(Map.Entry<Integer,Float> food_item : dietInfo.entrySet()){
//                    String item_name = food_item.getKey();
//                    Double currentConsumeValue = food_item.getValue();
//
//                    if (meats.contains(item_name)){
//
//                        Iterator<String> altProts= altProtein.iterator();
//                        for (int i = 0; i < picker.nextInt(altProtein.size()) - 1; i++){
//                            altProts.next();
//                        }
//
//                        String chosenAltProt = altProts.next();
//
//                        if (!suggestion.containsKey(item_name)) {
//                            suggestion.put(item_name, -currentConsumeValue * 0.3);
//                        }
//
//                        if (!suggestion.containsKey(chosenAltProt)) {
//                            suggestion.put(chosenAltProt,currentConsumeValue*0.3);
//                        } else {
//                            suggestion.put(chosenAltProt,suggestion.get(chosenAltProt)+currentConsumeValue*0.3);
//                        }
//
//                    }
//
//                }

            case "VEGETARIAN":
//
//                // LESSMEAT policy but more severe and encourages eggs
//
//                for(Map.Entry<Integer,Float> food_item : dietInfo.entrySet()){
//                    String item_name = food_item.getKey();
//                    Double currentConsumeValue = food_item.getValue();
//
//                    if (meats.contains(item_name)){
//
//                        HashSet<String> altsWithEggs = altProtein;
//                        altsWithEggs.add("EGGS");
//                        Iterator<String> altProts= altsWithEggs.iterator();
//                        for (int i = 0; i < picker.nextInt(altProtein.size()+1) - 1; i++){
//                            altProts.next();
//                        }
//
//                        String chosenAltProt = altProts.next();
//
//                        if (!suggestion.containsKey(item_name)) {
//                            suggestion.put(item_name, -currentConsumeValue);
//                        }
//
//                        if (!suggestion.containsKey(chosenAltProt)) {
//                            suggestion.put(chosenAltProt,currentConsumeValue*0.7);
//                        } else {
//                            suggestion.put(chosenAltProt,suggestion.get(chosenAltProt)+currentConsumeValue*0.3);
//                        }
//
//                    }
//
//                }

            case "VEGAN":

//                // policy "VEGETARIAN" but with no eggs
//
//                for(Map.Entry<Integer,Float> food_item : dietInfo.entrySet()){
//                    String item_name = food_item.getKey();
//                    Double currentConsumeValue = food_item.getValue();
//
//                    if (meats.contains(item_name)){
//
//                        Iterator<String> altProts= altProtein.iterator();
//                        for (int i = 0; i < picker.nextInt(altProtein.size()) - 1; i++){
//                            altProts.next();
//                        }
//
//                        String chosenAltProt = altProts.next();
//
//                        if (!suggestion.containsKey(item_name)) {
//                            suggestion.put(item_name, -currentConsumeValue);
//                        }
//
//                        if (!suggestion.containsKey(chosenAltProt)) {
//                            suggestion.put(chosenAltProt,currentConsumeValue);
//                        } else {
//                            suggestion.put(chosenAltProt,suggestion.get(chosenAltProt)+currentConsumeValue*0.3);
//                        }
//
//                    } else if (item_name == "EGGS"){
//
//                        if (!suggestion.containsKey("EGGS")) {
//                            suggestion.put("EEGS", -currentConsumeValue);
//                        }
//
//                    }
//
//                }

                //other ideas?
        }

        return suggestion;

    }

    //As per requirement 3.1.6a
    public float calculateMetroVanSavings(){ return metroVanPopln*calSuggestCo2();}


}
