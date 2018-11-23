package allinmain.cmpt276.verdefoodchallenge;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends Activity implements View.OnClickListener {

    // define layout objects which will be used
    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView emailView;
    private TextView passwordView;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    /*@Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        firebaseAuth.addAuthStateListener((FirebaseAuth.AuthStateListener) currentUser);
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firebaseAuth =FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);

        //connect to layout objects
        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        emailView = (TextView) findViewById(R.id.emailView);
        passwordView = (TextView) findViewById(R.id.passwordView);

        //initialize onclick listeners
        buttonRegister.setOnClickListener(this);
        emailView.setOnClickListener(this);
        passwordView.setOnClickListener(this);

    }



    //register User methods
    private void registerUser(){
        String email= editTextEmail.getText().toString();
        String password= editTextPassword.getText().toString();


        if (TextUtils.isEmpty(email)){
            Toast.makeText(this,"Enter email",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this,"Enter password",Toast.LENGTH_SHORT).show();
            return;
        }


            progressDialog.setMessage("Registing User...");
            progressDialog.show();
            firebaseAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();
                            if (task.isSuccessful()){

                                //FirebaseUser user = firebaseAuth.getCurrentUser();
                                Toast.makeText(SignUpActivity.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
                                Intent intent =new Intent(SignUpActivity.this,ProfileInfor.class);
                                SignUpActivity.this.startActivity(intent);

                            }
                            else {
                                Toast.makeText(SignUpActivity.this,"Registered Failed, please try again...",Toast.LENGTH_SHORT).show();
                                //finish();
                            }
                        }
                    });
        }




    //onclick listener events for buttonRegister
    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.buttonRegister:
                /*Intent intent =new Intent(SignUpActivity.this,GreenFoodActivity.class);
                SignUpActivity.this.startActivity(intent);*/
                registerUser();
                break;
        }
    }
}