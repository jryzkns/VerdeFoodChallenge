package allinmain.cmpt276.verdefoodchallenge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AdviceActivity extends Activity implements View.OnClickListener{
    private Button recal;
    private TextView calinfo;
    private DataCenter dc=DataCenter.getInstance();
    private String PolicyType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice);
        init();
    }
    private void init()
    {

        recal=(Button)this.findViewById(R.id.recal);
        calinfo=(TextView)this.findViewById(R.id.calinfo);
        recal.setOnClickListener(this);
        Intent intent= this.getIntent();
        PolicyType=intent.getStringExtra("Strategy");

        calinfo.setText(dc.getSuggestionInfo(PolicyType));

    }

    @Override
    public void onClick(View view) {
        this.finish();
    }
}
