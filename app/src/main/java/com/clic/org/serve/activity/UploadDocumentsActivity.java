package com.clic.org.serve.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.clic.imageservices.model.ImageCaptureType;
import com.clic.imageservices.utils.Constants;
import com.clic.org.R;
import com.clic.org.serve.data.UserItem;
import com.clic.org.serve.fragments.AddInvoiceFragment;

public class UploadDocumentsActivity extends AppCompatActivity implements AddInvoiceFragment.InVoicePathListener{

    String inVoice;
    UserItem mUserItem = new UserItem();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_documents);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mUserItem = getIntent().getExtras().getParcelable(getString(R.string.user_item));

        if(mUserItem.getItemDocs().size() < 3) {

            Bundle b =new Bundle();
            AddInvoiceFragment invoiceFragment = new AddInvoiceFragment();
            b.putParcelable(getString(R.string.user_item), mUserItem);
            invoiceFragment.setArguments(b);
            getSupportFragmentManager().beginTransaction().add(R.id.container, invoiceFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(null).commit();

        }
    }

    @Override
    public void getInvoicePath(String value) {

        inVoice = value;
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

  /*  @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == ImageCaptureType.CAPTURE_BY_GALLERY.getImageCaptureType() && data != null)
        {

            Toast.makeText(UploadDocumentsActivity.this, "Invoice Uploaded Successfully" + data.getExtras().getString(Constants.Gallery.IMAGE_PATH), Toast.LENGTH_SHORT).show();

        }
        else  if (data == null && resultCode == RESULT_OK) {

            Toast.makeText(UploadDocumentsActivity.this, "Invoice Uploaded Successfully"+inVoice, Toast.LENGTH_SHORT).show();
        }
        else if (data != null && resultCode == Activity.RESULT_OK) {
            // The document selected by the user won't be returned in the intent.
            // Instead, a URI to that document will be contained in the return intent
            // provided to this method as a parameter.
            // Pull that URI using resultData.getData().
            Uri uri = null;
            if (data != null) {
                uri = data.getData();
                Toast.makeText(UploadDocumentsActivity.this, "Invoice Uploaded Successfully"+uri.toString(), Toast.LENGTH_SHORT).show();

            }
        }
    }*/
}
