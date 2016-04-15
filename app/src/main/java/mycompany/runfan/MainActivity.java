package mycompany.runfan;

import android.content.res.Configuration;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


/**
 * Created by macmini02 on 11.04.16.
 */
public class MainActivity extends AppCompatActivity {

    private String[] listaDePlanetas;

    private ListView drawerList;
    final private String[] fragments = {"mycompany.runfan.fragments.FragmentOne", "adasdasdasdasd"};

    // Contenedor principal donde van a interactuar las vistas
    private DrawerLayout drawerLayout;

    // Toolbar(Barra de herramientas)
    private Toolbar myToolBar;

    ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);

        iniciarToolbar();

        setupInterctiveMenu();

        // Saco la información del array xml
        listaDePlanetas = getResources().getStringArray(R.array.planetas);

        // Instancio el listview para poder meter un adapter
        drawerList = (ListView) findViewById(R.id.lista_izq);
        drawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.simple_list_item_1, listaDePlanetas));


    /*    drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // Cuando un item ha sido seleccionado en la lista
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                // creo un escuchador para el drawerlayout. Lo creo ya que me interesa mostrar un fragment dependiendo si este layoput esta desplegado o no.
                drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {

                    // and when navigation drawer closed...
                    @Override
                    public void onDrawerClosed(View drawerView) {
                        super.onDrawerClosed(drawerView);
                        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
                        tx.replace(R.id.maincontent, Fragment.instantiate(MainActivity.this, fragments[0]));
                        tx.commit();
                    }

                    // or when navigation drawer opened...
                    @Override
                    public void onDrawerOpened(View drawerView) {
                        super.onDrawerOpened(drawerView);
                        Toast.makeText(getApplicationContext(), "llamada a onDrawOpened", Toast.LENGTH_SHORT).show();
                    }
                });
                drawerLayout.closeDrawer(drawerList);
            }
        });
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.maincontent, Fragment.instantiate(MainActivity.this, fragments[0]));*/
    }

    public void iniciarToolbar() {
        myToolBar = (Toolbar) findViewById(R.id.mytoolbar);

        // Establezco el toolbar como el action bar del Activity
        if(myToolBar != null)
            setSupportActionBar(myToolBar);

        // Establezco un "home" para el action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public void setupInterctiveMenu() {

        // Esta clase proporciona una forma práctica de unir la funcionalidad de DrawerLayout y el action bar
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, myToolBar, R.string.open, R.string.close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                Toast.makeText(getApplicationContext(), "onDrawerClosed", Toast.LENGTH_SHORT).show();
                invalidateOptionsMenu();
                syncState();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
                syncState();
            }
        };

        // Añado drawerToggle como escuchador al drawerLayout
        drawerLayout.addDrawerListener(drawerToggle);

        // Mantiene sincronizados drawerToggle y drawerLayout
        drawerToggle.syncState();

    }

    // Instancio el menu para que se muestre en el toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menutoolbar, menu);
        return true;
    }

    // Método que reacciona si un item del toolbar ha sido seleccionado
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorite:
                Toast.makeText(getApplicationContext(), "Añadir", Toast.LENGTH_SHORT).show();
                return true;
        }
        return false;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    public void setFragments() {

    }

}
