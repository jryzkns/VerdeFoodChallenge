package allinmain.cmpt276.verdefoodchallenge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Co2CalActivity extends Activity implements View.OnClickListener{
    private Button recal, suggest;
    private TextView calinfo;
    private DataCenter dc=DataCenter.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_co2cal);
        init();
    }
    private void init()
    {
        //recal=(Button)this.findViewById(R.id.recal);
        //recal.setOnClickListener(this);


        calinfo=(TextView)this.findViewById(R.id.calinfo);
        calinfo.setText(dc.getDietInfo());
        suggest=this.findViewById(R.id.suggestion_from_result);
        suggest.setOnClickListener(this);




        //navigation
        ImageView toGreenfood = findViewById(R.id.toGreenfood_result);
        ImageView toResult = findViewById(R.id.toResult_result);
        ImageView toCommunity = findViewById(R.id.toCommunity_result);
        ImageView toProfile = findViewById(R.id.toProfile_result);
        toGreenfood.setOnClickListener(this);
        toResult.setOnClickListener(this);
        toCommunity.setOnClickListener(this);
        toProfile.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.suggestion_from_result:
                intent = new Intent(Co2CalActivity.this, SuggestionType.class);
                Co2CalActivity.this.startActivity(intent);
                break;
            case R.id.about_result:
                intent = new Intent(Co2CalActivity.this, ActivityAboutUs.class);
                Co2CalActivity.this.startActivity(intent);
                break;


            //navigation bar
            case R.id.toGreenfood_result:
                intent = new Intent(Co2CalActivity.this, GreenFoodActivity.class);
                Co2CalActivity.this.startActivity(intent);
                this.finish();
                break;
            case R.id.toCommunity_result:
                intent = new Intent(Co2CalActivity.this, community.class);
                Co2CalActivity.this.startActivity(intent);
                this.finish();
                break;
            case R.id.toProfile_result:
                intent = new Intent(Co2CalActivity.this, Profile.class);
                Co2CalActivity.this.startActivity(intent);
                this.finish();
                break;


        }
    }
}


