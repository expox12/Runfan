package mycompany.runfan;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


/**
 * Created by macmini02 on 11.04.16.
 */
public class MainActivity extends AppCompatActivity {

    private String[] listaDePlanetas;
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    final private String[] fragments = {"mycompany.runfan.fragments.FragmentOne", "adasdasdasdasd"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instancio el toolbar e incorporo el objeto al Activity
        Toolbar myToolBar = (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(myToolBar);

        // Saco la información del array xml
        listaDePlanetas = getResources().getStringArray(R.array.planetas);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);

        // Instancio el listview para poder meter un adapter
        drawerList = (ListView) findViewById(R.id.lista_izq);
        drawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.simple_list_item_1, listaDePlanetas));

        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {

                    // navigation drawer closed
                    @Override
                    public void onDrawerClosed(View drawerView) {
                        super.onDrawerClosed(drawerView);

                        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
                        tx.replace(R.id.maincontent, Fragment.instantiate(MainActivity.this, fragments[0]));
                        tx.commit();
                    }

                    // navigation drawer opened
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
        tx.replace(R.id.maincontent, Fragment.instantiate(MainActivity.this, fragments[0]));
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
}
