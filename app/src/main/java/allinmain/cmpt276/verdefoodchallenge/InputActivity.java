package allinmain.cmpt276.verdefoodchallenge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.content.SharedPreferences;
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

        SharedPreferences sp=getSharedPreferences("userData", Context.MODE_PRIVATE);;
        if(sp.getFloat(dc.getFood(FoodStringIndex).getName(),-1f)>0)
        {
            dc.addDietItem(FoodStringIndex, sp.getFloat(dc.getFood(FoodStringIndex).getName(),-1f));
        }
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

        MuBar.setProgress(FoodStringIndex);
        inputSeekBar.setProgress((int)(dc.getDietItem(FoodStringIndex)*2));//set to 2*hash<FoodNameString.get(FoodStringIndex)>
    }

    //  button next
    public void next(View view) {


        SeekBar inputSeekBar = findViewById(R.id.input_value);
        /*
           write code that pass user input [FoodNameString.get(FoodStringIndex),inputSeekBar.getProgress()/2]
           覆盖
         */
        dc.addDietItem(FoodStringIndex,inputSeekBar.getProgress()/2);
        SharedPreferences sp=getSharedPreferences("userData", Context.MODE_PRIVATE);;
        SharedPreferences.Editor edit = sp.edit();
        edit.putFloat(dc.getFood(FoodStringIndex).getName(),inputSeekBar.getProgress()/2);
        edit.commit();
        FoodStringIndex++;


        if(sp.getFloat(dc.getFood(FoodStringIndex).getName(),-1f)>0)
        {
            dc.addDietItem(FoodStringIndex, sp.getFloat(dc.getFood(FoodStringIndex).getName(),-1f));
        }

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

