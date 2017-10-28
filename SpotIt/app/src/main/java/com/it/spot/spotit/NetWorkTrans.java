package com.it.spot.spotit;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;


import org.json.JSONException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

//Http Post Get Communication
public class NetWorkTrans extends AsyncTask<HashMap<String, Object>, Void, String> {


    public Context context;
    public  AsyncResponse   delegate = null;
    //    public ProgressHUD progressHUD;
    public  boolean         hideProgressFlag;

    public  String delimiter = "--";
    public  String boundary = "---------------------------14737809831466499882746641440";
    public  String method;
    public  String url;

    public  boolean responeseError;
    private Exception exception = new Exception();

    public NetWorkTrans(AsyncResponse delegate) {
        this.delegate = delegate;
        this.context = context;
        this.hideProgressFlag = hideProgressFlag;
    }

    @Override
    protected void onPreExecute() {
        responeseError = false;
        if (!hideProgressFlag) {
            //progressHUD = ProgressHUD.show(context, "Loading...", true, false, null);
        }
    }

    @Override
    protected String doInBackground(HashMap<String, Object>... params) {
        HashMap<String, Object> param = params[0];

        method = param.get("method").toString();
        url = param.get("url").toString();
        String results = "";
        if (method == "post"){
            param.remove("method");
            param.remove("url");
            if(param.containsKey("image")) {
                param.remove("image");
                try {
                    results = uploadImage(url, param);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else{
                results = postParams(url, param);
            }

        }else{
            param.remove("method");
            param.remove("url");
            try {
                results = getRequest( url );
            } catch (IOException e) {
                responeseError = true;
                return "";
            }
        }

        return results;
    }

    private String uploadImage(String requestURL, HashMap<String, Object> postDataParams) throws IOException, JSONException {
        String filename = postDataParams.get("photo_path").toString();
        String stringUrl = requestURL;
        String attachmentName = "photo";
        String crlf = "\r\n";
        String twoHyphens = "--";
        String response = "";

        //Setup the request
        HttpURLConnection httpUrlConnection = null;
        URL url = new URL(stringUrl);
        httpUrlConnection = (HttpURLConnection) url.openConnection();
        httpUrlConnection.setUseCaches(false);
        httpUrlConnection.setDoOutput(true);
        httpUrlConnection.setDoInput(true);
        httpUrlConnection.setReadTimeout(15000);
        httpUrlConnection.setConnectTimeout(15000);
        httpUrlConnection.setRequestMethod("POST");
        httpUrlConnection.setRequestProperty("Connection", "Keep-Alive");
        httpUrlConnection.setRequestProperty("Cache-Control", "no-cache");
        httpUrlConnection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
        httpUrlConnection.setRequestProperty("userid",postDataParams.get("userid").toString());

        // Start content wrapper
        DataOutputStream dos = new DataOutputStream(httpUrlConnection.getOutputStream());
        dos.writeBytes(twoHyphens + boundary + crlf);
        dos.writeBytes("Content-Disposition: form-data; name=\"" + attachmentName + "\";filename=\"" + "photo.jpg" + "\"" + crlf);
        //dos.writeBytes("Content-Type: image/jpeg");
        dos.writeBytes(crlf);

        int res = 1;
        // Read from FileInputStream and write to OutputStream
        if (filename != null) {
            byte[] buffer = new byte[1024];
            FileInputStream fileInputStream = new FileInputStream(filename);
            OutputStream os = httpUrlConnection.getOutputStream();
            while ((res = fileInputStream.read(buffer)) > 0) {
                os.write(buffer, 0, res);
            }

            res = httpUrlConnection.getResponseCode();
            String line;
            if(res == 200 || res == 400) {
                for(BufferedReader br = new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream())); (line = br.readLine()) != null; response = response + line) {
                    ;
                }
            } else {
                response = "";
                Log.d("PostResponseAsyncTask", res + "");
            }
        }
        // Disconnection
        httpUrlConnection.disconnect();
        return response;
    }


    private String postParams(String requestURL, HashMap<String, Object> postDataParams) {
        String response = "";

        try {
            URL url = new URL(requestURL);
            HttpURLConnection e = (HttpURLConnection)url.openConnection();
            e.setReadTimeout(15000);
            e.setConnectTimeout(15000);
            e.setRequestProperty("Connection", "Keep-Alive");
            e.setRequestProperty("Cache-Control", "no-cache");
            e.setRequestProperty("enctype","multipart/form-data");
            if(postDataParams.get("is_get_access_token").toString() == "yes") {
                e.setRequestProperty("Authorization", "Basic " + Global.credentialBase64);
            }
            else{
                e.setRequestProperty("Authorization", Global.Authorization);
            }
            e.setRequestMethod("POST");
            e.setDoInput(true);
            e.setDoOutput(true);
            OutputStream os = e.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(this.getPostDataString(postDataParams));
            writer.flush();
            writer.close();
            os.close();
            int responseCode = e.getResponseCode();
            String line;
            if(responseCode == 200 || responseCode == 400) {
                for(BufferedReader br = new BufferedReader(new InputStreamReader(e.getInputStream())); (line = br.readLine()) != null; response = response + line) {
                    ;
                }
            } else {
                response = "";
                Log.d("PostResponseAsyncTask", responseCode + "");
            }
        } catch (MalformedURLException var11) {
            Log.d("PostResponseAsyncTask", "MalformedURLException Error: " + var11.toString());
            this.exception = var11;
        } catch (ProtocolException var12) {
            Log.d("PostResponseAsyncTask", "ProtocolException Error: " + var12.toString());
            this.exception = var12;
        } catch (UnsupportedEncodingException var13) {
            Log.d("PostResponseAsyncTask", "UnsupportedEncodingException Error: " + var13.toString());
            this.exception = var13;
        } catch (IOException var14) {
            Log.d("PostResponseAsyncTask", "IOException Error: " + var14.toString());
            this.exception = var14;
        }

        return response;
    }

    private String getPostDataString(HashMap<String, Object> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        Iterator var4 = params.entrySet().iterator();

        while(var4.hasNext()) {
            Map.Entry entry = (Map.Entry)var4.next();
            if(first) {
                first = false;
            } else {
                result.append("&");
            }

            result.append(URLEncoder.encode((String)entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode((String)entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }

    public String getRequest (String serverUrl) throws IOException {

        StringBuilder sb = new StringBuilder();

        HttpURLConnection con = (HttpURLConnection) ( new URL(serverUrl)).openConnection();
        con.setReadTimeout(15000);
        con.setRequestProperty("Authorization", Global.Authorization);
        con.connect();
        int resCode = con.getResponseCode();
        InputStream in;
        if (resCode == HttpURLConnection.HTTP_OK) {
            in = con.getInputStream();
        } else {
            in = con.getErrorStream();
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String nextLine = "";
            while ((nextLine = reader.readLine()) != null) {
                sb.append(nextLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        con.disconnect();
        return sb.toString();
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        if (!hideProgressFlag) {
           // progressHUD.cancel();
        }
        if (delegate != null) {
            if (responeseError)
                delegate.processFinishWithError(method);
            else
                try {
                    delegate.processFinishWithSuccess(method, result);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
        }
    }

}

