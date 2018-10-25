package allinmain.cmpt276.verdefoodchallenge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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
        recal=(Button)this.findViewById(R.id.recal);
        calinfo=(TextView)this.findViewById(R.id.calinfo);
        recal.setOnClickListener(this);
        calinfo.setText(dc.getDietInfo());
        suggest=this.findViewById(R.id.suggestion_from_result);
        suggest.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.suggestion_from_result){
            Intent intent =new Intent(Co2CalActivity.this,SuggestionType.class);
            Co2CalActivity.this.startActivity(intent);
        }
        else{this.finish();}
    }
}
