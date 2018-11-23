package allinmain.cmpt276.verdefoodchallenge;

import android.app.Activity;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.grpc.Context;

public class Meallist  extends ArrayAdapter<meal> {

    private Activity context;
    private List<meal> mealList;

    public Meallist(Activity context, List<meal> mealList){
        super(context, R.layout.meallist_layout,mealList);
        this.context = context;
        this.mealList = mealList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.meallist_layout, null, true);

        ImageView pic = listViewItem.findViewById(R.id.listmealPic);
        TextView name = listViewItem.findViewById(R.id.listmealName);


        meal meal_this = mealList.get(position);


        name.setText(meal_this.mName);


        TextView Res = listViewItem.findViewById(R.id.listmealRes);
        Res.setText("Restaurant: " + meal_this.mrName);
        TextView Loc = listViewItem.findViewById(R.id.listmealLoc);
        Loc.setText("Location: "+ meal_this.mrLoc);
        TextView Pro = listViewItem.findViewById(R.id.listmealPro);
        Pro.setText("Main Protein: "+ meal_this.mProtein);



        //need a way to get firebase uri into picasso
        Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(pic);

//        Picasso.get().load(Uri.parse(meal_this.getmImg())).into(pic);

//        official firebase way of doing this but i don't think i know how to import glideapp
//        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
//        GlideApp.with(this /* context */)
//                .load(storageReference)
//                .into(pic);

        return listViewItem;


    }
}
