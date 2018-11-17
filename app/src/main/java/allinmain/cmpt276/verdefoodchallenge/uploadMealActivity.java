package allinmain.cmpt276.verdefoodchallenge;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;
import java.util.UUID;

public class uploadMealActivity extends Activity implements View.OnClickListener {

    private Button submit,choosePhoto, imageCamera;
    private EditText mName,mProtein,mRestName,mRestLoc,mDesc;
    private Bitmap meal_image = null;
    private ImageView preview;
    private FirebaseFirestore db;

    private FirebaseStorage storage;
    private StorageReference storageReference;

    private Uri filepath = Uri.EMPTY;

    //String representation of uri linked to default image
    private String defaultimg = "gs://greenfood-challenge.appspot.com/images/default.jpg";

    private final int PICK_IMAGE_REQUEST = 71;
    private final int TAKE_IMAGE_REQUEST = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uploadmeal);

        db = FirebaseFirestore.getInstance();

        mName = findViewById(R.id.ETmeal_name);
        mProtein = findViewById(R.id.ETmeal_protein);
        mRestName = findViewById(R.id.ETmealrest_name);
        mRestLoc = findViewById(R.id.ETmeal_loc);
        mDesc = findViewById(R.id.ETmeal_desc);

        submit = findViewById(R.id.submit_meal);
        submit.setOnClickListener(this);

        choosePhoto = findViewById(R.id.image_upload);
        choosePhoto.setOnClickListener(this);

        imageCamera = findViewById(R.id.image_camera);
        imageCamera.setOnClickListener(this);

        preview = findViewById(R.id.image_preview);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.submit_meal:

                final String meal_name = mName.getText().toString().trim();
                final String meal_protein = mProtein.getText().toString().trim();
                final String meal_Restaurant_Name = mRestName.getText().toString().trim();
                final String meal_Restaurant_Location = mRestLoc.getText().toString().trim();
                final String meal_Description = mDesc.getText().toString().trim();

                //very naive approach to catch for empty submissions
                if (    meal_name.equals("") || meal_protein.equals("") ||
                        meal_Restaurant_Name.equals("") || meal_Restaurant_Location.equals("")){
                    Toast.makeText(this,"You have left some fields blank!",Toast.LENGTH_LONG).show();
                    break;
                }

                if (!filepath.equals(Uri.EMPTY)) {

                    String identifier = UUID.randomUUID().toString();
                    StorageReference ref = storageReference.child("images/" + identifier);
                    ref.putFile(filepath);

                    String constructed_uri = "gs://greenfood-challenge.appspot.com/images/" + identifier;

                    upload_meal(meal_name, meal_protein, meal_Restaurant_Name,
                            meal_Restaurant_Location, meal_Description, constructed_uri);

                }else{

                    upload_meal(meal_name, meal_protein, meal_Restaurant_Name,
                            meal_Restaurant_Location, meal_Description, defaultimg);

                }
                break;

            case R.id.image_camera:
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent,TAKE_IMAGE_REQUEST);
                break;

            case R.id.image_upload:
                Intent imageSelectIntent = new Intent();
                imageSelectIntent.setType("image/*");
                imageSelectIntent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(imageSelectIntent,"Select Picture"),PICK_IMAGE_REQUEST);
                break;
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){

            filepath = data.getData();

            if (requestCode == PICK_IMAGE_REQUEST){
                try{
                    meal_image = MediaStore.Images.Media.getBitmap(getContentResolver(),filepath);
                }catch(IOException e){
                    e.printStackTrace();
                }
            } else if (requestCode == TAKE_IMAGE_REQUEST){
                meal_image = (Bitmap)data.getExtras().get("data");
            }

            preview.setImageBitmap(meal_image);
        }

    }

    private void upload_meal(String mN, String mP, String mrN, String mrL, String mD, String mDL){
        meal new_meal = new meal(mN,mP,mrN,mrL,mD,mDL);
        db.collection("meals").add(new_meal);
        this.finish();
    }
}