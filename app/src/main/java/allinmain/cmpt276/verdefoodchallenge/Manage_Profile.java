package allinmain.cmpt276.verdefoodchallenge;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
import android.support.annotation.NonNull;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Manage_Profile extends Activity implements View.OnClickListener {
    //   private DatabaseReference databaseReference;
//    private FirebaseAuth firebaseAuth;
//    private EditText editTextfirstName,editTextLastinit;
//    private EditText editTextLocation;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    // define layout objects be used in this activity
    private Button manage_save;
    private EditText manage_editText_name;
    private Button delete_infor;
    private EditText getManage_editText_location;
    private Spinner location_spinner;
    private Button location_save_Button;

    ArrayAdapter<CharSequence> location_adapter;

//    private ArrayList<String> locations;

    private int location_seleted;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage__profile);
        databaseReference= FirebaseDatabase.getInstance().getReference();
        init();
        firebaseAuth =FirebaseAuth.getInstance();
//        firebaseAuth = FirebaseAuth.getInstance();
//        databaseReference = FirebaseDatabase.getInstance().getReference();
//        editTextfirstName= (EditText) findViewById(R.id.firstname_change_name_manage);
//        editTextLastinit=(EditText) findViewById(R.id.lastinit_change_name_manage);
//        if (firebaseAuth.getCurrentUser()==null){
//            finish();
//            Intent intent =new Intent(Manage_Profile.this,login.class);
//            Manage_Profile.this.startActivity(intent);
//        }
        FirebaseUser user = firebaseAuth.getCurrentUser();
        DatabaseReference databaseReferenceName = FirebaseDatabase.getInstance().getReference("USERINFO").child(user.getUid()).child("name");
        databaseReferenceName.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                manage_editText_name.setText(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    private void init(){

//        locations.add(0,"all");
//        locations.add(1,"V");
//        locations.add(2,"B");
//        locations.add(3,"C");
//        locations.add(4,"S");
//        locations.add(5,"R");
//        locations.add(6,"NW");

        // connect to layout objects
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

        // connect to layout ojects
        TextView icon_bar =     findViewById(R.id.change_icon);
        TextView name_bar =     findViewById(R.id.change_name);
        TextView location_bar = findViewById(R.id.change_location);
        TextView delete_bar =   findViewById(R.id.delete_Profile);
        delete_infor =          findViewById(R.id.save_delete_button_manage);
        manage_save =           findViewById(R.id.save_name_button_manage);
        location_save_Button =  findViewById(R.id.location_save);
        manage_editText_name =  findViewById(R.id.firstname_change_name_manage);
        //getManage_editText_location = findViewById(R.id.)

        // initialize and set up onClick listener for buttons
        delete_infor.setOnClickListener(this);
        manage_save.setOnClickListener(this);
        location_save_Button.setOnClickListener(this);
        icon_bar.setOnClickListener(this);
        name_bar.setOnClickListener(this);
        location_bar.setOnClickListener(this);
        delete_bar.setOnClickListener(this);


    // method for saving user informations
    }
    private void saveUserInformation(){
        String FullName= manage_editText_name.getText().toString().trim();
        String Location=location_spinner.getSelectedItem().toString().trim();
        UserInformation userInformation = new UserInformation(FullName,Location,"0","0");
        FirebaseUser user = firebaseAuth.getCurrentUser();
        //databaseReference.child(user.getUid()).setValue(userInformation);
        databaseReference.child("USERINFO").child(user.getUid()).setValue(userInformation);
        Toast.makeText(this,"Informtion saved...",Toast.LENGTH_LONG).show();
    }

// Onclick listener for committing user profile data change
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
            case R.id.save_name_button_manage:
                saveUserInformation();
                break;
            case R.id.save_delete_button_manage:
                FirebaseUser user = firebaseAuth.getCurrentUser();
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("USERINFO").child(user.getUid());

                databaseReference.removeValue();
                manage_editText_name.setText("");
                //editTextLocation.setText("");
                Toast.makeText(this,"INFOR deleted",Toast.LENGTH_LONG).show();
                break;
            case R.id.location_save:
                saveUserInformation();
                break;
        }


    }





    //expand four spinning bars (adjust the height of bars layout space)
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
            icon.getLayoutParams().height       = 500;
            name.getLayoutParams().height       = 0;
            location.getLayoutParams().height   = 0;
            delete.getLayoutParams().height     = 0;
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

//    public void initializeManageBars()
//    {
//        LinearLayout icon       = findViewById(R.id.change_icon_layout_manage);
//        LinearLayout name       = findViewById(R.id.change_name_layout_manage);
//        LinearLayout location   = findViewById(R.id.change_location_layout_manage);
//        LinearLayout delete     = findViewById(R.id.delete_layout_manage);
//
//        icon.requestLayout();
//        name.requestLayout();
//        location.requestLayout();
//        delete.requestLayout();
//    }

}
