package allinmain.cmpt276.verdefoodchallenge;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Meal_View extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal__view);


        Button back = findViewById(R.id.back_viewMeal);
        back.setOnClickListener(this);

        init();
        //delete_able
    }

    private void init(){
        TextView mealName = findViewById(R.id.mealName_viewMeal);
        ImageView mealPic = findViewById(R.id.mealPic_viewMeal);
        TextView restName = findViewById(R.id.restName_viewMeal);
        TextView restLoc = findViewById(R.id.restLoc_viewMeal);
        TextView restDesc = findViewById(R.id.restDesc_viewMeal);


        String mName = getIntent().getStringExtra("mealName");
        String mPic = getIntent().getStringExtra("mealPic");
        String mrName = getIntent().getStringExtra("mealrName");
        String mrLoc = getIntent().getStringExtra("mealrLoc");
        String mDesc = getIntent().getStringExtra("mealDesc");


        //set them

        mealName.setText(mName);

        restName.setText(mrName);
        restLoc.setText(mrLoc);
        restDesc.setText(mDesc);


        delete_able();
    }


    private void delete_able(){
        TextView delete = findViewById(R.id.delete_viewMeal);
        delete.setOnClickListener(this);
        delete.requestLayout();
        delete.getLayoutParams().height       = 100;

    }

    @Override
    public void onClick(View view) {

        switch(view.getId()){
            case R.id.back_viewMeal:
                this.finish();
                break;
            case R.id.delete_viewMeal:
                //db.collection("users").document(ID).delete()
                this.finish();
                break;
        }

    }
}
