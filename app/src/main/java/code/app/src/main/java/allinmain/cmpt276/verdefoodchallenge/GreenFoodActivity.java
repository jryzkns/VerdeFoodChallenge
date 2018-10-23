package code.app.src.main.java.allinmain.cmpt276.verdefoodchallenge;

import allinmain.cmpt276.verdefoodchallenge.ActivityAboutUs;
import allinmain.cmpt276.verdefoodchallenge.AdviceActivity;
import allinmain.cmpt276.verdefoodchallenge.Co2CalActivity;
import allinmain.cmpt276.verdefoodchallenge.DataCenter;
import allinmain.cmpt276.verdefoodchallenge.FoodActivity;
import allinmain.cmpt276.verdefoodchallenge.GridViewAdapter;
import allinmain.cmpt276.verdefoodchallenge.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

public class GreenFoodActivity extends Activity implements View.OnClickListener {
    private Button reset,suggest,result,about;
    private GridView foodgrdv;
    private allinmain.cmpt276.verdefoodchallenge.DataCenter dc;
    private allinmain.cmpt276.verdefoodchallenge.GridViewAdapter mGridViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greenfood);
        init();
    }
    private void init()
    {
        dc= DataCenter.getInstance();

        reset=(Button)this.findViewById(R.id.reset);
        suggest=(Button)this.findViewById(R.id.suggest);
        result=(Button)this.findViewById(R.id.result);
        about=(Button)this.findViewById(R.id.about);

        reset.setOnClickListener(this);
        suggest.setOnClickListener(this);
        result.setOnClickListener(this);
        about.setOnClickListener(this);

        foodgrdv=(GridView)this.findViewById(R.id.foodgrdv);
        mGridViewAdapter=new GridViewAdapter(this);
        foodgrdv.setAdapter(mGridViewAdapter);
        foodgrdv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                Intent intent=new Intent(GreenFoodActivity.this,FoodActivity.class);
                intent.putExtra("foodid",position);
                GreenFoodActivity.this.startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view) {
        Intent intent=null;
        switch(view.getId())
        {
            case R.id.reset:
                dc.resetDiet();
                break;
            case R.id.suggest:
                intent =new Intent(GreenFoodActivity.this,AdviceActivity.class);
                GreenFoodActivity.this.startActivity(intent);
                break;
            case R.id.result:
                intent =new Intent(GreenFoodActivity.this,Co2CalActivity.class);
                GreenFoodActivity.this.startActivity(intent);
                break;
            case R.id.about:
                intent =new Intent(GreenFoodActivity.this,ActivityAboutUs.class);
                GreenFoodActivity.this.startActivity(intent);
                break;
        }

    }
}