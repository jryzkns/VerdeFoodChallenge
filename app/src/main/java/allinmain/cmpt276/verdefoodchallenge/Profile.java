package allinmain.cmpt276.verdefoodchallenge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Profile extends Activity implements View.OnClickListener{

    //private TextView mTextMessage;

    /*private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.Food_Menu:
                    mTextMessage.setText(R.string.title_Food);
                    return true;
                case R.id.CO2e_Menu:
                    mTextMessage.setText(R.string.title_CO2e);
                    return true;
                case R.id.Suggestion_Menu:
                    mTextMessage.setText(R.string.title_Suggestion);
                    return true;
                case R.id.Community_Menu:
                    mTextMessage.setText(R.string.title_Suggestion);
                    return true;
                case R.id.Me_Menu:
                    mTextMessage.setText(R.string.title_Me);
                    return true;
            }
            return false;
        }
    };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //mTextMessage = (TextView) findViewById(R.id.message);
        //BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        //navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        init();
    }

    private void init(){
        ImageView about = findViewById(R.id.about_profile);
        TextView manage= findViewById(R.id.manage_Profile);
        TextView share= findViewById(R.id.share_Profile);
        TextView logout= findViewById(R.id.logout_Profile);
        about.setOnClickListener(this);
        manage.setOnClickListener(this);
        share.setOnClickListener(this);
        logout.setOnClickListener(this);


    }

    public void onClick(View view ){
        //view.setBackgroundResource(R.color.buttonbar_change);
        switch(view.getId()){
            case R.id.about_food:
                Intent about =new Intent(Profile.this,ActivityAboutUs.class);
                Profile.this.startActivity(about);
                break;
            case R.id.manage_Profile:
                Intent manage=new Intent(Profile.this,Manage_Profile.class);
                Profile.this.startActivity(manage);
                break;
            case R.id.share_Profile:

                break;
            case R.id.logout_Profile:

                break;
        }


    }

}
