package allinmain.cmpt276.verdefoodchallenge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ActivityAboutUs extends Activity implements OnClickListener {

    private Button iknow,share;

    private Intent shareIntent;

    private String shareBody = "I am making **positive** change on the world with this app," +
            " and I have pledged to reduce " +
            "<VALUE>" +
            " of CO2e! Come join me!";

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);

        init();
    }
    private void init()
    {
        iknow=(Button)this.findViewById(R.id.iknow);
        iknow.setOnClickListener(this);


        // As per Requirement 3.2.5
        share=(Button)this.findViewById(R.id.share);
 //       share.setOnClickListener(this);
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

                shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("test/pain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Verde Food Challenge!");
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(shareIntent, "Share to: "));
            }
        });

    }

        @Override
    public void onClick(View view) {
        this.finish();
    }
}
