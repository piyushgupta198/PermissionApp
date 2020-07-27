package com.example.permission;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.permission.R;

import java.util.List;

public class OwnAdapter extends BaseAdapter {

    private List<PackageInfo> list;
    private Context context;
    private PackageManager packageManager;

    public OwnAdapter (Context mContext, List<PackageInfo> packageInfos, PackageManager manager){

        super();
        context = mContext;
        list = packageInfos;
        packageManager = manager;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.list_manner, null);
        }

        PackageInfo info = (PackageInfo) getItem(position);
        TextView textView = (TextView) view.findViewById(R.id.textView);
        ImageView imageView = (ImageView) view.findViewById(R.id.icon);

        textView.setText(packageManager.getApplicationLabel(info.applicationInfo).toString());
        imageView.setImageDrawable(packageManager.getApplicationIcon(info.applicationInfo));

        return view;
    }
}
