package allinmain.cmpt276.verdefoodchallenge;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;


public class InputActivity extends Activity {

    //private ArrayList<String> FoodNameString = new ArrayList<>();

    //private int[] FoodPicList = new int[20];
    private int FoodStringIndex;
    private SeekBar skBar;
    private SeekBar MuBar;

        private DataCenter dc=DataCenter.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);


        Intent intent= this.getIntent();

        FoodStringIndex=intent.getIntExtra("foodid",-1);;
        //start();
        skBar = findViewById(R.id.input_value);
        MuBar = findViewById(R.id.menu);
        skBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                TextView sb_process = findViewById(R.id.skprocess);
                sb_process.setText(""+0.5*(float)seekBar.getProgress());
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                input(seekBar.getProgress());
                update();
            }
        });

        MuBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                FoodStringIndex=seekBar.getProgress();
                update();
            }
        });
        update();
    }

    //update food name and food picture base on FoodStringIndex
    private void update() {
        if (FoodStringIndex > 19) {
            FoodStringIndex = 19;
            // exit activity
            InputActivity.this.finish();
            return;
        }
        TextView foodName = findViewById(R.id.foodName);
        ImageView foodImg = findViewById(R.id.foodImg);
        SeekBar inputSeekBar = findViewById(R.id.input_value);
        foodImg.setImageResource(dc.getFood(FoodStringIndex).getResid());
        foodName.setText(dc.getFood(FoodStringIndex).getName());
        MuBar = findViewById(R.id.menu);
        MuBar.setProgress(FoodStringIndex);
        inputSeekBar.setProgress((int)(dc.getDietItem(FoodStringIndex)*2));//set to 2*hash<FoodNameString.get(FoodStringIndex)>
    }

    //run when the first time hits button "next"
    /*public void start() {
        //add food string
        FoodNameString.add(0, "Lamb");
        FoodNameString.add(1, "Beef");
        FoodNameString.add(2, "Cheese");
        FoodNameString.add(3, "Pork");
        FoodNameString.add(4, "Farmed Salmon");
        FoodNameString.add(5, "Turkey");
        FoodNameString.add(6, "Chicken");
        FoodNameString.add(7, "Canned Tuna");
        FoodNameString.add(8, "Eggs");
        FoodNameString.add(9, "Potatoes");
        FoodNameString.add(10, "Rice");
        FoodNameString.add(11, "Peanut Butter");
        FoodNameString.add(12, "Nuts");
        FoodNameString.add(13, "Yogurt");
        FoodNameString.add(14, "Brococoli");
        FoodNameString.add(15, "Tofu");
        FoodNameString.add(16, "Dry Beans");
        FoodNameString.add(17, "Milk(2%)");
        FoodNameString.add(18, "Tomatoes");
        FoodNameString.add(19, "Lentils");
        //add FoodImgList
        FoodPicList[0] = R.drawable.lamb;
        FoodPicList[1] = R.drawable.beef;
        FoodPicList[2] = R.drawable.cheese;
        FoodPicList[3] = R.drawable.pork;
        FoodPicList[4] = R.drawable.farmed_salmon;
        FoodPicList[5] = R.drawable.turkey;
        FoodPicList[6] = R.drawable.chicken;
        FoodPicList[7] = R.drawable.canned_tuna;
        FoodPicList[8] = R.drawable.eggs;
        FoodPicList[9] = R.drawable.potatoes;
        FoodPicList[10] = R.drawable.rice;
        FoodPicList[11] = R.drawable.peanut_butter;
        FoodPicList[12] = R.drawable.nuts;
        FoodPicList[13] = R.drawable.yogurt;
        FoodPicList[14] = R.drawable.brococoli;
        FoodPicList[15] = R.drawable.tofu;
        FoodPicList[16] = R.drawable.dry_beans;
        FoodPicList[17] = R.drawable.milk;
        FoodPicList[18] = R.drawable.tomatoes;
        FoodPicList[19] = R.drawable.lentils;

    }*/

    //  button next
    public void next(View view) {
        FoodStringIndex++;
        update();
    }

    public void back(View view) {
        InputActivity.this.finish();
    }

    public void privious(View view) {
        //InputActivity.this.finish();

        if (FoodStringIndex == 0) {
            //go to beginning activity
            InputActivity.this.finish();
        } else {
            FoodStringIndex--;
            update();
        }
    }

    public void random(View view){
        int r=ThreadLocalRandom.current().nextInt(0, 100);
        skBar.setProgress(r);
        input(skBar.getProgress());

    }

    public void result(View view){
        /*
        go to result activity
         */
        Intent intent =new Intent(InputActivity.this,Co2CalActivity.class);
        InputActivity.this.startActivity(intent);
    }
/*
    public void clear(View view){
        Toast.makeText(getApplicationContext(), "all data has been cleared", Toast.LENGTH_SHORT).show();
        dc.resetDiet();
        update();
    }*/

    private void input(int f){
        if(f!=0){

            dc.addDietItem(FoodStringIndex,(float)f/2);
        }
        else{dc.delDietItem(FoodStringIndex);}

    }

}

