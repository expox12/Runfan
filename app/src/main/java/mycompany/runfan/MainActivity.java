package mycompany.runfan;

import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import mycompany.runfan.adapters.NavigationAdapter;
import mycompany.runfan.fragments.HomeFragment;


/**
 * Created by macmini02 on 11.04.16.
 */
public class MainActivity extends AppCompatActivity {

    // array list donde se van almacenando cada fila(imágen y texto) del menu lateral(navigation)
    private ArrayList<item_object> navItems;

    private String[] navTitles; // array de los titulos
    TypedArray navIcons; // array para los ficheros 'res'

    // Adaptador menu lateral
    NavigationAdapter navAdapter;

    private ListView drawerList;
    final private String[] fragments = { "mycompany.runfan.fragments.HomeFragment", "mycompany.runfan.fragments.FragmentTwo" };

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

        // Saco la información del xml
        navTitles = getResources().getStringArray(R.array.nav_options);
        navIcons = getResources().obtainTypedArray(R.array.nav_icons);

        // Creo un array list de tipo item_object y lo relleno de datos.
        navItems = new ArrayList<>();
        for (int i = 0; i < navTitles.length; i++)
            navItems.add(new item_object(navTitles[i], navIcons.getResourceId(i, -1)));

        navAdapter = new NavigationAdapter(this, navItems);

        // Instancio el listview para poder meter un adapter
        drawerList = (ListView) findViewById(R.id.lista_izq);
        drawerList.setAdapter(navAdapter);

        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                mostrarFragment(position);
            }
        });

    }

    public void mostrarFragment(int posicion) {
        Fragment fragment = null;
        switch (posicion) {
            case 0:
                fragment = new HomeFragment();
                break;

            default:
                Toast.makeText(this, "Opción " + navTitles[posicion] + " no disponible!", Toast.LENGTH_SHORT).show();
                fragment = new HomeFragment();
                posicion = 0;
                break;
        }
        if(fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.maincontent, fragment).commit();

            drawerList.setItemChecked(posicion, true);
            drawerList.setSelection(posicion);

            setTitle(navTitles[posicion]);

            drawerLayout.closeDrawer(drawerList);
        } else {
            Log.e("Error ", "mostrarFragment " + posicion);
        }
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

    // Este método es llamado en el momento en el cual el Activity se ha cargado por completo.
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    // Se ejecuta este metodo cuando la configuracion del dispositivo cambia y hay una Activity en curso
    // La nueva configuracion hay que indicarla en el AndroidManifest.xml
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

}
