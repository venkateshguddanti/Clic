package com.clic.org.serve.listener;

/**
 * Created by Venkatesh on 07-06-2016.
 */
public interface ServiceListener {

    public void onServiceResponse(String response);
    public void onServiceError(String response);
}
