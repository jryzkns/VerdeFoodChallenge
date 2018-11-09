package allinmain.cmpt276.verdefoodchallenge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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


public class AdviceActivity extends Activity implements View.OnClickListener{
    private Button pledge;
    private TextView calinfo;
    private DataCenter dc=DataCenter.getInstance();
    private String PolicyType;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice);

        firebaseAuth =FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference();

        init();
    }
    //Initialization
    private void init()
    {
        pledge=(Button)this.findViewById(R.id.pledge);
        calinfo=(TextView)this.findViewById(R.id.calinfo);
        pledge.setOnClickListener(this);
        Intent intent= this.getIntent();
        PolicyType=intent.getStringExtra("Strategy");

        calinfo.setText(dc.getSuggestionInfo(PolicyType));

    }
    //Exit activity
    @Override
    public void onClick(View view){

        String pledgeval = Double.toString(dc.calSuggestCo2()/1000*-52);
        final String[] pledgername = {""};
        final String[] pledgerloc = {""};

        final FirebaseUser current_user = firebaseAuth.getCurrentUser();

        final DatabaseReference dbPledges = FirebaseDatabase.getInstance().getReference("USERINFO");

        dbPledges.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                pledgername[0] = dataSnapshot.child(current_user.getUid()).child("name").getValue(String.class);

                String pledgeval = Double.toString(dc.calSuggestCo2()/1000*-52);
                UserInformation testUser = dataSnapshot.child(current_user.getUid()).getValue(UserInformation.class);

                UserInformation pledger = new UserInformation(testUser.getNAME(), testUser.getLOCATION(),"0",pledgeval);

                dbPledges.child(current_user.getUid()).setValue(pledger);

                Log.d("jek",testUser.getLOCATION());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Intent intent =new Intent(AdviceActivity.this,community.class);
        AdviceActivity.this.startActivity(intent);
        this.finish();

    }
}
