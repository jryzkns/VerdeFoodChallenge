package allinmain.cmpt276.verdefoodchallenge;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Meal_My extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal__my);
        init();
    }

    private void init(){

        View searchMeal = findViewById(R.id.Search_meal);
        searchMeal.setOnClickListener(this);

        View addNew = findViewById(R.id.addNewMeal_meal);
        addNew.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.Search_meal:
                intent = new Intent(Meal_My.this,Meal_Search.class);
                Meal_My.this.startActivity(intent);
                this.finish();
                break;
            case R.id.addNewMeal_meal:
                intent = new Intent(Meal_My.this,Meal_Add.class);
                Meal_My.this.startActivity(intent);
                break;
        }
    }
}
