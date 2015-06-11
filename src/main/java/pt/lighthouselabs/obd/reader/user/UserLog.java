package pt.lighthouselabs.obd.reader.user;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import pt.lighthouselabs.obd.reader.car.Car;

/**
 * Created by hamurabi on 6/11/15.
 */
public class UserLog {
    private SQLiteDatabase bd;

    public UserLog(Context context) {
        UserLogOpenHelper userLogOpenHelper = new UserLogOpenHelper(context);
        bd = userLogOpenHelper.getWritableDatabase();
    }

    public void insert (User u) {
        ContentValues valores = new ContentValues(4);
        valores.put("NOME", u.getNome());
        valores.put("EMAIL", u.getEmail());
        valores.put("SENHA", u.getSenha());
        valores.put("CARRO", u.getCarro().getId());

        if (u.getIdGoogle().length() > 0) {
            /*
            * TODO
            * precisa mesmo setar novamente os dados?
            */
            bd.update("USER", valores, "ID = ?", new String[] {"" + u.getIdGoogle()});
        } else {
            bd.insert("USER", null, valores);
        }
    }

    public void remover (User u) {
        String[] id = {String.valueOf(u.getIdGoogle())};
        bd.delete("USER", "ID = ?", id);
    }

    public List<User> listar(){
        List<User> users = new ArrayList<User>();
        Cursor c = bd.query("USER", User.COLUNAS,
                null, null, null, null, "NOME");
        if (c.moveToFirst()){
            do{
                User user = new User();
                user.setIdGoogle(c.getString(0));
                user.setNome(c.getString(1));
                user.setEmail(c.getString(2));
                user.setSenha(c.getString(3));
            }while(c.moveToNext());
        }
        c.close();
        return users;
    }

    public User buscarPeloId(int id){
        User user = new User();

        Cursor cursor = bd.query("USER", User.COLUNAS,
                "id="+id, null, null, null, null);

        if (cursor.moveToFirst()){
            user.setIdGoogle(cursor.getString(0));
            user.setNome(cursor.getString(1));
            user.setEmail(cursor.getString(2));
            user.setSenha(cursor.getString(3));
            /* TODO
             * Pegar o objeto carro!
             */
        }
        cursor.close();
        return user;
    }

    public User buscarPeloEmail(String email){
        User user = new User();

        Cursor cursor = bd.query("USER", User.COLUNAS,"EMAIL="+email, null, null, null, null);

        if (cursor.moveToFirst()){
            user.setIdGoogle(cursor.getString(0));
            user.setNome(cursor.getString(1));
            user.setEmail(cursor.getString(2));
            user.setSenha(cursor.getString(3));
            /* TODO
             * Pegar o objeto carro!
             */
        }
        cursor.close();
        return user;
    }


}
