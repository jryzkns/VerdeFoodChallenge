package allinmain.cmpt276.verdefoodchallenge;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Co2CalActivity extends Activity implements View.OnClickListener{
    private Button back,recal;
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
