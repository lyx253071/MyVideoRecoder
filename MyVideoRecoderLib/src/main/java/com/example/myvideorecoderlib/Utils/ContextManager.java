package com.example.myvideorecoderlib.Utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import static android.content.Context.CONTEXT_IGNORE_SECURITY;
import static android.content.Context.CONTEXT_INCLUDE_CODE;

public class ContextManager {
    private Context mContext;
    public ContextManager(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * 获取指定包名的资源文件
     */
    public Context getRemoteContext(String packgeName){
        Context remoteContext = null;
        try {
            remoteContext = mContext.createPackageContext(packgeName, CONTEXT_INCLUDE_CODE | CONTEXT_IGNORE_SECURITY);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            Log.e("tagId", "getRemoteContext: "+e );
        }
        return remoteContext;
    }
}
