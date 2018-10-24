package allinmain.cmpt276.verdefoodchallenge;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SuggestionType extends Activity implements View.OnClickListener {


    Button Carnivore,Lessmeat,Vegetarian,Vegan,Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion_type);
        init();
    }


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
    }


    public void onClick(View view) {

        Intent intent=new Intent(SuggestionType.this,AdviceActivity.class);
        switch (view.getId()){
            case R.id.toSuggestion_Carnivore:
                intent.putExtra("Strategy","CARNIVORE");
                break;
            case R.id.toSuggestion_Lessmeat:
                intent.putExtra("Strategy","LESSMEAT");
                break;
            case R.id.toSuggestion_Vegetarian:
                intent.putExtra("Strategy","VEGETARIAN");
                break;
            case R.id.toSuggestion_Vegan:
                intent.putExtra("Strategy","VEGAN");
                break;
            case R.id.toSuggestion_Back:
                this.finish();
                return;
        }

        SuggestionType.this.startActivity(intent);

    }
}
