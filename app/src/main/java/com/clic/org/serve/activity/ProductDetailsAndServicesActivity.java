package com.clic.org.serve.activity;

import android.Manifest;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.clic.org.R;
import com.clic.org.serve.Utils.ClicUtils;
import com.clic.org.serve.data.UserItem;
import com.clic.org.serve.fragments.AddInvoiceFragment;
import com.clic.org.serve.fragments.MyListFragment;
import com.clic.org.serve.fragments.ProductAccessoriesFragment;
import com.clic.org.serve.fragments.ProductInfoFragment;
import com.clic.org.serve.fragments.ProductLearnMoreFragment;
import com.clic.org.serve.fragments.ProductSellFragment;
import com.clic.org.serve.fragments.ProductServiceFragment;

public class ProductDetailsAndServicesActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        MyListFragment.AddClicProductListener,AddInvoiceFragment.InVoicePathListener{

    View productInfo,productService,productAccesories,productSell,productLearn;
    DrawerLayout drawer;
    FloatingActionButton clicServe;
    private Dialog menu_dialog;
    final int LOCATION_PERMISSION = 0;
    UserItem mUserItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info_services);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        clicServe = (FloatingActionButton)findViewById(R.id.fab);

        mUserItem = getIntent().getExtras().getParcelable(getString(R.string.user_item));



         drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(this);

        ProductInfoFragment productInfoFragment = new ProductInfoFragment();
        Bundle b = new Bundle();
        b.putParcelable(getString(R.string.user_item), mUserItem);
        productInfoFragment.setArguments(b);
        getSupportFragmentManager().beginTransaction().add(R.id.content, productInfoFragment).commit();
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.BLUETOOTH,
                    Manifest.permission.BLUETOOTH_ADMIN,
                    Manifest.permission.INTERNET,
                    Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.ACCESS_NETWORK_STATE,
            }, LOCATION_PERMISSION);
        }


    }

    public void onProductClic(View view)
    {
        int id = view.getId();
        Bundle bundle = new Bundle();
        switch (id)
        {

            case R.id.product_info:
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                ProductInfoFragment productInfoFragment = new ProductInfoFragment();
                bundle.putParcelable(getString(R.string.user_item),mUserItem);
                productInfoFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.content, productInfoFragment).commit();
                break;
            case R.id.product_service:
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                ProductServiceFragment productServiceFragment = new ProductServiceFragment();
                bundle.putParcelable(getString(R.string.user_item),mUserItem);
                productServiceFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.content, productServiceFragment).commit();
                break;
            case R.id.product_accessories:
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                ProductAccessoriesFragment productAccessoriesFragment = new ProductAccessoriesFragment();
                bundle.putParcelable(getString(R.string.user_item),mUserItem);
                productAccessoriesFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.content, productAccessoriesFragment).commit();
                break;
            case R.id.product_sell:
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                ProductSellFragment productSellFragment = new ProductSellFragment();
                bundle.putParcelable(getString(R.string.user_item),mUserItem);
                productSellFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.content, productSellFragment).commit();

                break;
            case R.id.product_learn:
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                ProductLearnMoreFragment productLearnMoreFragment = new ProductLearnMoreFragment();
                bundle.putParcelable(getString(R.string.user_item), mUserItem);
                productLearnMoreFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.content, productLearnMoreFragment).commit();
                break;

        }

        drawer.closeDrawer(GravityCompat.START);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.product_details_and_services, menu);


        /*searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));*/
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camara) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }


        drawer.closeDrawer(GravityCompat.START);
        return true;
    }





    @Override
    public void getInvoicePath(String value) {

    }

    @Override
    public void onArticalSelectedProgress(int value) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode)
        {
            case LOCATION_PERMISSION:




                break;
        }
    }
}
