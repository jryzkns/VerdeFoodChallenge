package allinmain.cmpt276.verdefoodchallenge;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class Pledgelist extends ArrayAdapter<UserInformation> {

    private Activity context;
    private List<UserInformation> pledgeList;

    public Pledgelist(Activity context, List<UserInformation> pledgeList){
        super(context, R.layout.list_layout,pledgeList);
        this.context = context;
        this.pledgeList = pledgeList;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);

        TextView tvName = listViewItem.findViewById(R.id.listusername);
        TextView tvLocation = listViewItem.findViewById(R.id.listuserlocation);
        TextView tvPledge = listViewItem.findViewById(R.id.listuserPledge);

        UserInformation user = pledgeList.get(position);

        tvName.setText(" " + user.getNAME());
        tvLocation.setText("From: " + user.getLOCATION());
        tvPledge.setText("Pledged to save " + user.getPLEDGEAMOUNT().toString() + "T of CO2e!");

        return listViewItem;
    }
}
