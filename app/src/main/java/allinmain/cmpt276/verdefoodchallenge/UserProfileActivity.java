package allinmain.cmpt276.verdefoodchallenge;

import android.app.Activity;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UserProfileActivity extends Activity {

    private EditText mCo2;
    private EditText mFirstName;
    private EditText mLastName;
    private EditText mArea;

    private Button mUpload;

    private Firebase mRef;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);



        mCo2= (EditText) findViewById(R.id.CO2Field);
        mFirstName= (EditText) findViewById(R.id.FirstNameField);
        mLastName= (EditText) findViewById(R.id.LastNameField);
        mArea= (EditText) findViewById(R.id.AreaField);

        mUpload = (Button) findViewById(R.id.UploadButton);


        mUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String co2 = mCo2.getText().toString();
                String firstName = mFirstName.getText().toString();
                String lastName = mLastName.getText().toString();
                String area = mArea.getText().toString();

                Firebase Co2Attribute = mRef.child("CO2");
                Firebase firstNameAttribute = mRef.child("first name");
                Firebase lastNameAttribute = mRef.child("last name");
                Firebase areaAttribute = mRef.child("area");

                Co2Attribute.setValue(co2);
                firstNameAttribute.setValue(firstName);
                lastNameAttribute.setValue(lastName);
                areaAttribute.setValue(area);

            }
        });



    }
}
