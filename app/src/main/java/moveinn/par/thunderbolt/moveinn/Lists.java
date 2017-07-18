package moveinn.par.thunderbolt.moveinn;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import moveinn.par.thunderbolt.moveinn.sql.DataBaseArea;

import static moveinn.par.thunderbolt.moveinn.R.id.textViewName;

public class Lists extends AppCompatActivity {

    private AppCompatActivity activity = Lists.this;
    private AppCompatTextView textViewType;
    private RecyclerView recyclerViewUsers;
    private List<Details> listDetails;
    private ListsAdapter Adapter;
    private DataBaseArea databasearea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists);
//        getSupportActionBar().setTitle("");

        textViewType = (AppCompatTextView) findViewById(textViewName);
        recyclerViewUsers = (RecyclerView) findViewById(R.id.recyclerViewUsers);

        listDetails = new ArrayList<>();
        Adapter = new ListsAdapter(listDetails);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewUsers.setLayoutManager(mLayoutManager);
        recyclerViewUsers.setItemAnimator(new DefaultItemAnimator());
        recyclerViewUsers.setHasFixedSize(true);
        recyclerViewUsers.setAdapter(Adapter);
        databasearea = new DataBaseArea(activity);

        /*String emailFromIntent = getIntent().getStringExtra("EMAIL");
        textViewName.setText(emailFromIntent);*/

        getDataFromSQLite();

    }

    private void getDataFromSQLite() {
        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                listDetails.clear();
                listDetails.addAll(databasearea.getAllArea());

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Adapter.notifyDataSetChanged();
            }
        }.execute();
    }
}
