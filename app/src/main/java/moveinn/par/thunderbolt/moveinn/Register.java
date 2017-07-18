package moveinn.par.thunderbolt.moveinn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import moveinn.par.thunderbolt.moveinn.sql.DataBaseHelper;


public class Register extends ActionBarActivity implements View.OnClickListener{
    EditText fName,lName,cnum,emaill, etUsername, etPassword;
    Button bRegister;
    DataBaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fName = (EditText) findViewById(R.id.fName);
        lName = (EditText) findViewById(R.id.lName);
        cnum = (EditText) findViewById(R.id.cnum);
        emaill = (EditText) findViewById(R.id.email);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bRegister = (Button) findViewById(R.id.bRegister);

        bRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bRegister:
                
                String fname = fName.getText().toString();
                String lname = lName.getText().toString();
                String email = emaill.getText().toString();

                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                String contactno = cnum.getText().toString();
                if(fname.equals("") || lname.equals("") ||contactno.equals("")|| email.equals("")|| username.equals("")|| password.equals("")){
                    Toast.makeText(getApplicationContext(), "Details must be filled", Toast.LENGTH_LONG).show();
                    return;
                }


                    User user = new User(fname, lname, contactno, email, username, password);
                    registerUser(user);

                
                break;
        }
    }

    private void registerUser(User user) {
       /* ServerRequests serverRequest = new ServerRequests(this);
        serverRequest.storeUserDataInBackground(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                Intent loginIntent = new Intent(Register.this, Login.class);
                startActivity(loginIntent);
            }
        });*/
        databaseHelper = new DataBaseHelper(getApplicationContext());
        databaseHelper.addUser(user);
        Toast.makeText(getApplicationContext(), "Successfully Registered", Toast.LENGTH_SHORT).show();

        emptyInputEditText();

        Intent i = new Intent(Register.this, Login.class);
        startActivity(i);

    }
    private void emptyInputEditText() {
        fName.setText(null);
        lName.setText(null);
        emaill.setText(null);
        etPassword.setText(null);
        etUsername.setText(null);
        cnum.setText(null);

    }
}
