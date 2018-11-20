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
    public List<Asymmetric_Itemset> DesigningItem( int qty) {
        List<Asymmetric_Itemset> items = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
//            if (i==0){
//                int colSpan=2;
//                int rowSpan = colSpan;
//                Asymmetric_Itemset item = new Asymmetric_Itemset(colSpan, rowSpan, currentOffset + i);
//            }
//            else if (i==6){
//                int colSpan=2;
//                int rowSpan = colSpan;
//                Asymmetric_Itemset item = new Asymmetric_Itemset(colSpan, rowSpan, currentOffset + i);
//            }
//            else{
//                int colSpan=1;
//                int rowSpan = colSpan;
//                Asymmetric_Itemset item = new Asymmetric_Itemset(colSpan, rowSpan, currentOffset + i);
//                items.add(item);
//            }
            int colSpan = i > 0.2f ? 1 : 2;
            int rowSpan = colSpan;
            Asymmetric_Itemset item = new Asymmetric_Itemset(colSpan, rowSpan, currentOffset + i);
            items.add(item);

        }
        // handing set up the number 8 item
        int colSpan = 2;
        int rowSpan = colSpan;
        Asymmetric_Itemset item = new Asymmetric_Itemset(colSpan, rowSpan, currentOffset + 8);
        items.add(item);

        currentOffset += qty;
        return items;
    }
}
