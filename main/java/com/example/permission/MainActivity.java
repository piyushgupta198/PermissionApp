package com.example.permission;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    PackageManager packageManager;
    List<PackageInfo> list;
    List<PackageInfo> list2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.listView);
        packageManager = getPackageManager();
        list = packageManager.getInstalledPackages(PackageManager.GET_PERMISSIONS);
        list2 = new ArrayList<>();

        for(PackageInfo pi : list){
            if((pi.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM)==0){
                list2.add(pi);
            }
        }

        listView.setAdapter(new OwnAdapter(this, list2, packageManager));


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                AppData appData = (AppData) getApplicationContext();
                PackageInfo info = (PackageInfo) parent.getItemAtPosition(position);
                appData.setPackageInfo(info);

                Intent i = new Intent(MainActivity.this, AppList.class);
                startActivity(i);

            }
        });
    }
}
