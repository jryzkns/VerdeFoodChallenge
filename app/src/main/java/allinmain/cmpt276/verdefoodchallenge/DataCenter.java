package allinmain.cmpt276.verdefoodchallenge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
public class DataCenter {
    private static DataCenter mDataCenter=null;
    private ArrayList<Food> mFoodLst;
    private HashMap<Integer,Float> dietInfo;
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
        dietInfo=new  HashMap<Integer, Float> ();

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
    }
    public float calDietCo2()
    {
        float result=0;
        for(Entry<Integer,Float> item:dietInfo.entrySet())
        {
            result+=mFoodLst.get(item.getKey()).getCo2()*item.getValue();
        }

        return result;
    }
    public String getDietInfo()
    {
        String result="";

        for(Entry<Integer,Float> item:dietInfo.entrySet())
        {

            result+=mFoodLst.get(item.getKey()).getName()+":"+item.getValue()+" kg/week\n";
        }
        result+="\nYou will cost :"+calDietCo2()*52+"kg Co2 in one year";
        return result;
    }

}
