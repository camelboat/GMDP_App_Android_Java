package com.GMDP.myapplication;

import android.os.AsyncTask;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.*;
import javax.net.ssl.HttpsURLConnection;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class Lighting extends AppCompatActivity {
    private Switch light_switch_1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lighting);
        Switch light_switch_1 = (Switch) findViewById(R.id.light_switch_1);
        light_switch_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    new post_command_on().execute();
                    Toast.makeText(Lighting.this, "Successfully Turn On!", Toast.LENGTH_LONG).show();
                } else {
                    new post_command_off().execute();
                    Toast.makeText(Lighting.this, "Successfully Turn Off!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}

class post_command_on extends AsyncTask<Void, Void, Void>
{
    @Override
    protected Void doInBackground(Void...P) {
        try {
            URL baseURL = new URL("https://api.thingspeak.com/talkbacks/31641/commands");
            HttpsURLConnection con = (HttpsURLConnection) baseURL.openConnection();
            con.setRequestMethod("POST");
            con.setReadTimeout(10000);
            con.setConnectTimeout(15000);
            con.setDoInput(true);
            con.setDoOutput(true);

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            Log.d("hello", "1");
            params.add(new BasicNameValuePair("api_key", "NU6M3B6JB1Q3IR76"));
            params.add(new BasicNameValuePair("command_string", "0010"));
            params.add(new BasicNameValuePair("position", "1"));
            Log.d("hello", "2");
            OutputStream os = con.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(getQuery(params));
            writer.flush();
            writer.close();
            os.close();
            con.connect();
            Log.d("hello", getQuery(params));
            Log.d("hello", Integer.toString(con.getResponseCode()));
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    private String getQuery(List<NameValuePair> params) throws UnsupportedEncodingException
    {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (NameValuePair pair : params)
        {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
        }

        return result.toString();
    }
}

class post_command_off extends AsyncTask<Void, Void, Void>
{
    @Override
    protected Void doInBackground(Void...P) {
        try {
            URL baseURL = new URL("https://api.thingspeak.com/talkbacks/31641/commands");
            HttpsURLConnection con = (HttpsURLConnection) baseURL.openConnection();
            con.setRequestMethod("POST");
            con.setReadTimeout(10000);
            con.setConnectTimeout(15000);
            con.setDoInput(true);
            con.setDoOutput(true);

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            Log.d("hello", "1");
            params.add(new BasicNameValuePair("api_key", "NU6M3B6JB1Q3IR76"));
            params.add(new BasicNameValuePair("command_string", "0000"));
            params.add(new BasicNameValuePair("position", "1"));
            Log.d("hello", "2");
            OutputStream os = con.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(getQuery(params));
            writer.flush();
            writer.close();
            os.close();
            con.connect();
            Log.d("hello", getQuery(params));
            Log.d("hello", Integer.toString(con.getResponseCode()));
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    private String getQuery(List<NameValuePair> params) throws UnsupportedEncodingException
    {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (NameValuePair pair : params)
        {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
        }

        return result.toString();
    }
}
