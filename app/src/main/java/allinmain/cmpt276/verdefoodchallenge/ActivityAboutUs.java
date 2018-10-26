package allinmain.cmpt276.verdefoodchallenge;

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
    //Initialization
    private void init()
    {
        iknow=(Button)this.findViewById(R.id.iknow);
        iknow.setOnClickListener(this);
    }
    //Exit activity
    @Override
    public void onClick(View view) {
        this.finish();
    }
}
