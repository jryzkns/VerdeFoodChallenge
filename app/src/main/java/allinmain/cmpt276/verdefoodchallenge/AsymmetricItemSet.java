package allinmain.cmpt276.verdefoodchallenge;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.felipecsl.asymmetricgridview.library.model.AsymmetricItem;

public class AsymmetricItemSet implements AsymmetricItem {
    private int columnSpan;
    private int rowSpan;
    private int position;

    public AsymmetricItemSet() {
        this(1, 1, 0);
    }

    public AsymmetricItemSet(int columnSpan, int rowSpan, int position) {
        this.columnSpan = columnSpan;
        this.rowSpan = rowSpan;
        this.position = position;
    }

    public AsymmetricItemSet(Parcel in) {
        readFromParcel(in);
    }

    public int getColumnSpan() {
        return columnSpan;
    }

    public int getRowSpan() {
        return rowSpan;
    }

    public int getPosition() {
        return position;
    }

    public String toString() {
        return String.format("%s: %sx%s", position, rowSpan, columnSpan);
    }

    public int describeContents() {
        return 0;
    }

    private void readFromParcel(Parcel in) {
        columnSpan = in.readInt();
        rowSpan = in.readInt();
        position = in.readInt();
    }

    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(columnSpan);
        dest.writeInt(rowSpan);
        dest.writeInt(position);
    }

    /* Parcelable interface implementation */
    public static final Parcelable.Creator<AsymmetricItem> CREATOR = new Parcelable.Creator<AsymmetricItem>() {
        @Override public AsymmetricItem createFromParcel(@NonNull Parcel in) {
            return new AsymmetricItemSet(in);
        }

        @Override @NonNull public AsymmetricItem[] newArray(int size) {
            return new AsymmetricItemSet[size];
        }
    };
}
