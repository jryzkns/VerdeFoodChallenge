package allinmain.cmpt276.verdefoodchallenge;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridViewAdapter extends BaseAdapter {
    private DataCenter dc=DataCenter.getInstance();
    private Context mContext;
    public GridViewAdapter( Context mContext)
    {
        this.mContext=mContext;
    }
    @Override
    public int getCount() {
        return dc.getFoodsSize();
    }

    @Override
    public Object getItem(int i) {
        return dc.getFood(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        if(convertView==null)
        {
            View view;
            final ViewHolder holder;
            LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = mInflater.inflate(R.layout.gridview_item, null);
            holder = new ViewHolder();

            holder.img=(ImageView) view.findViewById(R.id.img);
            holder.name = (TextView)view.findViewById(R.id.name);

            holder.img.setImageResource(dc.getFood(i).getResid());
            holder.name.setText(dc.getFood(i).getName());
            view.setTag(holder);
            return view;
        }else
        {
            ViewHolder holder;
            holder=(ViewHolder)convertView.getTag();
            holder.img.setImageResource(dc.getFood(i).getResid());
            holder.name.setText(dc.getFood(i).getName());
            convertView.setTag(holder);
            return convertView;
        }
    }
}
class ViewHolder{
    ImageView img;
    TextView name;

}
