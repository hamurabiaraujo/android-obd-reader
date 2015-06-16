package pt.lighthouselabs.obd.reader.manufacturer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ManufacturerOpenHelper extends SQLiteOpenHelper{

    /// tag for logging
    private static final String TAG = ManufacturerOpenHelper.class.getName();

    public ManufacturerOpenHelper(Context context) {
        super(context, ManufacturerLog.DATABASE_NAME, null, ManufacturerLog.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        execSQL(db, ManufacturerLog.DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void execSQL(SQLiteDatabase db, String[] statements) {
        final String tag = TAG + ".execSQL()";
        for (String sql : statements) {
            Log.d(tag, sql);
            db.execSQL(sql);
        }
    }
}