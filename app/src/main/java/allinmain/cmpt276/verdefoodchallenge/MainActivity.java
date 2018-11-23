package allinmain.cmpt276.verdefoodchallenge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;

public class MainActivity extends Activity implements CompoundButton.OnCheckedChangeListener{
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

        start = findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MainActivity.this.startActivity(new Intent(MainActivity.this,login.class));
//                MainActivity.this.startActivity(new Intent(MainActivity.this,uploadMealActivity.class));
//                MainActivity.this.finish();
            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        SharedPreferences.Editor edit = sp.edit();
    }
}
