package allinmain.cmpt276.verdefoodchallenge;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileInfor extends Activity implements View.OnClickListener {
    private Button Buttonsave;
    private Button ButtonDelete;
    private EditText editTextName;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private Button logout;
    private Spinner location_spinner;
    ArrayAdapter<CharSequence> location_adapter;
    int location_selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_infor);

        firebaseAuth =FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser()==null){
            //profile activity here
            finish();
            Intent intent =new Intent(ProfileInfor.this,login.class);
            ProfileInfor.this.startActivity(intent);
        }
        //FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference= FirebaseDatabase.getInstance().getReference();

        // connect to layout objects
        editTextName = (EditText) findViewById(R.id.editTextName);
        Buttonsave = (Button) findViewById(R.id.Buttonsave);
        ButtonDelete = (Button)findViewById(R.id.delete_Profile);
        logout = (Button)findViewById(R.id.logout_Profile);
        //initialize onClick listeners
        Buttonsave.setOnClickListener(this);
        logout.setOnClickListener(this);
        ButtonDelete.setOnClickListener(this);


        FirebaseUser user = firebaseAuth.getCurrentUser();
        DatabaseReference databaseReferenceName = FirebaseDatabase.getInstance().getReference("USERINFO").child(user.getUid()).child("name");
        DatabaseReference databaseReferenceLocation = FirebaseDatabase.getInstance().getReference("USERINFO").child(user.getUid()).child("location");
        // firebase addVaulueEvent listeners for names
        databaseReferenceName.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                editTextName.setText(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        // firebase addVaulueEvent listeners for location
        databaseReferenceLocation.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        //set up spinner
        location_spinner = findViewById(R.id.SPlogin_location);
        location_adapter=ArrayAdapter.createFromResource(this,R.array.Locations,android.R.layout.simple_spinner_item);
        location_adapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
        location_spinner.setAdapter(location_adapter);
        location_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                location_selected = i;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

    }

    //save user information
    private void saveUserInformation(){
        String FullName= editTextName.getText().toString().trim();
        String Location= getResources().getStringArray(R.array.Locations)[location_selected];
        ;
        UserInformation userInformation = new UserInformation(FullName,Location,"0","0");
        FirebaseUser user = firebaseAuth.getCurrentUser();
        //databaseReference.child(user.getUid()).setValue(userInformation);
        databaseReference.child("USERINFO").child(user.getUid()).setValue(userInformation);
        Toast.makeText(this,"Informtion saved...",Toast.LENGTH_LONG).show();
    }


    //on click event
    @Override
    public void onClick(View view) {

        switch(view.getId()){
            case R.id.Buttonsave:
                saveUserInformation();
                Intent intent =new Intent(ProfileInfor.this,GreenFoodActivity.class);
                ProfileInfor.this.startActivity(intent);
                break;
            case R.id.logout_Profile:
                FirebaseAuth.getInstance().signOut();
                Intent i=new Intent(ProfileInfor.this,login.class);
                ProfileInfor.this.startActivity(i);
                break;
            case R.id.delete_Profile:
                FirebaseUser user = firebaseAuth.getCurrentUser();
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("USERINFO").child(user.getUid());

                databaseReference.removeValue();
                editTextName.setText("");
                Toast.makeText(this,"INFOR delete",Toast.LENGTH_LONG).show();
                break;
        }

    }
}
