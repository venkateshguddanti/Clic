package com.clic.org.serve.fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by Venkatesh on 08-06-2016.
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {


    DateFromPickerListener mDateListener;


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        mDateListener = (DateFromPickerListener)getTargetFragment();

        // Create a new instance of DatePickerFragment and return it
        return new DatePickerDialog(getActivity(),this,year,month,day);
    }



    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

        mDateListener.getDataFromPicker(dayOfMonth + "-" + monthOfYear++ + "-" + year);
        Log.d("debug", "date" + dayOfMonth + "-" + monthOfYear+1 + "-" + year);

    }

    public interface DateFromPickerListener
    {
        public void getDataFromPicker(String value);
    }


}
