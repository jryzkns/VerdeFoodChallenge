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

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;
import java.util.UUID;

public class uploadMealActivity extends Activity implements View.OnClickListener {

    private Button submit,choosePhoto;
    private EditText mName,mProtein,mRestName,mRestLoc,mDesc;
    private Bitmap meal_image = null;
    private ImageView preview;
    private FirebaseFirestore db;

    private FirebaseStorage storage;
    private StorageReference storageReference;

    private Uri filepath;

    private final int PICK_IMAGE_REQUEST = 71;

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

        preview = findViewById(R.id.image_preview);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.submit_meal:

                //need catch for empty results!

                String meal_name = mName.getText().toString().trim();
                String meal_protein = mProtein.getText().toString().trim();
                String meal_Restaurant_Name = mRestName.getText().toString().trim();
                String meal_Restaurant_Location = mRestLoc.getText().toString().trim();
                String meal_Description = mDesc.getText().toString().trim();

                meal new_meal = new meal(meal_name,meal_protein,meal_Restaurant_Name,meal_Restaurant_Location,meal_Description);

                StorageReference ref = storageReference.child("images/"+ UUID.randomUUID().toString());
                ref.putFile(filepath);

                db.collection("meals").add(new_meal);

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

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null){
            filepath = data.getData();
            try{
                meal_image = MediaStore.Images.Media.getBitmap(getContentResolver(),filepath);
                preview.setImageBitmap(meal_image);
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}