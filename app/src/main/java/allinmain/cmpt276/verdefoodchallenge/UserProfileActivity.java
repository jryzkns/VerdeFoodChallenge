package allinmain.cmpt276.verdefoodchallenge;

import android.app.Activity;
//import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import static android.content.ContentValues.TAG;

public class UserProfileActivity extends Activity {

    private EditText mCo2;
    private EditText mFirstName;
    private EditText mLastName;
    private EditText mArea;
    private EditText mEmail;
    private Button mUpload;


    private DatabaseReference mDatabase;


    //private Firebase mRef;


    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    //FirebaseAuth auth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        //final String userId = FirebaseUser.getIdToken();
        final String userId = user.getIdToken();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // if not login in go back to login activity
//        if(firebaseAuth.getCurrentUser() == null){
//            //closing this activity
//            finish();
//            //starting login activity
//            startActivity(new Intent(this, LoginActivity.class));
//        }




        mFirstName= (EditText) findViewById(R.id.FirstNameField);
        mLastName= (EditText) findViewById(R.id.LastNameField);
        mEmail = (EditText)findViewById(R.id.EmailField);
        mCo2= (EditText) findViewById(R.id.CO2Field);
        mArea= (EditText) findViewById(R.id.AreaField);

        mUpload = (Button) findViewById(R.id.UploadButton);




        mDatabase.child("users").child(userId).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        User user = dataSnapshot.getValue(User.class);

                        mFirstName.setText(user.getFirstName());
                        mLastName.setText(user.getLastName());
                        mEmail.setText(user.getEmail());
                        mArea.setText(user.getArea());
                        mCo2.setText(user.getCo2());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG,"get user:OnCancelled");
                    }
                }

        );


//        UserInfor userInfor = new UserInfor();

//        boolean bUid = snapshot.child("User Id").exists;
//        boolean bFirstName = dataSnapshot.getValue(User.class);
//        boolean bLastName = dataSnapshot.getValue(User.class);
//        boolean bArea = dataSnapshot.getValue(User.class);

        mUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String co2 = mCo2.getText().toString();
                String firstName = mFirstName.getText().toString();
                String lastName = mLastName.getText().toString();
                String email = mEmail.getText().toString();
                String area = mArea.getText().toString();

                //userInfor.updataUser(userId, firstName, lastName, email, co2, area);
                //new activity or stay in the same activity

                mDatabase.child("users").child(userId).child("firstName").setValue(firstName);
                mDatabase.child("users").child(userId).child("lastName").setValue(lastName);
                mDatabase.child("users").child(userId).child("email").setValue(email);
                mDatabase.child("users").child(userId).child("co2").setValue(co2);
                mDatabase.child("users").child(userId).child("area").setValue(area);
            }
        });

    }
}




//@IgnoreExtraProperties
class User {
    public String userId = FirebaseUser.getIdToken();
    public String firstName;
    public String lastName;
    public String email;
    public String co2;
    public String area;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    //initialize user data
    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.co2 = "0.0"; //default value for new user
        this.area = null; ////default value for new user
    }

    public String getFirstName(){return firstName;}
    public String getLastName(){return lastName;}
    public String getEmail(){return email;}
    public String getCo2(){return co2;}
    public String getArea(){return area;}

}



//// initialize user data with default value
//    private void writeNewUser(String userId,
//                              String firstName,
//                              String lastName,
//                              String email) {
//            //UserInfor userInfor = new UserInfor(firstName, lastName, email);
//            mDatabase.child("users").child(userId).child("firstName").setValue(firstName);
//            mDatabase.child("users").child(userId).child("lastName").setValue(lastName);
//            mDatabase.child("users").child(userId).child("co2").setValue(0.0);
//            mDatabase.child("users").child(userId).child("area").setValue(null);
//            mDatabase.child("users").child(userId).child("email").setValue(email);

//        available implementation of initial user data
//        mDatabase.child(userId).child("firstName").setValue(firstName);
//        mDatabase.child(userId).child("lastName").setValue(lastName);
//        mDatabase.child(userId).child("email").setValue(email);
//        mDatabase.child(userId).child("email").setValue(email);
//        mDatabase.child(userId).child("email").setValue(email);

//    //updata user data
//    private void updataUser(String userId,
//                              String firstName,
//                              String lastName,
//                              String email,
//                              String co2,
//                              String area) {
//        mDatabase.child("users").child(userId).child("firstName").setValue(firstName);
//        mDatabase.child("users").child(userId).child("lastName").setValue(lastName);
//        mDatabase.child("users").child(userId).child("email").setValue(email);
//        mDatabase.child("users").child(userId).child("co2").setValue(co2);
//        mDatabase.child("users").child(userId).child("area").setValue(area);
//    }

