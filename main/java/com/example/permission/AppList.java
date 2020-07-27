package com.example.permission;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class AppList extends AppCompatActivity {

    PackageInfo packageInfo;
    TextView textView;
    String appName;
    PackageManager manager;
    TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_list);

        AppData data = (AppData) getApplicationContext();
        packageInfo = data.getPackageInfo();
        manager = getPackageManager();
        appName = (String) packageInfo.applicationInfo.loadLabel(manager);
        textView1 = (TextView) findViewById(R.id.permissionHeader);
        textView = (TextView) findViewById(R.id.permissionTextView);
        textView.setMovementMethod(new ScrollingMovementMethod());
        textView1.setText("*Permission use by "+appName+":");
        setValues();

    }

    public void setValues() {
        if (packageInfo.requestedPermissions != null){

            String permissionList;
            permissionList = getPermissions(packageInfo.requestedPermissions);
            String regex = "\\s*\\bandroid.permission.\\b\\s*";
            permissionList = permissionList.replaceAll(regex, "\n");
            textView.setText(permissionList);

        }else
            textView.setText("-");
    }
    private String getPermissions(String[] requestedPermissions) {
        String permission = "";
        for (int i = 0; i < requestedPermissions.length; i++) {
            permission = permission + requestedPermissions[i] + ",\n";
        }
        return permission;
    }
}
