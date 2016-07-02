package com.clic.org.serve.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.clic.org.R;
import com.clic.org.serve.Utils.ClicUtils;
import com.clic.org.serve.data.SuccessBean;
import com.clic.org.serve.listener.ServiceListener;
import com.clic.org.serve.services.ServiceConstants;
import com.clic.org.serve.services.ServiceUtils;
import com.google.gson.Gson;

public class MainActivityClic extends AppCompatActivity {

    private boolean isProductExit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_clic);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ServiceUtils.makeJSONObjectReq(this,
                ServiceConstants.IS_PRODUCT_EXIST + ClicUtils.readPreference(this,R.string.clic_ClientID),
                mServiceListener, null);
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
        if(id == android.R.id.home)
        {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
    public void onButtonClic(View view)
    {
        int id = view.getId();
        switch (id)
        {
            case R.id.btn_raise_req:
                if(ClicUtils.readPreference(getApplicationContext(),R.string.is_product_exit) == null) {
                    Intent raiseReq = new Intent(MainActivityClic.this, AddClicProductActivity.class);
                    raiseReq.putExtra(getString(R.string.activity_service_req), getString(R.string.activity_service_req_addProduct));
                    startActivity(raiseReq);
                }else
                {
                    Intent knowMore = new Intent(MainActivityClic.this,ClicServeProducts.class);
                    knowMore.putExtra(getString(R.string.activity_type), getString(R.string.activity_know_more));
                    startActivity(knowMore);
                }
                break;
            case R.id.btn_know_about:
                if(ClicUtils.readPreference(getApplicationContext(),R.string.is_product_exit) == null) {
                    Intent raiseReq = new Intent(MainActivityClic.this, AddClicProductActivity.class);
                    raiseReq.putExtra(getString(R.string.activity_service_req), getString(R.string.activity_service_req_addProduct));
                    startActivity(raiseReq);
                }else
                {
                    Intent knowMore = new Intent(MainActivityClic.this,ClicServeProducts.class);
                    knowMore.putExtra(getString(R.string.activity_type), getString(R.string.activity_know_more));
                    startActivity(knowMore);
               }
                break;
            case R.id.btn_upload_doc:
                if(ClicUtils.readPreference(getApplicationContext(),R.string.is_product_exit) == null) {
                    Intent raiseReq = new Intent(MainActivityClic.this, AddClicProductActivity.class);
                    raiseReq.putExtra(getString(R.string.activity_service_req), getString(R.string.activity_service_req_addProduct));
                    startActivity(raiseReq);
                }else
                {
                    Intent knowMore = new Intent(MainActivityClic.this,ClicServeProducts.class);
                    knowMore.putExtra(getString(R.string.activity_type), getString(R.string.activity_upload_docs));
                    startActivity(knowMore);
                }
                break;
            case R.id.btn_accecories:
                startActivity(new Intent(MainActivityClic.this,RecommendClicAccessoriesActivity.class));
                break;
        }
    }

    ServiceListener mServiceListener = new ServiceListener() {
        @Override
        public void onServiceResponse(String response) {

            SuccessBean lobj = new Gson().fromJson(response,SuccessBean.class);
            if(lobj.getStatus().equalsIgnoreCase("yes")) {
                ClicUtils.createPreferences(getApplicationContext(), "true", R.string.is_product_exit);
            }else
            {
                ClicUtils.createPreferences(getApplicationContext(), null, R.string.is_product_exit);

            }
        }

        @Override
        public void onServiceError(String response) {

        }
    };

}
