package allinmain.cmpt276.verdefoodchallenge;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;



import java.util.List;

/**
 * Sample adapter implementation extending from AsymmetricGridViewAdapter<DemoItem> This is the
 * easiest way to get started.
 */
public class DefaultListAdapter extends ArrayAdapter<Asymmetric_Itemset> implements AsymmetricViewAdpater {

  private final LayoutInflater layoutInflater;

  public DefaultListAdapter( Context context, List<Asymmetric_Itemset> items) {
    super(context, 0, items);
    layoutInflater = LayoutInflater.from(context);
  }

  public DefaultListAdapter( Context context) {
    super(context, 0);
    layoutInflater = LayoutInflater.from(context);
  }

  @Override
  public View getView( int i, View convertView, @NonNull ViewGroup parent) {
    View v;
    Asymmetric_Itemset item = getItem(i);
    //Get the type of View that will be created by getView(int, View, ViewGroup) for the specified item.
    boolean isRegular = getItemViewType(i) == 0;
    //position==0
    if (convertView == null) {
//      if(position==0)
//      v = layoutInflater.inflate(
//              R.layout.adapter_item_even, parent, false);
//      else
       v = layoutInflater.inflate(
          isRegular ? R.layout.adapter_item_even : R.layout.adapter_item_odd, parent, false);
    } else {
      v = convertView;
    }

    TextView textView;
    if (isRegular) {
      textView = (TextView) v.findViewById(R.id.textview);
    } else {
      textView = (TextView) v.findViewById(R.id.textview_odd);
    }

    textView.setText(String.valueOf(item.getPosition()));

    return v;
  }

  @Override
  public int getViewTypeCount() {
    return 2;
  }

  @Override
  public int getItemViewType( int position) {
    return position % 2 == 0 ? 1 : 0;
  }

  public void appendItems(List<Asymmetric_Itemset> newItems) {
    addAll(newItems);
    notifyDataSetChanged();
  }

  public void setItems(List<Asymmetric_Itemset> moreItems) {
    clear();
    appendItems(moreItems);
  }
}