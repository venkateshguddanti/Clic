package com.clic.org.serve.Utils;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.clic.imageservices.utils.ImageServices;
import com.clic.org.R;
import com.clic.org.serve.activity.MainActivityClic;
import com.clic.org.serve.activity.ProductDetailsAndServicesActivity;
import com.clic.org.serve.adapters.ItemGridAdapter;
import com.clic.org.serve.constants.ClicConstants;
import com.clic.org.serve.data.Address;
import com.clic.org.serve.data.OtpValidation;
import com.clic.org.serve.listener.ServiceListener;
import com.clic.org.serve.services.ServiceConstants;
import com.clic.org.serve.services.ServiceUtils;
import com.clic.org.serve.view.TouchImageView;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.HashMap;
import java.util.Objects;
import java.util.logging.FileHandler;

/**
 * Created by Venkatesh on 21-05-2016.
 */
public  class ClicUtils {



    public static void createImagePinchDialog(final Activity activity,int layout,final String path)
    {
        final Dialog dialog = new Dialog(activity,android.R.style.Theme_Holo_Light_NoActionBar);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(layout);

       // TouchImageView imageView = (TouchImageView)dialog.findViewById(R.id.pinchImage);
        ImageView imageView = (ImageView)dialog.findViewById(R.id.pinchImage);

        Glide.with(activity)
                .load("http://tineye.com/images/widgets/mona.jpg")
                .fitCenter()
                .into(imageView);
        dialog.show();
    }
    public static void createAddressDialog(final Activity activity,int layout, final Address address)
    {
        final Dialog dialog = new Dialog(activity,android.R.style.Theme_Holo_Light_NoActionBar);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(layout);

        final EditText edtHouseNo = (EditText)dialog.findViewById(R.id.edithouseNo);
        final EditText edtStreet = (EditText)dialog.findViewById(R.id.editStreetName);
        final EditText edtCity = (EditText)dialog.findViewById(R.id.editCity);
        final EditText edtState = (EditText)dialog.findViewById(R.id.editState);
        final EditText edtCountry = (EditText)dialog.findViewById(R.id.editCountry);
        final EditText edtPincode = (EditText)dialog.findViewById(R.id.editPincode);
        Button buttonSubmit = (Button)dialog.findViewById(R.id.btn_submit);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                if (edtPincode.getText().toString().length() == 0) {
                    ClicUtils.displayToast(activity, "Please Enter pincode to proceed");
                } else {
                    address.setCity(edtCity.getText().toString());
                    address.setHouseNumber(edtHouseNo.getText().toString());
                    address.setCountry(edtCountry.getText().toString());
                    address.setState(edtState.getText().toString());
                    address.setPinCode(Integer.parseInt(edtPincode.getText().toString()));
                    address.setStreetName(edtStreet.getText().toString());
                }
            }
        });

        dialog.show();
    }
    public static Dialog createMenuDialog(Activity activity,int layout)
    {
        final Dialog dialog = new Dialog(activity,android.R.style.Theme_Light_NoTitleBar_Fullscreen);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(layout);

        GridView myGridView = (GridView)dialog.findViewById(R.id.gridView);

       // ItemGridAdapter myAdapter = new ItemGridAdapter(activity,web,imageId,ClicConstants.CLIC_MENU);
       // myGridView.setAdapter(myAdapter);


        myGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                dialog.dismiss();

            }
        });
        dialog.getWindow().setLayout(getDeviceWidth(activity, ClicConstants.DEVICE_WIDTH)
                , getDeviceWidth(activity, ClicConstants.DEVICE_HEIGHT) - 250);

        dialog.show();

        return dialog;

    }
    public static String createOTPValidationDialog(final Activity activity,int layout,final String params,final ServiceListener mListener)
    {
        final String result = "";
        final Dialog dialog = new Dialog(activity,android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);
        final boolean flag = false;
        dialog.setContentView(layout);
        dialog.setTitle("Enter OTP");


        final EditText edtOTP = (EditText)dialog.findViewById(R.id.editTextOtp);

        Button btnSubmit = (Button)dialog.findViewById(R.id.btnValidate);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (edtOTP.getText().toString().length() == 0) {

                    ClicUtils.displayToast(activity,"Please Enter Input");

                } else {
                    dialog.dismiss();
                    ServiceUtils.postJsonObjectRequest(activity, ServiceConstants.OTP_VALIDATION, mListener, params);
                    dialog.dismiss();
                }


            }
        });

        dialog.show();


        return edtOTP.getText().toString();

    }

   public static ServiceListener mListener = new ServiceListener() {
        @Override
        public void onServiceResponse(String response) {

        }

        @Override
        public void onServiceError(String response) {

        }
    };
    public static int getDeviceWidth(Context context,String type)
    {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        if(type.equalsIgnoreCase(ClicConstants.DEVICE_HEIGHT)) {
            return height;
        }
        else
        {
            return width;
        }
    }

    public static void setItemHeightWidth(Context context,View view)
    {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(getDeviceWidth(context,ClicConstants.DEVICE_WIDTH)/2,
                getDeviceWidth(context,ClicConstants.DEVICE_WIDTH)/2);
        view.setLayoutParams(layoutParams);
    }

    public static void createPreferences(Context context,String prefValue,int prefKey)
    {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(context.getString(prefKey), prefValue);
        editor.commit();
    }

    public  static String readPreference(Context context,int prefKey)
    {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPref.getString(context.getString(prefKey), null);
    }

    public static boolean isNetworkAvailable(Context context) {
        if (context.checkCallingOrSelfPermission(Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }

        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo netInfo = connMgr.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }
    public static void displayToast(final Activity activity, final String message)
    {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(activity,message,Toast.LENGTH_LONG).show();
            }
        });
    }

    public static String getJsonStringFromObject(Object jsonObject)
    {
        Gson gson = new Gson();
       // Staff obj = new Staff();
        // 2. Java object to JSON, and assign to a String
        String jsonInString = gson.toJson(jsonObject);
        return jsonInString;
    }

    public static JsonObject getJsonObject( String obj )
    {
        JsonParser lParser = new JsonParser();
        return lParser.parse( obj ).getAsJsonObject();
    }

    public static String convertBitmapToString(String path)
    {

        Bitmap bm = BitmapFactory.decodeFile(path);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
        byte[] b = baos.toByteArray();
        Log.d("debug","decoded image"+Base64.encodeToString(b, Base64.DEFAULT));
        return Base64.encodeToString(b, Base64.DEFAULT);
    }



    public static boolean isFileSizeLimitExceed(String path)
    {
        File file = new File(path);

// Get length of file in bytes
        long fileSizeInBytes = file.length();
// Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
        long fileSizeInKB = fileSizeInBytes / 1024;
// Convert the KB to MegaBytes (1 MB = 1024 KBytes)
        long fileSizeInMB = fileSizeInKB / 1024;
        if(fileSizeInMB >= 1 )
        {
            return true;
        }else {
            return false;
        }
    }

   /* public static String getFilePathFromUri(Activity activity,Uri data) {

        // Will return "image:x*"
        String wholeID = DocumentsContract.getDocumentId(data);

// Split at colon, use second item in the array
        String id = wholeID.split(":")[1];

        String[] column = { MediaStore.Images.Media.DATA };

// where id is equal to
        String sel = MediaStore.Images.Media._ID + "=?";

        Cursor cursor = activity.getContentResolver().
                query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        column, sel, new String[]{ id }, null);

        String filePath = "";

        int columnIndex = cursor.getColumnIndex(column[0]);

        if (cursor.moveToFirst()) {
            filePath = cursor.getString(columnIndex);
        }

        cursor.close();

        return filePath;
    }*/

    public static void createProgressBar(ProgressDialog mProgress)
    {
        mProgress.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        mProgress.setIndeterminate(true);
        mProgress.show();
        mProgress.setContentView(R.layout.progress);
    }
}
