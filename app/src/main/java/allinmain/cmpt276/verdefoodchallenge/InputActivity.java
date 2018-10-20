package allinmain.cmpt276.verdefoodchallenge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;


public class InputActivity extends AppCompatActivity {

    ArrayList<String> FoodNameString = new ArrayList<>();
    //FoodImgList
    int FoodStringIndex=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
    }

    //update food name and food picture base on FoodStringIndex
    private void update(){
        TextView foodName = findViewById(R.id.foodName);
        ImageView foodImg = findViewById(R.id.foodImg);
        SeekBar inputSeekBar= findViewById(R.id.input_value);
        //change foodImg to correct picture
        foodName.setText(FoodNameString.get(FoodStringIndex));
        inputSeekBar.setProgress(0);
    }

    //run when the first time hits button "next"
    public void start(){
        FoodNameString.add(0,"Lamb");
        FoodNameString.add(1,"Beef");
        FoodNameString.add(2,"Cheese");
        FoodNameString.add(3,"Pork");
        FoodNameString.add(4,"Farmed Salmon");
        FoodNameString.add(5,"Turkey");
        FoodNameString.add(6,"Chicken");
        FoodNameString.add(7,"Canned Tuna");
        FoodNameString.add(8,"Eggs");
        FoodNameString.add(8,"Potatoes");
        FoodNameString.add(10,"Rice");
        FoodNameString.add(11,"Peanut Butter");
        FoodNameString.add(12,"Nuts");
        FoodNameString.add(13,"Yogurt");
        FoodNameString.add(14,"Brococoli");
        FoodNameString.add(15,"Tofu");
        FoodNameString.add(16,"Dry Beans");
        FoodNameString.add(17,"Milk(2%)");
        FoodNameString.add(18,"Tomatoes");
        FoodNameString.add(19,"Lentils");
        //add FoodImgList

    }

    //  button next
    public void next(View view){
        if(FoodStringIndex==0){start();}

        SeekBar inputSeekBar= findViewById(R.id.input_value);
        /*
           write code that pass user input [FoodNameString.get(FoodStringIndex),inputSeekBar.getProgress()]
           覆盖
         */
        FoodStringIndex++;
        update();
    }

    //button back
    public void back(View view){
        if(FoodStringIndex==0){
            //go to beginning activity
        }else{
            FoodStringIndex--;
            update();
        }
    }


}
