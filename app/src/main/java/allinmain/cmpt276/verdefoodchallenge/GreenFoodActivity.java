package allinmain.cmpt276.verdefoodchallenge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

public class GreenFoodActivity extends Activity implements View.OnClickListener {
    private Button reset,suggest,result,about;
    private GridView foodgrdv;
    private DataCenter dc;
    private GridViewAdapter mGridViewAdapter;
    private SharedPreferences sp ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greenfood);
        init();
    }
    private void init()
    {
        dc=DataCenter.getInstance();


        sp=getSharedPreferences("userData", Context.MODE_PRIVATE);;

        for(int i=0;i<dc.getFoodsSize();i++)
        {
            if(sp.getFloat(dc.getFood(i).getName(),-1f)>0)
            {
                dc.addDietItem(i, sp.getFloat(dc.getFood(i).getName(),-1f));
            }

        }



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
                Intent intent=new Intent(GreenFoodActivity.this,InputActivity.class);
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
                Toast.makeText(getApplicationContext(), "all data has been cleared", Toast.LENGTH_SHORT).show();
                //remove saved data
                sp.edit().clear().commit();
                break;
            case R.id.suggest:
                intent =new Intent(GreenFoodActivity.this,SuggestionType.class);
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