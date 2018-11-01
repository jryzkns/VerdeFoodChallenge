package allinmain.cmpt276.verdefoodchallenge;

import android.app.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Manage_Profile extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage__profile);
        init();
    }
    private void init(){

        TextView icon_bar=findViewById(R.id.change_icon);
        TextView name_bar=findViewById(R.id.change_name);
        TextView location_bar=findViewById(R.id.change_location);
        TextView delete_bar=findViewById(R.id.delete_Profile);


        icon_bar.setOnClickListener(this);
        name_bar.setOnClickListener(this);
        location_bar.setOnClickListener(this);
        delete_bar.setOnClickListener(this);
    }

    public void onClick(View view){

        switch(view.getId()){
            case R.id.change_icon:
                icon_bar();
                break;
            case R.id.change_name:
                name_bar();
                break;
            case R.id.change_location:
                location_bar();
                break;
            case R.id.delete_Profile:
                delete_bar();
                break;


        }


    }


    private void icon_bar(){
        LinearLayout icon= findViewById(R.id.change_icon_layout_manage);
        LinearLayout name= findViewById(R.id.change_name_layout_manage);
        LinearLayout location= findViewById(R.id.change_location_layout_manage);
        LinearLayout delete= findViewById(R.id.delete_layout_manage);
        icon.requestLayout();
        name.requestLayout();
        location.requestLayout();
        delete.requestLayout();

        if(icon.getLayoutParams().height==0) {
            icon.getLayoutParams().height = 500;
            name.getLayoutParams().height = 0;
            location.getLayoutParams().height = 0;
            delete.getLayoutParams().height = 0;
        }else{
            icon.getLayoutParams().height = 0;
        }

    }
    private void name_bar(){
        LinearLayout icon= findViewById(R.id.change_icon_layout_manage);
        LinearLayout name= findViewById(R.id.change_name_layout_manage);
        LinearLayout location= findViewById(R.id.change_location_layout_manage);
        LinearLayout delete= findViewById(R.id.delete_layout_manage);
        icon.requestLayout();
        name.requestLayout();
        location.requestLayout();
        delete.requestLayout();

        if(name.getLayoutParams().height==0) {
            icon.getLayoutParams().height = 0;
            name.getLayoutParams().height = 500;
            location.getLayoutParams().height = 0;
            delete.getLayoutParams().height = 0;
        }else{
            name.getLayoutParams().height = 0;
        }

    }
    private void location_bar(){
        LinearLayout icon= findViewById(R.id.change_icon_layout_manage);
        LinearLayout name= findViewById(R.id.change_name_layout_manage);
        LinearLayout location= findViewById(R.id.change_location_layout_manage);
        LinearLayout delete= findViewById(R.id.delete_layout_manage);
        icon.requestLayout();
        name.requestLayout();
        location.requestLayout();
        delete.requestLayout();

        if(location.getLayoutParams().height==0) {
            icon.getLayoutParams().height = 0;
            name.getLayoutParams().height = 0;
            location.getLayoutParams().height = 500;
            delete.getLayoutParams().height = 0;
        }else{
            location.getLayoutParams().height = 0;
        }

    }
    private void delete_bar(){
        LinearLayout icon= findViewById(R.id.change_icon_layout_manage);
        LinearLayout name= findViewById(R.id.change_name_layout_manage);
        LinearLayout location= findViewById(R.id.change_location_layout_manage);
        LinearLayout delete= findViewById(R.id.delete_layout_manage);
        icon.requestLayout();
        name.requestLayout();
        location.requestLayout();
        delete.requestLayout();

        if(delete.getLayoutParams().height==0) {
            icon.getLayoutParams().height = 0;
            name.getLayoutParams().height = 0;
            location.getLayoutParams().height = 0;
            delete.getLayoutParams().height = 500;
        }else{
            delete.getLayoutParams().height = 0;
        }

    }
}
