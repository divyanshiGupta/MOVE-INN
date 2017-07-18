package moveinn.par.thunderbolt.moveinn;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import moveinn.par.thunderbolt.moveinn.helpers.InputValidation;
import moveinn.par.thunderbolt.moveinn.sql.DataBaseHelper;


public class Login extends ActionBarActivity implements View.OnClickListener {
    Button bLogin;
    TextView registerLink;
    EditText etUsername, etPassword;
    UserLocalStore userLocalStore;
    private final AppCompatActivity activity = Login.this;
    User user;
    // View v;
    private InputValidation inputValidation;
    private DataBaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

      //  v = (View)findViewById(R.id.view);

        bLogin = (Button) findViewById(R.id.bLogin);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        registerLink = (TextView) findViewById(R.id.tvRegisterLink);


        bLogin.setOnClickListener(this);
        registerLink.setOnClickListener(this);

        userLocalStore = new UserLocalStore(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bLogin:
                String password = etPassword.getText().toString();
                String username  = etUsername.getText().toString();

                databaseHelper =  new DataBaseHelper(getApplicationContext());
                inputValidation = new InputValidation(getApplicationContext());

                user = new User(username, password);

                authenticate(user);
                break;
            case R.id.tvRegisterLink:
                Intent registerIntent = new Intent(Login.this, Register.class);
                startActivity(registerIntent);
                break;
        }
    }

    private void authenticate(User user) {

        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();


        if (!inputValidation.isInputEditTextFilled(etUsername, "Enter Username")) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(etPassword, "Enter Password")) {
            return;
        }

        if (password.equals(databaseHelper.getSinlgeEntry(username)))
        {
            Toast.makeText(getApplicationContext(), "Welcome " + username +"", Toast.LENGTH_SHORT).show();

            logUserIn(user);



          Intent accountsIntent = new Intent(activity, Loggedin.class);
           /* accountsIntent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());
            emptyInputEditText();*/

            startActivity(accountsIntent);


        } else {
            // Snack Bar to show success message that record is wrong
            Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();
            showErrorMessage();
        }
       /* ServerRequests serverRequest = new ServerRequests(this);
        serverRequest.fetchUserDataAsyncTask(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                if (returnedUser == null) {
                    showErrorMessage();
                } else {
                    logUserIn(returnedUser);
                }
            }
        });*/


    }

    private void showErrorMessage() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Login.this);
        dialogBuilder.setMessage("Incorrect user details");
        dialogBuilder.setPositiveButton("Ok", null);
        dialogBuilder.show();
    }

    private void logUserIn(User returnedUser) {
        userLocalStore.storeUserData(returnedUser);
        userLocalStore.setUserLoggedIn(true);
        //startActivity(new Intent(this, DetailActivity.class));
    }
}
