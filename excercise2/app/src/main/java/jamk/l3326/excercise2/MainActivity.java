package jamk.l3326.excercise2;

import android.app.DialogFragment;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AddNewItemDialogFragment.DialogListener {

    private SQLiteDatabase db;
    private ListView listView;
    private ItemCursorAdapter listAdapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.listView);
        listView.setLongClickable(true);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                db.delete(DatabaseOpenHelper.TABLE_NAME, "_id=?", new String[]{String.valueOf(l)});
                updateList();
                return true;
            }
        });

        db = (new DatabaseOpenHelper(this)).getWritableDatabase();

        listAdapter = new ItemCursorAdapter(this, getCursor());
        listView.setAdapter(listAdapter);

        updateList();
    }

    private Cursor getCursor() {
        return db.rawQuery("SELECT * FROM " + DatabaseOpenHelper.TABLE_NAME, null);
    }

    private void updateList() {
        Cursor c = getCursor();
        listAdapter.changeCursor(c);

        final int quantityIdx = c.getColumnIndexOrThrow(DatabaseOpenHelper.QUANTITY);
        final int priceIdx = c.getColumnIndexOrThrow(DatabaseOpenHelper.PRICE);

        TextView tvTotal = (TextView)findViewById(R.id.totalPrice);

        double total = 0;
        if (c.moveToFirst()) {
            do {
                total += c.getInt(quantityIdx) * c.getDouble(priceIdx);
            } while (c.moveToNext());

            tvTotal.setText("Total price " + total + "eur");
        } else
            tvTotal.setText("No items");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_item:
                AddNewItemDialogFragment dialogFragment = new AddNewItemDialogFragment();
                dialogFragment.show(getFragmentManager(), "Add new item");
        }

        return false;
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialogFragment, String name, int q, double p) {
        ContentValues values = new ContentValues(3);
        values.put(DatabaseOpenHelper.NAME, name);
        values.put(DatabaseOpenHelper.QUANTITY, q);
        values.put(DatabaseOpenHelper.PRICE, p);
        db.insert(DatabaseOpenHelper.TABLE_NAME, null, values);

        updateList();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialogFragment) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        listAdapter.getCursor().close();
        db.close();
    }
}
