package allinmain.cmpt276.verdefoodchallenge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ActivityAboutUs extends Activity implements OnClickListener {

    private Button iknow,share;

    Intent shareIntent;

    String shareBody = "I am making **positive** change on the world with this app, and I have " +
            "pledged to <PLEDGE_DATA>! Come join me!";

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


        // As per Requirement 3.2.5
        share=(Button)this.findViewById(R.id.share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("test/pain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Verde Food Challenge!");
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(shareIntent, "Share to: "));
            }
        });

    }
    //Exit activity
    @Override
    public void onClick(View view) {
        this.finish();
    }
}
