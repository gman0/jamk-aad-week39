package jamk.l3326.excercise2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpenHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "shopping_list_db";
    public static final String NAME = "name";
    public static final String QUANTITY = "quantity";
    public static final String PRICE = "price";
    public static final String[] COLUMNS = new String[]{"_id", NAME, QUANTITY, PRICE};

    public DatabaseOpenHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME +
                    "(" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    NAME + " TEXT," +
                    QUANTITY + " INTEGER," +
                    PRICE + " REAL" +
                    ");");

        ContentValues values = new ContentValues();
        values.put(NAME, "Olut");
        values.put(QUANTITY, 24);
        values.put(PRICE, 2.64);
        long id = db.insert(TABLE_NAME, null, values);
        System.out.println("INSERT ID " + id);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
