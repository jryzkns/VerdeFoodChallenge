package allinmain.cmpt276.verdefoodchallenge;

import android.widget.ListAdapter;

import java.util.List;

public interface AsymmetricViewAdpater extends ListAdapter {

    void appendItems( List<Asymmetric_Itemset> newItems);

    void setItems( List<Asymmetric_Itemset> moreItems);
}
