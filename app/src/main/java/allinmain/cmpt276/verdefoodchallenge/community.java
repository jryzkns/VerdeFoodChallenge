package allinmain.cmpt276.verdefoodchallenge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class community extends Activity implements View.OnClickListener {

    private ListView listViewPledges;

    DatabaseReference DBPledges;

    List<UserInformation> pledgeList;
    List<UserInformation> localInfo;

    TextView num_pledges,ttl_pledges;

    Double ttl_pledge_val = 0d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);
        init();
    }

    private void init(){
//        ImageView about = findViewById(R.id.about_profile);
        TextView map_all=findViewById(R.id.all_map_community);
        TextView map_V=findViewById(R.id.V_map_community);
        TextView map_B=findViewById(R.id.B_map_community);
        TextView map_C=findViewById(R.id.C_map_community);
        TextView map_S=findViewById(R.id.S_map_community);
        TextView map_R=findViewById(R.id.R_map_community);
        TextView map_NW=findViewById(R.id.NW_map_community);

//        about.setOnClickListener(this);
        map_all.setOnClickListener(this);
        map_V.setOnClickListener(this);
        map_B.setOnClickListener(this);
        map_C.setOnClickListener(this);
        map_S.setOnClickListener(this);
        map_R.setOnClickListener(this);
        map_NW.setOnClickListener(this);


        listViewPledges = findViewById(R.id.listviewPledges);
        num_pledges = findViewById(R.id.num_pledges);
        ttl_pledges = findViewById(R.id.ttl_pledges);

        DBPledges = FirebaseDatabase.getInstance().getReference("USERINFO");
        pledgeList = new ArrayList<>();
        localInfo = new ArrayList<>();

        map_all.performClick(); //automatically shows all
    }

    @Override
    protected void onStart() {
        super.onStart();

        DBPledges.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                pledgeList.clear();
//                ttl_pledge_val = 0d;
                for(DataSnapshot pledgeSnapshot: dataSnapshot.getChildren()){
                    UserInformation user = pledgeSnapshot.getValue(UserInformation.class);
//                    ttl_pledge_val += new Double(user.getPLEDGEAMOUNT());
                    pledgeList.add(user);
                }
//
//                Pledgelist adapter = new Pledgelist(community.this, pledgeList);
//                listViewPledges.setAdapter(adapter);
//
////                // As per: req 3.2.4a
//                num_pledges.setText("Total Amount of Pledges: " + String.valueOf(pledgeList.size()));
//
////                // As per: req 3.2.4b
//                ttl_pledges.setText("Total Pledge Amounts to: " + String.valueOf(ttl_pledge_val));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }

    @Override
    public void onClick(View view) {

        ttl_pledge_val = 0d;

        localInfo.clear();

        ImageView map = findViewById(R.id.map_community);

        switch(view.getId()){

            case R.id.all_map_community:
                map.setBackgroundResource(R.drawable.gvancouver);
                for (UserInformation user : pledgeList) {
                    localInfo.add(user);
                    ttl_pledge_val += new Double(user.getPLEDGEAMOUNT());
                }
                break;

            case R.id.V_map_community:
                map.setBackgroundResource(R.drawable.vancouver);
                for (UserInformation user : pledgeList){
                    if (user.getLOCATION() == "VANCOUVER"){
                        localInfo.add(user);
                        ttl_pledge_val += new Double(user.getPLEDGEAMOUNT());
                    }
                }
                break;

            case R.id.B_map_community:
                map.setBackgroundResource(R.drawable.burnaby);
                for (UserInformation user : pledgeList){
                    if (user.getLOCATION() == "BURNABY"){
                        localInfo.add(user);
                        ttl_pledge_val += new Double(user.getPLEDGEAMOUNT());
                    }
                }
                break;

            case R.id.C_map_community:
                map.setBackgroundResource(R.drawable.coquitlam);
                for (UserInformation user : pledgeList){
                    if (user.getLOCATION() == "COQUITLAM"){
                        localInfo.add(user);
                        ttl_pledge_val += new Double(user.getPLEDGEAMOUNT());
                    }
                }
                break;

            case R.id.S_map_community:
                map.setBackgroundResource(R.drawable.surrey);
                for (UserInformation user : pledgeList){
                    if (user.getLOCATION() == "SURREY"){
                        localInfo.add(user);
                        ttl_pledge_val += new Double(user.getPLEDGEAMOUNT());
                    }
                }
                break;

            case R.id.R_map_community:
                map.setBackgroundResource(R.drawable.richmond);
                for (UserInformation user : pledgeList){
                    if (user.getLOCATION() == "RICHMOND"){
                        localInfo.add(user);
                        ttl_pledge_val += new Double(user.getPLEDGEAMOUNT());
                    }
                }
                break;

            case R.id.NW_map_community:
                map.setBackgroundResource(R.drawable.new_westminster);
                for (UserInformation user : pledgeList){
                    if (user.getLOCATION() == "NEW WESTMINSTER"){
                        localInfo.add(user);
                        ttl_pledge_val += new Double(user.getPLEDGEAMOUNT());
                    }
                }
                break;

//            case R.id.about_community:
//                Intent about =new Intent(community.this,ActivityAboutUs.class);
//                community.this.startActivity(about);
//                break;
        }

        Pledgelist adapter = new Pledgelist(community.this, localInfo);
        listViewPledges.setAdapter(adapter);
        if ( localInfo.size() > 0 ) {

            Log.d("jek",String.valueOf(localInfo.size()));
            // As per: req 3.2.4a
            num_pledges.setText("Total Amount of Pledges: " + String.valueOf(localInfo.size()));

            // As per: req 3.2.4b
            ttl_pledges.setText("Total Pledge Amounts to: " + String.valueOf(ttl_pledge_val));
        }
    }

}
