package code.app.src.main.java.allinmain.cmpt276.verdefoodchallenge;

import allinmain.cmpt276.verdefoodchallenge.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

public class ActivityAboutUs extends Activity implements View.OnClickListener {
    private Button iknow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);
        init();
    }
    private void init()
    {
        iknow=(Button)this.findViewById(R.id.iknow);
        iknow.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        this.finish();
    }
}
