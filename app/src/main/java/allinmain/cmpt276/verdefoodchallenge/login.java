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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class login extends Activity implements View.OnClickListener {

    // define layout object be used in login activity
    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView emailView;
    private TextView passwordView;
    private ProgressDialog progressDialog;
    private Button signupView;
    private FirebaseAuth firebaseAuth;

    /*@Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }*/

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
        setContentView(R.layout.activity_login);

        firebaseAuth =FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser()!=null){
            //profile activity here
            finish();
            Intent intent =new Intent(login.this,GreenFoodActivity.class);
            login.this.startActivity(intent);
        }

        // connect to layout objects
        progressDialog=new ProgressDialog(this);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        emailView = (TextView) findViewById(R.id.emailView);
        passwordView = (TextView) findViewById(R.id.passwordView);
        signupView = (Button) findViewById(R.id.signupView);

        // initialize onClick Listener
        signupView.setOnClickListener(this);
        // signupView.setOnClickListener(new view.onClick);
        buttonRegister.setOnClickListener(this);
        emailView.setOnClickListener(this);
        passwordView.setOnClickListener(this);

    }

    //methods for registering user
    private void registerUser(){
        String email= editTextEmail.getText().toString().trim();
        String password= editTextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            Toast.makeText(this,"Enter email",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this,"Enter password",Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()){
                            Toast.makeText(login.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
                            Intent intent =new Intent(login.this,ProfileInfor.class);
                            login.this.startActivity(intent);
                        }
                        else {
                            Toast.makeText(login.this,"Registered Failed, please try again...",Toast.LENGTH_SHORT).show();
                            //finish();
                        }
                    }
                });

    }

    //Onclick event for button register and sign up view
    @Override
    public void onClick(View view) {

        if (view==buttonRegister){
            registerUser();
            /*Intent intent =new Intent(login.this,GreenFoodActivity.class);
            login.this.startActivity(intent);*/
        }
        if (view==signupView){
            Intent intent1 =new Intent(login.this,SignUpActivity.class);
            login.this.startActivity(intent1);
        }

        /*switch (view.getId()){
            case R.id.buttonRegister:
                registerUser();
                *//*Intent intent =new Intent(login.this,GreenFoodActivity.class);
                login.this.startActivity(intent);*//*
                break;
            case R.id.signUp:
                Intent intent1 =new Intent(login.this,SignUpActivity.class);
                login.this.startActivity(intent1);
                break;
        }*/
    }
}
