package allinmain.cmpt276.verdefoodchallenge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;

import android.widget.RadioButton;
import android.widget.Adapter;
import android.widget.Toast;

import com.felipecsl.asymmetricgridview.library.Utils;
import com.felipecsl.asymmetricgridview.library.model.AsymmetricItem;
import com.felipecsl.asymmetricgridview.library.widget.AsymmetricGridView;
import com.felipecsl.asymmetricgridview.library.widget.AsymmetricGridViewAdapter;
import com.google.firebase.auth.FirebaseAuth;



import java.util.ArrayList;
import java.util.List;



public class MainActivity extends Activity implements CompoundButton.OnCheckedChangeListener, AdapterView.OnItemClickListener {
    ///private RadioButton malerb,femalerb;
    private SharedPreferences sp ;
    private Button start;

    private AsymmetricViewAdpater adapter;
    private AsymmetricGridView listView;
    private final Asym_Unitset resetutils = new Asym_Unitset();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (AsymmetricGridView) findViewById(R.id.listView);

        // Choose your own preferred column width
        listView.setRequestedColumnWidth(Utils.dpToPx(this, 120));
        final List<AsymmetricItem> items = new ArrayList<>();

        // initialize your items array


        if (savedInstanceState == null) {
            //I set the Design Item number independently to mack share the number will not be affect by older
            adapter = new DefaultListAdapter(this, resetutils.DesigningItem(9));
        }
        else {
            adapter = new DefaultListAdapter(this);
        }

