package pt.lighthouselabs.obd.reader.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.content.Intent;
import android.content.IntentSender;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;


import pt.lighthouselabs.obd.reader.R;
import pt.lighthouselabs.obd.reader.user.User;
import pt.lighthouselabs.obd.reader.user.UserLog;

public class RegisterPlus extends ActionBarActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final int SIGN_IN_CODE = 9000;

    private GoogleApiClient googleApiClient;
    private ConnectionResult connectionResult;
    private boolean isConsentScreenOpen;
    private boolean isSignInButtonClicked;

    private RelativeLayout llContainerAll;
    private ProgressBar pbContainer;

    private TextView id;
    private EditText editNome;
    private EditText editEmail;
    private EditText editSenha;
    private EditText editSenhaRepete;
    private Button btFimCadastro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_plus);

        accessView();

        // Deixando a Tela Full Screen e tirando a action bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        googleApiClient = new GoogleApiClient.Builder(RegisterPlus.this)
                .addConnectionCallbacks(RegisterPlus.this)
                .addOnConnectionFailedListener(RegisterPlus.this)
                .addApi(Plus.API)
                .addScope(Plus.SCOPE_PLUS_LOGIN)
                .build();
        // Chamada da conexão com o GooglePlus
        if(!googleApiClient.isConnecting()) {
            isSignInButtonClicked = true;
            showUi(false, true);
            resolveSignIn();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(googleApiClient != null) {
            googleApiClient.connect();
        }

    }

    @Override
    protected void onStop() {
        super.onStop();

        if(googleApiClient != null && googleApiClient.isConnected()) {
            googleApiClient.disconnect();
        }
    }

    // Util
    public void  accessView(){
        llContainerAll = (RelativeLayout) findViewById(R.id.llContainerAll);
        pbContainer    = (ProgressBar) findViewById(R.id.pbContainer);

        //id = (TextView) findViewById(R.id.txtId);
        editNome = (EditText) findViewById(R.id.editNome);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editSenha = (EditText) findViewById(R.id.editSenha);
        editSenhaRepete = (EditText) findViewById(R.id.editSenhaRepete);
        btFimCadastro = (Button) findViewById(R.id.btFimCadastro);
    }

    public void showUi(boolean status, boolean statusProgressBar) {
        if(!statusProgressBar) {
            llContainerAll.setVisibility(View.VISIBLE);
            pbContainer.setVisibility(View.GONE);
        } else {
            llContainerAll.setVisibility(View.GONE);
            pbContainer.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == SIGN_IN_CODE) {
            isConsentScreenOpen = false;

            if(resultCode != RESULT_OK) {
                isSignInButtonClicked = false;
                finish();
            }

            if(!googleApiClient.isConnecting()) {
                googleApiClient.connect();
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register_plus, menu);
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

    @Override
    public void onConnected(Bundle bundle) {
        isSignInButtonClicked = false;
        showUi(true, false);
        getDataProfile();
    }

    private void getDataProfile() {
        Person p = Plus.PeopleApi.getCurrentPerson(googleApiClient);

        if(p != null) {
            String idStr = p.getId();
            String nomeStr = p.getDisplayName();
            String emailStr = Plus.AccountApi.getAccountName(googleApiClient);

            editNome.setText(nomeStr);
            editEmail.setText(emailStr);

        } else {
            Toast.makeText(RegisterPlus.this, "Dados n\u00e3o recuperados", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        googleApiClient.connect();
        showUi(false, false);
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        if(!result.hasResolution()) {
            GooglePlayServicesUtil.getErrorDialog(result.getErrorCode(), RegisterPlus.this, 0).show();
            return;
        }
        if(!isConsentScreenOpen) {
            connectionResult = result;

            if(isSignInButtonClicked) {
                resolveSignIn();
            }
        }
    }

    public void resolveSignIn() {
        if(connectionResult != null && connectionResult.hasResolution()) {
            try {
                isConsentScreenOpen = true;
                connectionResult.startResolutionForResult(RegisterPlus.this, SIGN_IN_CODE);
            } catch (IntentSender.SendIntentException e) {
                isConsentScreenOpen = false;
                googleApiClient.connect();
            }
        }
    }

    public void revogarAcesso() {
        if(googleApiClient.isConnected()) {
            showUi(false, true);
            Plus.AccountApi.clearDefaultAccount(googleApiClient);
            Plus.AccountApi.revokeAccessAndDisconnect(googleApiClient).setResultCallback(new ResultCallback<Status>() {
                @Override
                public void onResult(Status status) {
                    finish();
                }
            });
        }
    }

    public void cadastrar(View view) {
        String nome, email, senha, repeteSenha;
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        nome = editNome.getText().toString();
        email = editEmail.getText().toString().trim();
        senha = editSenha.getText().toString();
        repeteSenha = editSenhaRepete.getText().toString();


        // Validação dos dados
        if(nome.isEmpty() || nome == null) {
            editNome.setError("Insira um nome!");
            editNome.setFocusable(true);
            editNome.requestFocus();
        } else if(!email.matches(emailPattern)) {
            editEmail.setError("Insira um e-mail v\u00e1lido!");
            editEmail.setFocusable(true);
            editEmail.requestFocus();
        } else if(senha.isEmpty() || senha == null) {
            editSenha.setError("Insira uma senha!");
            editSenha.setFocusable(true);
            editSenha.requestFocus();
        } else if(repeteSenha.isEmpty() || repeteSenha == null) {
            editSenhaRepete.setError("Repita sua senha!");
            editSenhaRepete.setFocusable(true);
            editSenhaRepete.requestFocus();
        } else if(!senha.equals(repeteSenha)) {
            editSenhaRepete.setError("Os campos de senha devem ser id\u00eanticos!");
            editSenhaRepete.setFocusable(true);
            editSenhaRepete.requestFocus();
        } else {

            Person p = Plus.PeopleApi.getCurrentPerson(googleApiClient);

            if(p != null) {
                String idStr = p.getId();
                String nomeStr = p.getDisplayName();
                String emailStr = Plus.AccountApi.getAccountName(googleApiClient);

                User user = new User();

                user.setIdGoogle(idStr);
                user.setName(nomeStr);
                user.setEmail(emailStr);
                user.setPassword(senha);

                UserLog userLog = new UserLog(this);

                userLog.inserir(user);
                Log.i("NOVO USU\u00c1RIO", user.getEmail());

                Toast.makeText(RegisterPlus.this, "Cadastrado com sucesso", Toast.LENGTH_LONG).show();
                Intent i = new Intent(RegisterPlus.this, MainActivity.class);
                startActivity(i);
            }
        }
    }

    public void verificaUsuario (UserLog userLog, User user) {
        if (userLog.buscarPeloEmail(user.getEmail()) != null) {
            Log.i("USU\u00c1RIO J\u00c1 CADASTRADO", user.getEmail());

            Toast.makeText(RegisterPlus.this, "Usu\u00e1rio j\u00e1 cadastrado, fa\u00e7a login ou recupere sua senha perdida", Toast.LENGTH_LONG).show();
            Intent i = new Intent(RegisterPlus.this, Login.class);
            startActivity(i);
        } else {
            userLog.inserir(user);
            Log.i("NOVO USU\u00c1RIO", user.getEmail());

            Toast.makeText(RegisterPlus.this, "Cadastrado com sucesso", Toast.LENGTH_LONG).show();
            Intent i = new Intent(RegisterPlus.this, MainActivity.class);
            startActivity(i);
        }


    }
}
