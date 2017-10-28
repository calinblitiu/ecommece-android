package com.it.spot.spotit;

import org.json.JSONException;

/**
 * Created by rubby on 10/28/2017.
 */

public interface AsyncResponse {

    void processFinishWithSuccess( String method,String output) throws JSONException;
    void processFinishWithError( String method );
}

