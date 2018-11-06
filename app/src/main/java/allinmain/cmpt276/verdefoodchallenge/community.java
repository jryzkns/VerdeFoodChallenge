package allinmain.cmpt276.verdefoodchallenge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
import java.util.List;

public class community extends Activity implements View.OnClickListener {

    private ListView listViewPledges;

    DatabaseReference DBPledges;

    List<UserInformation> pledgeList;

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

        DBPledges = FirebaseDatabase.getInstance().getReference("USERINFO");

        pledgeList = new ArrayList<>();

    }

    @Override
    protected void onStart() {
        super.onStart();

        DBPledges.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                pledgeList.clear();
                for(DataSnapshot pledgeSnapshot: dataSnapshot.getChildren()){
                    UserInformation user = pledgeSnapshot.getValue(UserInformation.class);

                    pledgeList.add(user);
                }

                Pledgelist adapter = new Pledgelist(community.this, pledgeList);
                listViewPledges.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        ImageView map = findViewById(R.id.map_community);
        switch(view.getId()){
            case R.id.all_map_community:
                map.setBackgroundResource(R.drawable.gvancouver);
                break;
            case R.id.V_map_community:
                map.setBackgroundResource(R.drawable.vancouver);
                break;
            case R.id.B_map_community:
                map.setBackgroundResource(R.drawable.burnaby);
                break;
            case R.id.C_map_community:
                map.setBackgroundResource(R.drawable.coquitlam);
                break;
            case R.id.S_map_community:
                map.setBackgroundResource(R.drawable.surrey);
                break;
            case R.id.R_map_community:
                map.setBackgroundResource(R.drawable.richmond);
                break;
            case R.id.NW_map_community:
                map.setBackgroundResource(R.drawable.new_westminster);
                break;
//            case R.id.about_community:
//                Intent about =new Intent(community.this,ActivityAboutUs.class);
//                community.this.startActivity(about);
//                break;
        }

    }

    //TODO watch video on how to pull data from DB
    //TODO write the calculation algorithm


}
