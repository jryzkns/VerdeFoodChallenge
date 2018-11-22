package allinmain.cmpt276.verdefoodchallenge;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class GreenFoodActivity extends Activity implements bottom_bar.OnFragmentInteractionListener {
    private Button reset;
    private ImageView about;
    private GridView foodgrdv;
    private DataCenter dc;
    private GridViewAdapter mGridViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greenfood);

        FragmentManager fm = getFragmentManager();
        bottom_bar fragment = new bottom_bar();
        fm.beginTransaction().add(R.id.bottom_bar_frame,fragment).commit();


        init();
    }
    private void init()
    {

        dc=DataCenter.getInstance();

        reset=findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dc.resetDiet();
                Toast.makeText(getApplicationContext(), "all data has been cleared", Toast.LENGTH_SHORT).show();
            }
        });

        about=findViewById(R.id.about_food);
        about.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(GreenFoodActivity.this,ActivityAboutUs.class);
                 GreenFoodActivity.this.startActivity(intent);
             }
        });

        foodgrdv = findViewById(R.id.foodgrdv);
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
    public void onFragmentInteraction() {

    }
}