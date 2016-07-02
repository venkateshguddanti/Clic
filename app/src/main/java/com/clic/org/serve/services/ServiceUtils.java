package com.clic.org.serve.services;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.clic.org.serve.AppController.ClicController;
import com.clic.org.serve.Utils.ClicUtils;
import com.clic.org.serve.listener.ServiceListener;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Venkatesh on 11-06-2016.
 */
public class ServiceUtils {


    public static void makeJSONObjectReq(Context context,String url, final ServiceListener mListener, String  params)
    {
        final ProgressDialog mProgress = new ProgressDialog(context);
        ClicUtils.createProgressBar(mProgress);
        Log.d("debug", "jsonresponse" + ServiceConstants.clicBaseUrl+url);
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, ServiceConstants.clicBaseUrl+url,params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.d("debug", "jsonObjectReq" + response.toString());
               mListener.onServiceResponse(response.toString());
                mProgress.dismiss();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("debug","jsonObjectReqError"+error);
                mListener.onServiceError(error.toString());
                mProgress.dismiss();
            }
        })
        {

        }
                ;
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
                2,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Adding request to request queue
        ClicController.getInstance().addToRequestQueue(jsonObjReq);
    }


    public static void makeJsonArrayRequest(final Activity activity,String url, final ServiceListener mListener, final HashMap<String,String> params)
    {
        final ProgressDialog mProgress = new ProgressDialog(activity);
        ClicUtils.createProgressBar(mProgress);
        Log.d("debug", "jsonresponse" + ServiceConstants.clicBaseUrl + url);
        JsonArrayRequest lreq = new JsonArrayRequest(ServiceConstants.clicBaseUrl + url,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("debug", "jsonObjectReq" + response.toString());
              mListener.onServiceResponse(response.toString());
                mProgress.dismiss();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("debug", "jsonObjectReqError" + error);
                mListener.onServiceError(error.toString());
                mProgress.dismiss();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        }
        ;

        lreq.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
                2,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        ClicController.getInstance().addToRequestQueue(lreq);
    }


    public static void postJsonObjectRequest(Activity activity,String url, final ServiceListener mListener, final String params)
    {
        final ProgressDialog mProgress = new ProgressDialog(activity);
        ClicUtils.createProgressBar(mProgress);
        Log.d("debug", "jsonresponse" + ServiceConstants.clicBaseUrl+url);
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, ServiceConstants.clicBaseUrl+url,params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.d("debug", "jsonresponse" + response.toString());
                 mListener.onServiceResponse(response.toString());
                mProgress.dismiss();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("debug","jsonresponse"+error.toString());
                mListener.onServiceError(error.toString());
                mProgress.dismiss();
            }
        })
        {

            /*@Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }*/
        };

        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
                2,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        // Adding request to request queue
        ClicController.getInstance().addToRequestQueue(jsonObjReq);
    }
}
