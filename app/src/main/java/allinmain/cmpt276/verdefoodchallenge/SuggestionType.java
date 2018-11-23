package allinmain.cmpt276.verdefoodchallenge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.getbase.floatingactionbutton.FloatingActionButton;

//import android.support.v7.app.AppCompatActivity;

public class SuggestionType extends Activity implements View.OnClickListener {


    ImageView Carnivore,Lessmeat,Vegetarian,Vegan;
    //the thrid part library version
    FloatingActionButton Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion_type);
        init();
    }

    //Initialization
    public void init(){
        Carnivore=findViewById(R.id.toSuggestion_Carnivore);
        Lessmeat=findViewById(R.id.toSuggestion_Lessmeat);
        Vegetarian=findViewById(R.id.toSuggestion_Vegetarian);
        Vegan=findViewById(R.id.toSuggestion_Vegan);
        Back=findViewById(R.id.toSuggestion_Back);

        Carnivore.setOnClickListener(this);
        Lessmeat.setOnClickListener(this);
        Vegetarian.setOnClickListener(this);
        Vegan.setOnClickListener(this);
        Back.setOnClickListener(this);

        /*Carnivore.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                switch (arg1.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        Carnivore.setBackgroundColor(getResources().getColor(R.color.button_change));
                        break;
                    }
                    case MotionEvent.ACTION_CANCEL:{
                        Carnivore.setBackgroundColor(getResources().getColor(R.color.page_background));
                        break;
                    }
                }
                return true;
            }
        });

        Lessmeat.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                switch (arg1.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        Lessmeat.setBackgroundColor(getResources().getColor(R.color.button_change));
                        break;
                    }
                    case MotionEvent.ACTION_CANCEL:{
                        Lessmeat.setBackgroundColor(getResources().getColor(R.color.page_background));
                        break;
                    }
                }
                return true;
            }
        });

        Vegan.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                switch (arg1.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        Vegan.setBackgroundColor(getResources().getColor(R.color.button_change));
                        break;
                    }
                    case MotionEvent.ACTION_CANCEL:{
                        Vegan.setBackgroundColor(getResources().getColor(R.color.page_background));
                        break;
                    }
                }
                return true;
            }
        });

        Vegetarian.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                switch (arg1.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        Vegetarian.setBackgroundColor(getResources().getColor(R.color.button_change));
                        break;
                    }
                    case MotionEvent.ACTION_CANCEL:{
                        Vegetarian.setBackgroundColor(getResources().getColor(R.color.page_background));
                        break;
                    }
                }
                return true;
            }
        });*/

    }

    //Suggestion types
    public void onClick(View view) {

        Intent intent=new Intent(SuggestionType.this,AdviceActivity.class);
        switch (view.getId()){
            case R.id.toSuggestion_Carnivore:   //Type: CARNIVORE
                intent.putExtra("Strategy","CARNIVORE");
                break;
            case R.id.toSuggestion_Lessmeat:    //Type: LESSMEAT
                intent.putExtra("Strategy","LESSMEAT");
                break;
            case R.id.toSuggestion_Vegetarian:  //Type: VEGETARIAN
                intent.putExtra("Strategy","VEGETARIAN");
                break;
            case R.id.toSuggestion_Vegan:       //Type: VEGAN
                intent.putExtra("Strategy","VEGAN");
                break;
            case R.id.toSuggestion_Back:        //Button "Back"
                overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);
                super.onBackPressed();
                return;
        }

        SuggestionType.this.startActivity(intent);
    }
    // when you click back and will slip back
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);

    }
}
