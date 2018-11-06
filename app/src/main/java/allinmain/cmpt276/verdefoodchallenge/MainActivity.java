package allinmain.cmpt276.verdefoodchallenge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;

public class MainActivity extends Activity implements CompoundButton.OnCheckedChangeListener{
    ///private RadioButton malerb,femalerb;
    private SharedPreferences sp ;
    private Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init()
    {
        sp = getSharedPreferences("User", Context.MODE_PRIVATE);
        if(sp.getInt("gender",-1)!=-1)
        {
           // this.startActivity(new Intent(this,GreenFoodActivity.class));
            //this.finish();
        };
        //malerb=(RadioButton)this.findViewById(R.id.malerb);
        //femalerb=(RadioButton)this.findViewById(R.id.femalerb);
        start=(Button)this.findViewById(R.id.start);
        //malerb.setOnCheckedChangeListener(this);
        //femalerb.setOnCheckedChangeListener(this);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                MainActivity.this.startActivity(new Intent(MainActivity.this,login.class));
                MainActivity.this.startActivity(new Intent(MainActivity.this,community.class));
                MainActivity.this.finish();
            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        SharedPreferences.Editor edit = sp.edit();
        /*switch (compoundButton.getId())
        {
            case R.id.malerb:
                if(b)
                {
                    edit.putInt("gender",1);
                    edit.commit();
                }
                break;
            case R.id.femalerb:
                edit.putInt("gender",0);
                edit.commit();
                break;
        }*/
    }
}
