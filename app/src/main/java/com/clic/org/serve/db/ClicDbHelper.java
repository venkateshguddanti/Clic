package com.clic.org.serve.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Venkatesh.Guddanti on 04/06/2016.
 */
public class ClicDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Clic.db";
    public static final String TABLE_NAME = "ClicServeProucts";
    public static final String CLIC_CUTOMER_ID = "ClicCustomerID";
    public static final String PRODUCT_ID_NAME = "ClicProductID";
    public static final String PRODUCT_WARRANTY = "warranty";
    public static final String PRODUCT_INVOICE = "invoice";
    public static final String PRODUCT_INSURANCE = "insurance";
    public static final String PRODUCT_OTHER = "other";
    public static final String PRODUCT_SERVICE_REQ = "serviceReq";
    public static final String CLIC_SYNC = "ClicStatus";
    public static final int DATABASE_VERSION = 1;
    public ClicDbHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_NAME + " (id integer primary key, " +
                   CLIC_CUTOMER_ID + " text" + "," +
                   PRODUCT_ID_NAME + " text" + "," +
                   PRODUCT_WARRANTY + " text" + "," +
                   PRODUCT_INVOICE + " text" + "," +
                   PRODUCT_INSURANCE + " text" + "," +
                   PRODUCT_OTHER + " text" + "," +
                   PRODUCT_SERVICE_REQ + " text" + "," +
                CLIC_SYNC + " text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public void insertDetails(String customerID,String productID,String warratny,String invoice,String insurance,String other,String service)
    {
        SQLiteDatabase myDb = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();
        cValues.put(CLIC_CUTOMER_ID, customerID);
        cValues.put(PRODUCT_ID_NAME, productID);
        cValues.put(PRODUCT_WARRANTY, warratny);
        cValues.put(PRODUCT_INSURANCE, insurance);
        cValues.put(PRODUCT_INVOICE, productID);
        cValues.put(PRODUCT_OTHER, productID);
        cValues.put(PRODUCT_SERVICE_REQ, productID);
        if(isProductExist(productID))
        {
            myDb.update(TABLE_NAME, cValues, PRODUCT_ID_NAME + "=" + productID, null);
        }
        else
        {
            myDb.insert(TABLE_NAME, null, cValues);
        }
    }

    public void deleteDetails(String beaconID)
    {
        SQLiteDatabase myDb = this.getWritableDatabase();
        myDb.delete(TABLE_NAME, CLIC_CUTOMER_ID + " = ?", new String[]{beaconID});
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        return numRows;
    }
    public boolean isProductExist(String beaconID)
    {
        String data = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from "+TABLE_NAME+" where "+CLIC_CUTOMER_ID+" =?",new String[]{beaconID});

        if(cursor.getCount() <= 0)
        {
            cursor.close();
            return false;
        }
        cursor.close();

        return true;

    }

    public ArrayList<String> getProducts()
    {
        ArrayList<String> data = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        cursor.moveToFirst();

        if(cursor.moveToFirst()) {
            do {
                data.add(cursor.getString(cursor.getColumnIndex(CLIC_CUTOMER_ID)));
                Log.d("debug", "beacondDetails");
            }while (cursor.moveToNext());
        }
        cursor.close();
        return data;
    }

    public boolean isDetailsExist()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + TABLE_NAME, null);

        cursor.moveToFirst();

        if(cursor.moveToNext())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}
