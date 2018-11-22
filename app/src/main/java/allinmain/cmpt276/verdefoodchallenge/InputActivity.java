package allinmain.cmpt276.verdefoodchallenge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;


public class InputActivity extends Activity {

    //private ArrayList<String> FoodNameString = new ArrayList<>();

    //private int[] FoodPicList = new int[20];
    private int FoodStringIndex;
    private SeekBar skBar;
    private SeekBar MuBar;

    private DataCenter dc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        dc = DataCenter.getInstance();

        Intent intent= this.getIntent();

        FoodStringIndex=intent.getIntExtra("foodid",-1);

        SharedPreferences sp=getSharedPreferences("userData", Context.MODE_PRIVATE);
        if(sp.getFloat(dc.getFood(FoodStringIndex).getName(),-1f)>0)
        {
            dc.addDietItem(FoodStringIndex, sp.getFloat(dc.getFood(FoodStringIndex).getName(),-1f));
        }


        skBar = findViewById(R.id.input_value);
        skBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                TextView sb_process = findViewById(R.id.skprocess);
                sb_process.setText("" + round(getExpProgress(seekBar.getProgress()),2));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                double expprogress = round(getExpProgress(seekBar.getProgress()),2);

                input(expprogress);
                update();
            }
        });

        MuBar = findViewById(R.id.menu);
        MuBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                FoodStringIndex=seekBar.getProgress();
                update();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar){
            }
        });
        update();
    }

    //update food name and food picture base on FoodStringIndex
    private void update() {

        if (FoodStringIndex > 19) {
            Intent back = new Intent(this,GreenFoodActivity.class);
            this.startActivity(back);

            //as suggested from Angelica during sprint 1 tech demo
            Toast.makeText(this,
                    "Now that you have set your eating habits, " +
                            "now check out what impact your diet has on the planet " +
                            "by pressing the result section!",Toast.LENGTH_LONG).show();
            InputActivity.this.finish();
        }else{
            TextView foodName = findViewById(R.id.foodName);
            ImageView foodImg = findViewById(R.id.foodImg);
            SeekBar inputSeekBar = findViewById(R.id.input_value);
            foodImg.setImageResource(dc.getFood(FoodStringIndex).getResid());
            foodName.setText(dc.getFood(FoodStringIndex).getName());

            MuBar.setProgress(FoodStringIndex);
            inputSeekBar.setProgress((int) getLinearProgress(dc.getDietItem(FoodStringIndex)));
        }

    }

    //  button next
    public void next(View view) {

        SeekBar inputSeekBar = findViewById(R.id.input_value);
        dc.addDietItem(FoodStringIndex, (float) getExpProgress(inputSeekBar.getProgress()));
        SharedPreferences sp=getSharedPreferences("userData", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putFloat(dc.getFood(FoodStringIndex).getName(),
                (float) getExpProgress(inputSeekBar.getProgress()));
        edit.commit();
        FoodStringIndex++;

        if (FoodStringIndex <= 19){

            if(sp.getFloat(dc.getFood(FoodStringIndex).getName(),-1f)>0)
            {
                dc.addDietItem(FoodStringIndex, sp.getFloat(dc.getFood(FoodStringIndex).getName(),-1f));
            }
        }

        update();
    }

    public void back(View view) {
        InputActivity.this.finish();
    }

    public void previous(View view) {
        //InputActivity.this.finish();

        if (FoodStringIndex == 0) {
            //go to beginning activity
            InputActivity.this.finish();
        } else {
            FoodStringIndex--;
            update();
        }
    }

    public void result(View view){

        Intent intent =new Intent(InputActivity.this,Co2CalActivity.class);
        InputActivity.this.startActivity(intent);
    }

    private void input(double d){
        if(d!=0){

            dc.addDietItem(FoodStringIndex,(float)d);
        }
        else{dc.delDietItem(FoodStringIndex);}

    }

    private double getExpProgress(double linearprogress){
        double c = 100/Math.log(51);
        double expProgress = Math.exp(linearprogress/c) - 1;
        return expProgress;
    }

    //inverse of getExpProgress
    private double getLinearProgress(double expprogress){
        double linearProgress = 100* Math.log(expprogress+1)/Math.log(51);
        return linearProgress;
    }

    // function referred from: https://www.baeldung.com/java-round-decimal-number
    private static double round(double value, int places) {
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}
