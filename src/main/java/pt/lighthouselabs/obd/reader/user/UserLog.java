package pt.lighthouselabs.obd.reader.user;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import pt.lighthouselabs.obd.reader.car.Car;

public class UserLog {

    /// the database version number
    public static final int DATABASE_VERSION = 1;
    /// the name of the database
    public static final String DATABASE_NAME = "autoboard";
    /// a tag string for debug logging (the name of this class)
    private static final String TAG = UserLog.class.getName();
    /// database table names
    private static final String USERS_TABLE = "Users";
    /// SQL commands to delete the database
    public static final String[] DATABASE_DELETE = new String[]{
            "drop table if exists " + USERS_TABLE + ";",
    };
    /// column names for RECORDS_TABLE
    private static final String USER_ID = "id";
    private static final String USER_NAME = "name";
    private static final String USER_EMAIL = "email";
    private static final String USER_PASSWORD = "senha";
    private static final String USER_ID_CAR = "idCar";

    /// array of all column names for RECORDS_TABLE
    private static final String[] USERS_TABLE_COLUMNS = new String[]{
            USER_ID,
            USER_NAME,
            USER_EMAIL,
            USER_PASSWORD,
            USER_ID_CAR
    };
    /// SQL commands to create the database
    public static final String[] DATABASE_CREATE = new String[]{
            "create table " + USERS_TABLE + " ( " +
                    USER_ID + " text primary key not null, " +
                    USER_NAME + " text not null, " +
                    USER_EMAIL + " text not null, " +
                    USER_PASSWORD + " text not null, " +
                    USER_ID_CAR+ " integer" +
                    ");"
    };

    private SQLiteDatabase bd;

    public UserLog(Context context) {
        UserLogOpenHelper userLogOpenHelper = new UserLogOpenHelper(context);
        bd = userLogOpenHelper.getWritableDatabase();
    }

    public void inserir (User u) {
        ContentValues valores = new ContentValues();
        valores.put(USER_ID, u.getIdGoogle());
        valores.put(USER_NAME, u.getName());
        valores.put(USER_EMAIL, u.getEmail());
        valores.put(USER_PASSWORD, u.getPassword());
        valores.put(USER_ID_CAR, u.getCar());

        bd.insert(USERS_TABLE, null, valores);
    }

    public void atualizar (User u) {
        ContentValues valores = new ContentValues();
        valores.put(USER_ID, u.getIdGoogle());
        valores.put(USER_NAME, u.getName());
        valores.put(USER_EMAIL, u.getEmail());
        valores.put(USER_PASSWORD, u.getPassword());
        valores.put(USER_ID_CAR, u.getCar());

        bd.update(USERS_TABLE, valores, USER_ID + " = ?", new String[]{"" + u.getIdGoogle()});
    }

    public void remover (User u) {
        String[] id = {String.valueOf(u.getIdGoogle())};
        bd.delete(USERS_TABLE, USER_ID + " = ?", id);
    }

    public List<User> listar(){
        List<User> users = new ArrayList<User>();
        Cursor c = bd.query(USERS_TABLE, User.COLUNAS, null, null, null, null, null);
        if (c.moveToFirst()){
            User user = new User();
            do{
                user.setIdGoogle(c.getString(0));
                user.setName(c.getString(1));
                user.setEmail(c.getString(2));
                user.setPassword(c.getString(3));
                user.setCar(Integer.parseInt(c.getString(4)));
            }while(c.moveToNext());
        }
        c.close();
        return users;
    }

    public User buscarPeloId(int id){
        User user = new User();

        Cursor cursor = bd.query(USERS_TABLE, User.COLUNAS, USER_ID + "="+id, null, null, null, null);

        if (cursor.moveToFirst()){
            user.setIdGoogle(cursor.getString(0));
            user.setName(cursor.getString(1));
            user.setEmail(cursor.getString(2));
            user.setPassword(cursor.getString(3));
            user.setCar(Integer.parseInt(cursor.getString(4)));
        }
        cursor.close();
        return user;
    }

    public User buscarPeloEmail(String email){
        User user = new User();

        Cursor cursor = bd.query(USERS_TABLE, User.COLUNAS, USER_EMAIL + " = "+email, null, null, null, null);
        if (cursor.getCount() > 0){
            Log.i("RETORNO DE USUÁRIO", "Foi retornado " + cursor.getCount() + "usuários");

            if (cursor.moveToFirst()){
                user.setIdGoogle(cursor.getString(0));
                user.setName(cursor.getString(1));
                user.setEmail(cursor.getString(2));
                user.setPassword(cursor.getString(3));
                user.setCar(Integer.parseInt(cursor.getString(4)));
            }

        }
        cursor.close();
        return user;
    }


}
