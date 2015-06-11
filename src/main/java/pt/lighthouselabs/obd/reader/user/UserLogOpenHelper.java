package pt.lighthouselabs.obd.reader.user;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hamurabi on 6/11/15.
 */
public class UserLogOpenHelper extends SQLiteOpenHelper {
    public static final String NOME_BANCO = "AutoBoard";
    public static final int VERSAO_BANCO = 1;
    public static final String USER_TABLE = "CREATE TABLE USER ( " +
            "ID INTEGER NOT NULL PRIMARY KEY, " +
            "NOME TEXT NOT NULL, " +
            "EMAIL TEXT NOT NULL, " +
            "SENHA TEXT NOT NULL," +
            "IDCARRO INTEGER);";

    public UserLogOpenHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS USER");
        onCreate(db);
    }
}
