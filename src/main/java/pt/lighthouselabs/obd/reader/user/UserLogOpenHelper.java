package pt.lighthouselabs.obd.reader.user;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class UserLogOpenHelper extends SQLiteOpenHelper {
    /// tag for logging
    private static final String TAG = UserLogOpenHelper.class.getName();

    public UserLogOpenHelper(Context context) {
        super(context, UserLog.DATABASE_NAME, null, UserLog.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        execSQL(db, UserLog.DATABASE_CREATE);
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
