package allinmain.cmpt276.verdefoodchallenge;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

public class Meal_Search extends Activity implements View.OnClickListener {

    private Spinner location_spinner;
    private Spinner protein_spinner;
    ArrayAdapter<CharSequence> location_adapter;
    ArrayAdapter<CharSequence> protein_adapter;
    private int location_seleted;
    private int protein_seleted;

    private boolean advance_expand;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal__search);
        init();
    }


    private void init(){


        //set up 2 spinners
        location_spinner = findViewById(R.id.location_advanceSearch_meal);
        location_adapter=ArrayAdapter.createFromResource(this,R.array.Locations,android.R.layout.simple_spinner_item);
        location_adapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
        location_spinner.setAdapter(location_adapter);
        location_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) { location_seleted = i; }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        protein_spinner = findViewById(R.id.protein_advanceSearch_meal);
        protein_adapter=ArrayAdapter.createFromResource(this,R.array.Proteins,android.R.layout.simple_spinner_item);
        protein_adapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
        protein_spinner.setAdapter(protein_adapter);
        protein_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) { protein_seleted = i; }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });


        //set listeners
        View advance_expand_button = findViewById(R.id.advance_expand_meal);
        advance_expand_button.setOnClickListener(this);
        View search_basic = findViewById(R.id.searchbar_meal);
        search_basic.setOnClickListener(this);
        View search_advance = findViewById(R.id.searchButton_advance_meal);
        search_advance.setOnClickListener(this);
        View MyMeal = findViewById(R.id.My_meal);
        MyMeal.setOnClickListener(this);


        //advance bar not expanded
        advance_expand = false;

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.My_meal:  //goto myMeals
                Intent intent = new Intent(Meal_Search.this,Meal_My.class);
                Meal_Search.this.startActivity(intent);

                break;
            case R.id.searchbar_meal:   //basic search

                break;
            case R.id.advance_expand_meal:  //expand or close advanceLayout
                if(advance_expand){advance_close();}
                else {advance_expand();}

                break;
            case R.id.searchButton_advance_meal:    //advance search

                advance_close();
                break;
        }

    }




    private void advance_expand(){
        LinearLayout layout = findViewById(R.id.advanceLayout_meal);
        layout.requestLayout();
        layout.getLayoutParams().height       = 200;
        advance_expand = true;

    }
    private void advance_close(){
        LinearLayout layout = findViewById(R.id.advanceLayout_meal);
        layout.requestLayout();
        layout.getLayoutParams().height       = 0;
        advance_expand = false;

    }


}




