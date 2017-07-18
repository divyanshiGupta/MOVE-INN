package moveinn.par.thunderbolt.moveinn;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    private Toolbar toolbar;
    ActionBar ab;

    public void onDrawerItemClicked(int index){
        Fragment objf=null;

        switch (index){
            case 0:
                objf= new home();
                break;

            case 1:
                objf=new ResidentialSearch();
                break;
            case 2:
                objf=new CommercialSearch();
                break;
            case 3:
                objf=new SellRentProp();
                break;



        }
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, objf)
                .commit();    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            ab = getActionBar();
        }
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);

        toolbar= (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        NavigationDrawerFragment drawerFragment= (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, new home())
                    .commit();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

      //  getMenuInflater().inflate(R.menu.menu_main, menu);



        MenuItem mn = menu.add(1,1,1,"item");
        mn.setIcon(R.drawable.pankhurio);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            mn.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        }


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (item.getItemId())
        {
            case 1:
            startActivity(new Intent(this,Login.class));
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

       }
