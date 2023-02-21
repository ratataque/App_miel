package com.example.app_miel.http_tool;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


public class Acces_HTTP extends AsyncTask<String, Integer, Long> {

    private ArrayList<NameValuePair> parametres;
    private String ret = null;
    public AsyncResponse delegate = null;

    public Acces_HTTP() {
        parametres = new ArrayList<NameValuePair>();
    }

    public void addParams(String nom, String valeur) {
        parametres.add(new BasicNameValuePair(nom, valeur));
    }

    @Override
    protected Long doInBackground(String... strings) {
        HttpClient cnxHttp = new DefaultHttpClient();
        HttpPost paramCnx = new HttpPost(strings[0]);

        try {
            paramCnx.setEntity(new UrlEncodedFormEntity(parametres));
            HttpResponse reponse = cnxHttp.execute(paramCnx);
            ret = EntityUtils.toString(reponse.getEntity());
        } catch (UnsupportedEncodingException e) {
            Log.d("erreur requete", "doInBackground:"+e.toString());
        } catch (ClientProtocolException e) {
            Log.d("erreur requete", "doInBackground:"+e.toString());
        } catch (IOException e) {
            Log.d("erreur requete", "doInBackground:"+e.toString());
        }
        return null;
    }

    @Override
    protected void onPostExecute(Long result) {
        delegate.processFinish((ret.toString()));
    }
}
