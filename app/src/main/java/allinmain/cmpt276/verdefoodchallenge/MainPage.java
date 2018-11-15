package allinmain.cmpt276.verdefoodchallenge;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainPage extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        init();
    }



    private  void init(){
        View Button1=findViewById(R.id.Button1_MainPage);
        View Button2=findViewById(R.id.Button2_MainPage);
        View Button3=findViewById(R.id.Button3_MainPage);
        View Button4=findViewById(R.id.Button4_MainPage);
        View Button5=findViewById(R.id.Button5_MainPage);
        View Button6=findViewById(R.id.Button6_MainPage);
        View Button7=findViewById(R.id.Button7_MainPage);
        View Button8=findViewById(R.id.Button8_MainPage);


        Button1.setOnClickListener(this);
        Button2.setOnClickListener(this);
        Button3.setOnClickListener(this);
        Button4.setOnClickListener(this);
        Button5.setOnClickListener(this);
        Button6.setOnClickListener(this);
        Button7.setOnClickListener(this);
        Button8.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.Button1_MainPage:
                Toast.makeText(getApplicationContext(), "1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Button2_MainPage:
                Toast.makeText(getApplicationContext(), "2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Button3_MainPage:
                Toast.makeText(getApplicationContext(), "3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Button4_MainPage:
                Toast.makeText(getApplicationContext(), "4", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Button5_MainPage:
                Toast.makeText(getApplicationContext(), "5", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Button6_MainPage:
                Toast.makeText(getApplicationContext(), "6", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Button7_MainPage:
                Toast.makeText(getApplicationContext(), "7", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Button8_MainPage:
                Toast.makeText(getApplicationContext(), "8", Toast.LENGTH_SHORT).show();
                break;


        }

    }
}
