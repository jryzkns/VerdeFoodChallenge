package code.app.src.main.java.allinmain.cmpt276.verdefoodchallenge;

import allinmain.cmpt276.verdefoodchallenge.DataCenter;
import allinmain.cmpt276.verdefoodchallenge.R;
import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class FoodActivity extends Activity implements View.OnClickListener {
    private Button cancel,set;
    private EditText ipt;
    private ImageView img;
    private TextView title;
    private int foodid;
    private allinmain.cmpt276.verdefoodchallenge.DataCenter dc= DataCenter.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        init();
    }
    private void init()
    {
       Intent intent= this.getIntent();
        foodid=intent.getIntExtra("foodid",-1);
        float foodkg=dc.getDietItem(foodid);
         cancel=(Button)this.findViewById(R.id.cancel);
         set=(Button)this.findViewById(R.id.set);
         cancel.setOnClickListener(this);
        set.setOnClickListener(this);

        img=(ImageView)this.findViewById(R.id.img);
         ipt=(EditText)this.findViewById(R.id.ipt);
         if(foodkg>0)
         {
             ipt.setText(foodkg+"");
         }
        title=(TextView) this.findViewById(R.id.title);
        title.setText(dc.getFood(foodid).getName());
        img.setImageResource(dc.getFood(foodid).getResid());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.cancel:
                break;
            case R.id.set:
              Float kg=Float.parseFloat( ipt.getText().toString());
              dc.addDietItem(foodid,kg);
                break;
        }
        FoodActivity.this.finish();
    }
}
