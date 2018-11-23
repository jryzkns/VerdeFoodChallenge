package allinmain.cmpt276.verdefoodchallenge;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
//import android.support.design.widget.BottomNavigationView;
//import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends Activity implements View.OnClickListener, bottom_bar.OnFragmentInteractionListener {

    //private TextView mTextMessage;

    private Intent shareIntent;

    private String shareBody = "I am making **positive** change on the world with this app," +
            " and I have pledged to reduce " +
            "<VALUE>" +
            " of CO2e! Come join me!";

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        FragmentManager fm = getFragmentManager();
        bottom_bar fragment = new bottom_bar();
        fm.beginTransaction().add(R.id.bottom_bar_frame,fragment).commit();

        init();
    }

    private void init(){
        ImageView about = findViewById(R.id.about_profile);
        TextView manage= findViewById(R.id.manage_Profile);
        TextView share= findViewById(R.id.share_Profile);
        TextView logout= findViewById(R.id.logout_Profile);
        about.setOnClickListener(this);
        manage.setOnClickListener(this);
        logout.setOnClickListener(this);

        //response to share button
        shareMethod();

    }


    public void shareMethod()
    {
        // share button listener
        TextView share= findViewById(R.id.share_Profile);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                firebaseAuth =FirebaseAuth.getInstance();
                FirebaseUser user = firebaseAuth.getCurrentUser();
                DatabaseReference sharerefDB = FirebaseDatabase.getInstance().getReference("USERINFO").child(user.getUid()).child("pledgeamount");
//                DatabaseReference sharerefDB = FirebaseDatabase.getInstance().getReference("USERINFO").child(user.getUid()).child("PLEDGEAMOUNT");

                sharerefDB.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        Log.d("jek","hit");

                        shareBody = "I am making **positive** change on the world with this app," +
                                " and I have pledged to reduce " +
                                dataSnapshot.getValue(String.class) +
                                "Tonnes of CO2e! Come join me!";

                        shareIntent = new Intent(Intent.ACTION_SEND);
                        shareIntent.setType("test/pain");
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Verde Food Challenge!");
                        shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                        startActivity(Intent.createChooser(shareIntent, "Share to: "));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

//                shareIntent = new Intent(Intent.ACTION_SEND);
//                shareIntent.setType("test/pain");
//                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Verde Food Challenge!");
//                shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
//                startActivity(Intent.createChooser(shareIntent, "Share to: "));
            }
        });
    }


    //onClick action
    public void onClick(View view ){
        //view.setBackgroundResource(R.color.buttonbar_change);
        Intent intent;

        switch(view.getId()){
            case R.id.about_profile:
                Intent about =new Intent(Profile.this,ActivityAboutUs.class);
                Profile.this.startActivity(about);
                break;
            case R.id.manage_Profile:
                Intent manage=new Intent(Profile.this,Manage_Profile.class);
                Profile.this.startActivity(manage);
                break;
            case R.id.share_Profile:

                break;
            case R.id.logout_Profile:
                FirebaseAuth.getInstance().signOut();
                Intent i=new Intent(Profile.this,login.class);
                Profile.this.startActivity(i);
                break;

        }


    }

    @Override
    public void onFragmentInteraction() {

    }
}
