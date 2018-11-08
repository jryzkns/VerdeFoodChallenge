package allinmain.cmpt276.verdefoodchallenge;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Manage_Profile extends Activity implements View.OnClickListener {
 //   private DatabaseReference databaseReference;
//    private FirebaseAuth firebaseAuth;
//    private EditText editTextfirstName,editTextLastinit;
//    private EditText editTextLocation;

    Spinner location_spinner;
    ArrayAdapter<CharSequence> location_adapter;

//    private ArrayList<String> locations;

    private int location_seleted;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage__profile);
        init();
//        firebaseAuth = FirebaseAuth.getInstance();
//        databaseReference = FirebaseDatabase.getInstance().getReference();
//        editTextfirstName= (EditText) findViewById(R.id.firstname_change_name_manage);
//        editTextLastinit=(EditText) findViewById(R.id.lastinit_change_name_manage);
//        if (firebaseAuth.getCurrentUser()==null){
//            finish();
//            Intent intent =new Intent(Manage_Profile.this,login.class);
//            Manage_Profile.this.startActivity(intent);
//        }



    }
    private void init(){

//        locations.add(0,"all");
//        locations.add(1,"V");
//        locations.add(2,"B");
//        locations.add(3,"C");
//        locations.add(4,"S");
//        locations.add(5,"R");
//        locations.add(6,"NW");

        location_spinner = findViewById(R.id.locationList);
        location_adapter=ArrayAdapter.createFromResource(this,R.array.Locations,android.R.layout.simple_spinner_item);
        location_adapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
        location_spinner.setAdapter(location_adapter);
        location_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                location_seleted = i;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        TextView icon_bar=findViewById(R.id.change_icon);
        TextView name_bar=findViewById(R.id.change_name);
        TextView location_bar=findViewById(R.id.change_location);
        TextView delete_bar=findViewById(R.id.delete_Profile);
        icon_bar.setOnClickListener(this);
        name_bar.setOnClickListener(this);
        location_bar.setOnClickListener(this);
        delete_bar.setOnClickListener(this);



    }

//    private void saveUserInformation(){
//        String firstName= editTextfirstName.getText().toString().trim();
//        String lastInitial=editTextLastinit.getText().toString().trim();
//        UserInformation userInformation = new UserInformation(firstName,lastInitial,"0","0");
//        FirebaseUser user = firebaseAuth.getCurrentUser();
//        databaseReference.child(user.getUid()).setValue(userInformation);
//        Toast.makeText(this,"Informtion saved...",Toast.LENGTH_LONG).show();
//    }

    public void onClick(View view){

        switch(view.getId()){
//            case R.id.saveButton:
//                saveUserInformation();
//                Intent intent =new Intent(Manage_Profile.this,GreenFoodActivity.class);
//                Manage_Profile.this.startActivity(intent);
//                break;
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





    //expand 4 bars
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
