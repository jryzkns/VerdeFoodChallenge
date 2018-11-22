package allinmain.cmpt276.verdefoodchallenge;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.widget.TextView;
import android.widget.Toast;

import java.io.*;
import java.lang.*;

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
    public ArrayList<Food> mFoodLst;
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

        //DO NOT SWAP THESE ROWS!
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
        meats = new HashSet<>(Arrays.asList(0,3,9,10,13,16));
        altProtein = new HashSet<>(Arrays.asList(12,7,1,4,8));
        vegetables = new HashSet<>(Arrays.asList(18,19));

    }
    //Basic function
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
    public void delDietItem(int foodid){dietInfo.remove(foodid);}
    public float getDietItem(int foodid)
    {
       if(dietInfo.containsKey(foodid))
       {
           return dietInfo.get(foodid);
       }
       return -1f;

    }
    //Reset data
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
    //Calculate suggestion
    public float calSuggestCo2()
    {
        float result=0;
        for(Entry<Integer,Float> item:suggestion.entrySet())
        {
            result+=mFoodLst.get(item.getKey()).getCo2()*item.getValue();
        }

        return result;
    }
    //Get data from Hash table
    public String getDietInfo()
    {
        String result="";

        //potentially getting rid of this in the end product
        for(Entry<Integer,Float> item:dietInfo.entrySet())
        {
            result+=mFoodLst.get(item.getKey()).getName()+" :"+item.getValue()+" kg/wk\n";
        }

        result+="With your current diet, you could be generating ";
        result+=(calDietCo2()*52)/1000;
        result+=" tonne of CO2e in a year, perhaps you can do better!";

        return result;
    }

    //Get Suggestion Information
    public String getSuggestionInfo(String Policy){

        suggestion = makeDietChangeTable(Policy);
        String result = "";

        if (suggestion.isEmpty()){
            result += "We currently don't have any suggestions for you right now, check back another time!";
        }else {

            result += "You have chosen the ";
            result += Policy;
            result += " strategy\n";
            result += "Here is a suggestion, try making this change to your diet:\n\n";

            //potentially getting rid of this in the end product
            for (Entry<Integer, Float> item : suggestion.entrySet()) {

                if (item.getValue() > 0){
                    result += "Have more ";
                } else {
                    result += "Have less ";
                }

                result += mFoodLst.get(item.getKey()).getName();

                result += " by ";

                result += Math.abs(item.getValue());

                result += " kg/wk\n";



            }

            result += "\n\nWith this change in diet, you could be saving ";
            result += -1 * ( calSuggestCo2() / 1000 * 52);
            result += " tonnes of CO2e in a year, that's like ";
            result += -1 * ( calSuggestCo2() / 1000 * 52 / 6);
            result += " elephants!\n\n";

            if(calculateMetroVanSavings() < 0) {

                result += "The Metro Vancouver area can collectively save ";
                result += -1 * (calculateMetroVanSavings() * 52 / 1000);
                result += " tonne of CO2e in a year if everyone took this same change in diet!";
            }
        }
        return result;
    }

    //As per requirement 3.1.5b
    public HashMap<Integer, Float> makeDietChangeTable(String policy){

        HashMap<Integer,Float> suggestion = new HashMap<>();

        switch (policy.toUpperCase()){
            case "CARNIVORE":

                // general idea is to just eat less of all the meats and have more chicken instead
                // KFC for life

                for(Map.Entry<Integer,Float> food_item : dietInfo.entrySet()){

                    Integer item_id = food_item.getKey();
                    Float currentConsumeValue = food_item.getValue();

                    if (item_id != 3 && meats.contains(item_id)){
                        if (!suggestion.containsKey(item_id)){
                            suggestion.put(item_id, (float) (-currentConsumeValue*0.3));
                        }

                        if (!suggestion.containsKey(3)){
                            suggestion.put(3, (float) (currentConsumeValue*0.3));
                        } else {
                            suggestion.put(3, (float) (suggestion.get(3)+currentConsumeValue*0.3));
                        }
                    }
                }

            case "LESSMEAT":

                //if it's meat, let's switch some of it to beans and tofu

                for(Map.Entry<Integer,Float> food_item : dietInfo.entrySet()) {

                    Integer item_id = food_item.getKey();
                    Float currentConsumeValue = food_item.getValue();

                    if (meats.contains(item_id)){
                        Iterator<Integer> altProts = altProtein.iterator();

                        for (int i = 0; i < picker.nextInt(altProtein.size()) - 1; i++){
                            altProts.next();
                        }

                        int chosenAltProt = altProts.next();

                        if (!suggestion.containsKey(item_id)){
                            suggestion.put(item_id, (float) (-currentConsumeValue*0.3));
                        }

                        if (!suggestion.containsKey(chosenAltProt)) {
                            suggestion.put(chosenAltProt, (float) (currentConsumeValue*0.3));
                        } else {
                            suggestion.put(chosenAltProt, (float) (suggestion.get(chosenAltProt)+currentConsumeValue*0.3));
                        }

                    }
                }

            case "VEGETARIAN":

                // LESSMEAT policy but more severe and encourages eggs

                for(Map.Entry<Integer,Float> food_item : dietInfo.entrySet()){
                    Integer item_id = food_item.getKey();
                    Float currentConsumeValue = food_item.getValue();

                    if (meats.contains(item_id)){

                        HashSet<Integer> altsWithEggs = altProtein;
                        altsWithEggs.add(5);
                        Iterator<Integer> altProts= altsWithEggs.iterator();
                        for (int i = 0; i < picker.nextInt(altProtein.size()+1) - 1; i++){
                            altProts.next();
                        }

                        Integer chosenAltProt = altProts.next();

                        if (!suggestion.containsKey(item_id)) {
                            suggestion.put(item_id, -currentConsumeValue);
                        }

                        if (!suggestion.containsKey(chosenAltProt)) {
                            suggestion.put(chosenAltProt, (float) (currentConsumeValue*0.7));
                        } else {
                            suggestion.put(chosenAltProt, (float) (suggestion.get(chosenAltProt)+currentConsumeValue*0.3));
                        }

                    }

                }

            case "VEGAN":

                // policy "VEGETARIAN" but with no eggs

                for(Map.Entry<Integer,Float> food_item : dietInfo.entrySet()){
                    Integer item_id = food_item.getKey();
                    Float currentConsumeValue = food_item.getValue();

                    if (meats.contains(item_id)){

                        HashSet<Integer> altsWithEggs = altProtein;
                        altsWithEggs.add(5);
                        Iterator<Integer> altProts= altsWithEggs.iterator();
                        for (int i = 0; i < picker.nextInt(altProtein.size()+1) - 1; i++){
                            altProts.next();
                        }

                        Integer chosenAltProt = altProts.next();

                        if (!suggestion.containsKey(item_id)) {
                            suggestion.put(item_id, -currentConsumeValue);
                        }

                        if (!suggestion.containsKey(chosenAltProt)) {
                            suggestion.put(chosenAltProt, (float) (currentConsumeValue*0.7));
                        } else {
                            suggestion.put(chosenAltProt, (float) (suggestion.get(chosenAltProt)+currentConsumeValue*0.3));
                        }

                    } else if (item_id == 5){

                        if (!suggestion.containsKey(5)) {
                            suggestion.put(5, -currentConsumeValue);
                        }

                    }

                }

        }

        return suggestion;

    }

    //As per requirement 3.1.6a
    public float calculateMetroVanSavings(){ return metroVanPopln*calSuggestCo2();}


}