        listView.setRequestedColumnCount(3);
        listView.setRequestedHorizontalSpacing(Utils.dpToPx(this, 3));
        listView.setAdapter(getNewAdapter());
        //listView.setDebugging(true);
        listView.setOnItemClickListener(this);

    }
    private AsymmetricGridViewAdapter getNewAdapter() {
        return new AsymmetricGridViewAdapter(this, listView, adapter);
    }
    @Override protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("currentOffset", resetutils.currentOffset);
        outState.putInt("itemCount", adapter.getCount());
        for (int i = 0; i < adapter.getCount(); i++) {
            outState.putParcelable("item_" + i, (Parcelable) adapter.getItem(i));
        }
    }
    @Override protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        resetutils.currentOffset = savedInstanceState.getInt("currentOffset");
        int count = savedInstanceState.getInt("itemCount");
        List<Asymmetric_Itemset> items = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            items.add((Asymmetric_Itemset) savedInstanceState.getParcelable("item_" + i));
        }
        adapter.setItems(items);
    }
    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.aymmetric_menu, menu);
        return true;
    }
    @Override public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.one_column) {
            //connect to the menu information (id directed)
            setNumColumns(1);
        } else if (id == R.id.two_columnns) {
            setNumColumns(2);

        } else if (id == R.id.three_columns) {
            setNumColumns(3);
        } else if (id == R.id.four_columns) {
            setNumColumns(4);
        } else if (id == R.id.five_columns) {
            setNumColumns(5);
        } else if (id == R.id.onetwenty_dp_columns) {
            setColumnWidth(120);
        } else if (id == R.id.twoforty_dp_columns) {
            setColumnWidth(240);
        } else if (id == R.id.append_items) {
            adapter.appendItems(resetutils.moarItems(50));
        } else if (id == R.id.reset_items) {
            resetutils.currentOffset = 0;
            adapter.setItems(resetutils.moarItems(50));
        } else if (id == R.id.reordering) {
            listView.setAllowReordering(!listView.isAllowReordering());
            item.setTitle(listView.isAllowReordering() ? "Prevent reordering" : "Allow reordering");
        } else if (id == R.id.debugging) {
            int index = listView.getFirstVisiblePosition();
            View v = listView.getChildAt(0);
            int top = (v == null) ? 0 : v.getTop();

            listView.setDebugging(!listView.isDebugging());
            item.setTitle(listView.isDebugging() ? "Disable debugging" : "Enable debugging");
            listView.setAdapter(adapter);

            listView.setSelectionFromTop(index, top);
        } else if (id == android.R.id.home) {
//            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    private void setNumColumns(int numColumns) {
        listView.setRequestedColumnCount(numColumns);
        listView.determineColumns();
        listView.setAdapter(getNewAdapter());
    }

    private void setColumnWidth(int columnWidth) {
        listView.setRequestedColumnWidth(Utils.dpToPx(this, columnWidth));
        listView.determineColumns();
        listView.setAdapter(getNewAdapter());
    }

    private void init()
    {
        sp = getSharedPreferences("User", Context.MODE_PRIVATE);
        if(sp.getInt("gender",-1)!=-1)
        {
           // this.startActivity(new Intent(this,GreenFoodActivity.class));
            //this.finish();
        };
        //malerb=(RadioButton)this.findViewById(R.id.malerb);
        //femalerb=(RadioButton)this.findViewById(R.id.femalerb);
        start=(Button)this.findViewById(R.id.start);
        //malerb.setOnCheckedChangeListener(this);
        //femalerb.setOnCheckedChangeListener(this);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.startActivity(new Intent(MainActivity.this,login.class));
            }
        });
    }
    public void onItemClick(@NonNull AdapterView<?> parent, @NonNull View view,
                                      int position, long id) {

        if (position==0){// The item 0 is the personal account information
            //Toast.makeText(this, "Item " + position + " clicked", Toast.LENGTH_SHORT).show();
            MainActivity.this.startActivity(new Intent(MainActivity.this,Profile.class));

            //MainActivity.this.finish();
        }
        else if (position==1){// target to the meal planning
            // The item 0 is the personal account information
            //Toast.makeText(this, "Item " + position + " clicked", Toast.LENGTH_SHORT).show();
            MainActivity.this.startActivity(new Intent(MainActivity.this,GreenFoodActivity.class));
            //MainActivity.this.finish();
        }

        else if (position==2){//upcoming information
            Toast.makeText(this, "Weekly plan coming soon", Toast.LENGTH_SHORT).show();
//            MainActivity.this.startActivity(new Intent(MainActivity.this,Co2CalActivity.class));
        }
        else if (position==3){//upcoming information: the weekly history week have
//            Toast.makeText(this, "Weekly History coming soon", Toast.LENGTH_SHORT).show();
            MainActivity.this.startActivity(new Intent(MainActivity.this,login.class));

        }
        else if (position==4){//upcoming information: the weekly history week have
//            Toast.makeText(this, "Weekly History coming soon", Toast.LENGTH_SHORT).show();
            MainActivity.this.startActivity(new Intent(MainActivity.this,community.class));
        }
        else if (position==5){//upcoming information: Suggestion / empty? not?
//            Toast.makeText(this, "Weekly History coming soon", Toast.LENGTH_SHORT).show();
            MainActivity.this.startActivity(new Intent(MainActivity.this,SuggestionType.class));

        }
        else if (position==6){//upcoming information: Share activity
            Toast.makeText(this, "Share function coming soon", Toast.LENGTH_SHORT).show();
//            shareMethod();
//            MainActivity.this.startActivity(new Intent(MainActivity.this,Profile.class));


        }
        else if (position==7){//upcoming information: the weekly history week have
//            Toast.makeText(this, "Weekly History coming soon", Toast.LENGTH_SHORT).show();
            FirebaseAuth.getInstance().signOut();
            Intent i=new Intent(MainActivity.this,login.class);

        }
        else if (position==8){// The result to the one previous setting have (if there is not reset info
            //Toast.makeText(this, "Item " + position + " clicked", Toast.LENGTH_SHORT).show();
            MainActivity.this.startActivity(new Intent(MainActivity.this,Co2CalActivity.class));
        }
        else
        Toast.makeText(this, "Item " + position + " clicked", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        SharedPreferences.Editor edit = sp.edit();
        /*switch (compoundButton.getId())
        {
            case R.id.malerb:
                if(b)
                {
                    edit.putInt("gender",1);
                    edit.commit();
                }
                break;
            case R.id.femalerb:
                edit.putInt("gender",0);
                edit.commit();
                break;
        }*/
    }

    @Override
    public void onPointerCaptureChanged( boolean hasCapture ) {

    }
}
