package allinmain.cmpt276.verdefoodchallenge;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class community extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);
        init();
    }

    private void init(){
        ImageView about = findViewById(R.id.about_profile);
        TextView map_all=findViewById(R.id.all_map_community);
        TextView map_V=findViewById(R.id.V_map_community);
        TextView map_B=findViewById(R.id.B_map_community);
        TextView map_C=findViewById(R.id.C_map_community);
        TextView map_S=findViewById(R.id.S_map_community);
        TextView map_R=findViewById(R.id.R_map_community);
        TextView map_NW=findViewById(R.id.NW_map_community);

        about.setOnClickListener(this);
        map_all.setOnClickListener(this);
        map_V.setOnClickListener(this);
        map_B.setOnClickListener(this);
        map_C.setOnClickListener(this);
        map_S.setOnClickListener(this);
        map_R.setOnClickListener(this);
        map_NW.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        ImageView map = findViewById(R.id.map_community);
        switch(view.getId()){
            case R.id.all_map_community:
                map.setBackgroundResource(R.drawable.gvancouver);
                break;
            case R.id.V_map_community:
                map.setBackgroundResource(R.drawable.vancouver);
                break;
            case R.id.B_map_community:
                map.setBackgroundResource(R.drawable.burnaby);
                break;
            case R.id.C_map_community:
                map.setBackgroundResource(R.drawable.coquitlam);
                break;
            case R.id.S_map_community:
                map.setBackgroundResource(R.drawable.surrey);
                break;
            case R.id.R_map_community:
                map.setBackgroundResource(R.drawable.richmond);
                break;
            case R.id.NW_map_community:
                map.setBackgroundResource(R.drawable.new_westminster);
                break;
            case R.id.about_community:
                Intent about =new Intent(community.this,ActivityAboutUs.class);
                community.this.startActivity(about);
                break;
        }

    }
}
