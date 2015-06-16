package pt.lighthouselabs.obd.reader.user;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import pt.lighthouselabs.obd.reader.car.Car;

public class UserLog {
    private SQLiteDatabase bd;

    public UserLog(Context context) {
        UserLogOpenHelper userLogOpenHelper = new UserLogOpenHelper(context);
        bd = userLogOpenHelper.getWritableDatabase();
    }

    public void inserir (User u) {
        ContentValues valores = new ContentValues();
        valores.put("ID", u.getIdGoogle());
        valores.put("NOME", u.getNome());
        valores.put("EMAIL", u.getEmail());
        valores.put("SENHA", u.getSenha());
        valores.put("IDCARRO", u.getCarro());

        bd.insert("USER", null, valores);
    }

    public void atualizar (User u) {
        ContentValues valores = new ContentValues();
        valores.put("ID", u.getIdGoogle());
        valores.put("NOME", u.getNome());
        valores.put("EMAIL", u.getEmail());
        valores.put("SENHA", u.getSenha());
        valores.put("IDCARRO", u.getCarro());

        bd.update("USER", valores, "ID = ?", new String[]{"" + u.getIdGoogle()});
    }

    public void remover (User u) {
        String[] id = {String.valueOf(u.getIdGoogle())};
        bd.delete("USER", "ID = ?", id);
    }

    public List<User> listar(){
        List<User> users = new ArrayList<User>();
        Cursor c = bd.query("USER", User.COLUNAS, null, null, null, null, "NOME ASC");
        if (c.moveToFirst()){
            User user = new User();
            do{
                user.setIdGoogle(c.getString(0));
                user.setNome(c.getString(1));
                user.setEmail(c.getString(2));
                user.setSenha(c.getString(3));
                user.setCarro(Integer.parseInt(c.getString(4)));
            }while(c.moveToNext());
        }
        c.close();
        return users;
    }

    public User buscarPeloId(int id){
        User user = new User();

        Cursor cursor = bd.query("USER", User.COLUNAS, "ID="+id, null, null, null, null);

        if (cursor.moveToFirst()){
            user.setIdGoogle(cursor.getString(0));
            user.setNome(cursor.getString(1));
            user.setEmail(cursor.getString(2));
            user.setSenha(cursor.getString(3));
            user.setCarro(Integer.parseInt(cursor.getString(4)));
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
            user.setCarro(Integer.parseInt(cursor.getString(4)));
        }
        cursor.close();
        return user;
    }


}
