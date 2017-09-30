package jamk.l3326.excercise2;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class ItemCursorAdapter extends CursorAdapter {
    public ItemCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tName = (TextView)view.findViewById(R.id.name);
        TextView tQuantity = (TextView)view.findViewById(R.id.quantity);
        TextView tPrice = (TextView)view.findViewById(R.id.price);

        String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseOpenHelper.NAME));
        int quantity = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseOpenHelper.QUANTITY));
        double price = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseOpenHelper.PRICE));

        tName.setText(name);
        tQuantity.setText(String.valueOf(quantity));
        tPrice.setText(String.valueOf(price));
    }
}
