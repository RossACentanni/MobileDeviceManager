package com.example.mobiledevicemanager;

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyDeviceAdminReceiver extends DeviceAdminReceiver {

    @Override
    public void onEnabled(Context context, Intent intent){
        super.onEnabled(context, intent);
    }

    @Override
    public void onDisabled(Context context, Intent intent){
        Toast.makeText(context, "Admin disabled.", Toast.LENGTH_SHORT).show();
        super.onDisabled(context, intent);
    }
}
