package moveinn.par.thunderbolt.moveinn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Loggedin extends AppCompatActivity {

    EditText name;
    UserLocalStore userLocalStore;
    User user;
    Button login,back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggedin);

        //name = (EditText)findViewById(R.id.name);

        userLocalStore =  new UserLocalStore(getApplicationContext());

       // user = new User(userLocalStore.getLoggedInUser().getUsername(), userLocalStore.getLoggedInUser().getPassword());
        String n = userLocalStore.getLoggedInUser().getFName();
        Toast.makeText(getApplicationContext(), n + " welcome", Toast.LENGTH_SHORT);

       // name.setText(n);

        login = (Button)findViewById(R.id.bLogin);
        back = (Button)findViewById(R.id.back);

        login.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View view) {

                userLocalStore.setUserLoggedIn(false);
                userLocalStore.clearUserData();
                Intent i = new Intent(Loggedin.this,MainActivity.class);
                //i.putExtra(EXTRA_ADD_UPDATE, "Add");
                startActivity(i);
            }
        });

        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Loggedin.this, MainActivity.class);
                startActivity(i);
            }
        });
    }


}
