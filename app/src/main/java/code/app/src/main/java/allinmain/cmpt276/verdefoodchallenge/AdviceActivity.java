package code.app.src.main.java.allinmain.cmpt276.verdefoodchallenge;

import allinmain.cmpt276.verdefoodchallenge.DataCenter;
import allinmain.cmpt276.verdefoodchallenge.R;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AdviceActivity extends Activity implements View.OnClickListener{
    private Button back,recal;
    private TextView calinfo;
    private allinmain.cmpt276.verdefoodchallenge.DataCenter dc= DataCenter.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice);
        init();
    }
    private void init()
    {
        back=(Button)this.findViewById(R.id.back);
        recal=(Button)this.findViewById(R.id.recal);
        calinfo=(TextView)this.findViewById(R.id.calinfo);
        back.setOnClickListener(this);
        recal.setOnClickListener(this);
        calinfo.setText(dc.getDietInfo());

    }

    @Override
    public void onClick(View view) {
        this.finish();
    }
}
