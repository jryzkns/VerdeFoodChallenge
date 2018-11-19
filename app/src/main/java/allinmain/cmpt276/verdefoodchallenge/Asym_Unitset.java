package allinmain.cmpt276.verdefoodchallenge;

import java.util.ArrayList;
import java.util.List;

public class Asym_Unitset {
    int currentOffset;
    Asym_Unitset(){}
    public List<Asymmetric_Itemset> moarItems( int qty) {
        List<Asymmetric_Itemset> items = new ArrayList<>();

        for (int i = 0; i < qty; i++) {
            int colSpan = Math.random() < 0.2f ? 2 : 1;
            // Swap the next 2 lines to have items with variable
            // column/row span.
            // int rowSpan = Math.random() < 0.2f ? 2 : 1;
            int rowSpan = colSpan;
            Asymmetric_Itemset item = new Asymmetric_Itemset(colSpan, rowSpan, currentOffset + i);
            items.add(item);
        }

        currentOffset += qty;

        return items;
    }
}
