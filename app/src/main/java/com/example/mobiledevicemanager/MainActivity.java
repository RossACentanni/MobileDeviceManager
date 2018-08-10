package com.example.mobiledevicemanager;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_ENABLE_ADMIN = 1;
    private static final String TAG = "TAG_Main";

    CheckBox CBEnableAdmin;
    Button  BTNLockScreen;
    Button BTNChangePassword;
    CheckBox CBDisableCameras;

    DevicePolicyManager dpManager;
    ComponentName daReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        dpManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
        daReceiver = new ComponentName(this, MyDeviceAdminReceiver.class);
    }

    private void initViews(){
        CBEnableAdmin = findViewById(R.id.CBEnableAdmin);
        BTNLockScreen = findViewById(R.id.BTNLockScreen);
        BTNChangePassword = findViewById(R.id.BTNChangePassword);
        CBDisableCameras = findViewById(R.id.CBDisableCameras);
    }

    public void enableAdmin(View v){
        boolean checked = ((CheckBox) v).isChecked();
        if(checked){
            Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, daReceiver);
            intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "for laughs");
            startActivityForResult(intent, REQUEST_CODE_ENABLE_ADMIN);

            makeVisible();
        }
        else {
            makeGone();
            dpManager.removeActiveAdmin(daReceiver);
        }
    }

    public void lockScreen(View v){
        dpManager.lockNow();
    }

    public void changePassword(View v) {
        Intent intent = new Intent(DevicePolicyManager.ACTION_SET_NEW_PASSWORD);
        startActivity(intent);
    }

    public void disableCameras(View v) {
        boolean checked = ((CheckBox) v).isChecked();
        dpManager.setCameraDisabled(daReceiver, checked);
    }

    private void makeVisible(){
        BTNLockScreen.setVisibility(View.VISIBLE);
        BTNChangePassword.setVisibility(View.VISIBLE);
        CBDisableCameras.setVisibility(View.VISIBLE);
    }

    private void makeGone(){
        BTNLockScreen.setVisibility(View.GONE);
        BTNChangePassword.setVisibility(View.GONE);
        CBDisableCameras.setVisibility(View.GONE);
    }
}
