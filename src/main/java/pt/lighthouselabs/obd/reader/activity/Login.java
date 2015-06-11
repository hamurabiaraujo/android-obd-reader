package pt.lighthouselabs.obd.reader.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import pt.lighthouselabs.obd.reader.R;

public class Login extends ActionBarActivity {

    private EditText editEmailLogin;
    private EditText editSenhaLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Deixando a Tela Full Screen e tirando a action bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        editEmailLogin = (EditText) findViewById(R.id.editEmailLogin);
        editSenhaLogin = (EditText) findViewById(R.id.editSenhaLogin);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void abrirRecuperarSenha(View view) {
        Intent i = new Intent(this, RecoverPassword.class);
        startActivity(i);
    }

    public void abrirCadastrar(View view) {
        Intent i = new Intent(this, RegisterChoose.class);
        startActivity(i);
    }

    public void abrirMenu(View view) {
        String email, senha;
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        email = editEmailLogin.getText().toString().trim();
        senha = editSenhaLogin.getText().toString();

        if(!email.matches(emailPattern)) {
            editEmailLogin.setError("Insira um e-mail válido!");
            editEmailLogin.setFocusable(true);
            editEmailLogin.requestFocus();
        } else if(senha.isEmpty() || senha == null) {
            editSenhaLogin.setError("Insira uma senha!");
            editSenhaLogin.setFocusable(true);
            editSenhaLogin.requestFocus();
        } else {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
    }
}
