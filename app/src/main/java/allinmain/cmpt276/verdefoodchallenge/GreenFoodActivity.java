package allinmain.cmpt276.verdefoodchallenge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class GreenFoodActivity extends Activity implements View.OnClickListener {
    private Button reset,profile,community,results;
    private ImageView about;
    private GridView foodgrdv;
    private DataCenter dc;
    private GridViewAdapter mGridViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greenfood);
        init();
    }
    private void init()
    {
        dc=DataCenter.getInstance();

        reset=(Button)this.findViewById(R.id.reset);
        profile=(Button)this.findViewById(R.id.profile);
        about=this.findViewById(R.id.about_food);

        results= this.findViewById(R.id.GFtoRes);
        community=this.findViewById(R.id.GFtoComm);

        reset.setOnClickListener(this);
        about.setOnClickListener(this);
        profile.setOnClickListener(this);
        
        community.setOnClickListener(this);
        results.setOnClickListener(this);

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
            case R.id.profile:
                intent =new Intent(GreenFoodActivity.this,Profile.class);
                GreenFoodActivity.this.startActivity(intent);
                break;
            case R.id.reset:
                dc.resetDiet();
                Toast.makeText(getApplicationContext(), "all data has been cleared", Toast.LENGTH_SHORT).show();
                break;
//            case R.id.suggest:
  //              intent =new Intent(GreenFoodActivity.this,SuggestionType.class);
    //            GreenFoodActivity.this.startActivity(intent);
      //          break;
//            case R.id.result:
  //              intent =new Intent(GreenFoodActivity.this,Co2CalActivity.class);
    //            GreenFoodActivity.this.startActivity(intent);
      //          break;
            case R.id.about_food:
                intent =new Intent(GreenFoodActivity.this,ActivityAboutUs.class);
                GreenFoodActivity.this.startActivity(intent);
                break;
            case R.id.GFtoComm:
                intent = new Intent(GreenFoodActivity.this,community.class);
                GreenFoodActivity.this.startActivity(intent);
                break;
            case R.id.GFtoRes:
                intent = new Intent(GreenFoodActivity.this,Co2CalActivity.class);
                GreenFoodActivity.this.startActivity(intent);
                break;
        }

    }
}