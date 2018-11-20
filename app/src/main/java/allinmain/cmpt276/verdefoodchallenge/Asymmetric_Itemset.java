package allinmain.cmpt276.verdefoodchallenge;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.felipecsl.asymmetricgridview.library.model.AsymmetricItem;

public class Asymmetric_Itemset implements AsymmetricItem {
    private int columnSpan;
    private int rowSpan;
    private int position;

    public Asymmetric_Itemset() {
        this(1, 1, 0);
    }

    public Asymmetric_Itemset(int columnSpan, int rowSpan, int position) {
        this.columnSpan = columnSpan;
        this.rowSpan = rowSpan;
        this.position = position;
    }
    public Asymmetric_Itemset(Parcel in) {
        readFromParcel(in);
    }

    @Override public int getColumnSpan() {
        return columnSpan;
    }

    @Override public int getRowSpan() {
        return rowSpan;
    }

    public int getPosition() {
        return position;
    }

    @Override public String toString() {
        return String.format("%s: %sx%s", position, rowSpan, columnSpan);
    }

    @Override public int describeContents() {
        return 0;
    }

    private void readFromParcel(Parcel in) {
        columnSpan = in.readInt();
        rowSpan = in.readInt();
        position = in.readInt();
    }

    @Override public void writeToParcel( @NonNull Parcel dest, int flags) {
        dest.writeInt(columnSpan);
        dest.writeInt(rowSpan);
        dest.writeInt(position);
    }

    /* Parcelable interface implementation */
    public static final Parcelable.Creator<Asymmetric_Itemset> CREATOR = new Parcelable.Creator<Asymmetric_Itemset>() {
        @Override public Asymmetric_Itemset createFromParcel( @NonNull Parcel in) {
            return new Asymmetric_Itemset(in);
        }

        @Override @NonNull public Asymmetric_Itemset[] newArray(int size) {
            return new Asymmetric_Itemset[size];
        }
    };
}
