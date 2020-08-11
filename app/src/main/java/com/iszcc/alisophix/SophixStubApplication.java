package com.iszcc.alisophix;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Keep;

import com.taobao.sophix.PatchStatus;
import com.taobao.sophix.SophixApplication;
import com.taobao.sophix.SophixEntry;
import com.taobao.sophix.SophixManager;
import com.taobao.sophix.listener.PatchLoadStatusListener;

/**
 * create by Rainy on 2020/7/24.
 * email: im.wyu@qq.com
 * github: Rainvy
 * describe:
 */
public class SophixStubApplication extends SophixApplication {

    private final String TAG = "SophixStubApplication";

    private static final String ALI_SOPHIX_APP_KEY = "30816433";
    private static final String ALI_SOPHIX_APP_SECRET = "90d86682ac85e84d05fd3773a91862a4";
    private static final String ALI_SOPHIX_APP_RSA = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCyWBtx61IK8Ecd9+OH4dfsdBhe0dtzostL/nwHz3TR5WDLUuRQ4oTfcMwV3ExdcNjnodFWYvfCY+08/95XusxLDuBTM/rgZd5lD8vAMGsuXx1B7whrbWYyGYNztmsdvJip+6pawCbKEFav18rUfCHgSLVNTXEpeb6HbSgexSrsOHF5k6MyLxkRDmJ9SoRmiksgy9yR8uroo+k/NkWaKahqIoWq6Xxc6sz9tLGxIqlWLjcW3lwGimCYYdhvRCMg/szFYft9gbFcQ6Hp4JA/PFOGPCVJAurH5WxW4eYSE0k6C55FXQizWAAA4jzS83Jp5rPOhUZ82surt2KI/JCevrCZAgMBAAECggEANfA51Ll0JcTH7YNkeFtQv73ZBQx5T77jt4cvIfmXfkfef4ABd4ka4K0vvEjVe8k+gMfLFVJ3Vn58aN+F8oiXcvf71o45CRcmLPCbEfRS5kJ0CdXuePcsWmim/h8kxGiez4JC4pGM6b4JScN9H8ivQjMbSlbqq72bZ0AXSz8cnTZu3bZkgoUJ53t+S2Qhd5/HkzaY+B+3ZqLDrmI3rsPaHNPxRsUwdFVfVHcCvZiJdDxlgdxUy5DpgVQYT3G5sp8c/Z1xQSe5XVncrU+xXZ6DABqj1L66iCYocfiSH0KPg2sZw6kw1XT2Njpeom+YqB8909Q8D/DywPwD3k3nedAckQKBgQDvZ+Wcmrd07HHhzNXEYSjm4B0cLEaeKrtlRHYqh++A5FVFyQ+G3AuZOCX3uQAUwehp9yL47DsOAtcLCCw7wuTQgLB7dM2KgIC7MFdnzjfH515rAbzKz41jzAHnbVJvZLi3/9f0xftA1BeSqogeltdSsVWKSLd8kYiXyskxXOEKjQKBgQC+tLXCYrM3IPAsPFbgzdB535tWSUaVKv3B8lSsSA1t5BW7AqNZVY4iqSA7Gr7LOl5/y4LwABUB/Nv0qL4tZDaIrWGu9ig+TJ1oLO6MsDPSRhoVQ4i6HooPioNc5A6HObV0vx7ouqU4b+FzFpsMoyhOnjMU6yoof5lY9yKzbo8hPQKBgQCix+Euz2AoR1DTuGQtR+b4vK/uZQiLKQEMRTWXi6BE4nw8nA5LozjbsHrYaljsxEAUNO75lB4/U50GKkNxSv4r6ySUfQH4x5FANgloUqZI7T+cwKyutdssXR16TzH6/eA/c91R2IATP2Lb3cn6CpRT7LH9+Z2RPJ9EnqSYRM/bTQKBgGtiSGK/vxiQ1IykIWuOmRC3R7nrovIF9kPKl8E3BVDWuSHH14FLOrDruPlvEvZO1L1zNDS+ZxXqc1St3AClSEvoDXQ+qeSCsbPicSUJZvTFgpRspcXfwnAD1/prdH7SkQm/QMFdsAE6hqGOnhocmWrDjBIAZYnOZ+P/rSuKWtAlAoGBALC5C9RiHuCY8xbpCjDJ9Y6eC0V/VWQaf1gcy1OsG9kqYKY8ltOmARovpPgCPDutFZd8LcSQtSK5UBlmAYkTvsaHuzaDVMkTtBYew8yeE15QOW8io/MlOl2tWc3RJu4GDpWOuAp+ffmeI8WlNT33959r6Eg6D+Yh1E7neuY8jdda";


    // todo  此处SophixEntry应指定真正的Application，并且保证RealApplicationStub类名不被混淆。
    /*
    -keepclassmembers class com.AppClient {
        public <init>();
        }
        # 如果不使用android.support.annotation.Keep则需加上此行
        # -keep class com.my.pkg.SophixStubApplication$RealApplicationStub
     */

    @Keep
    @SophixEntry(MyAppication.class)
    static class RealApplicationStub {
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//         todo 如果需要使用MultiDex，需要在此处调用。记得在原来的Application里面去除MultiDex，避免重复调用导致问题。
//         MultiDex.install(this);
        initSophix();
    }

    private void initSophix() {
        String appVersion = "0.0.0";
        try {
            appVersion = this.getPackageManager().getPackageInfo(this.getPackageName(), 0).versionName;
        } catch (Exception e) {
        }
        final SophixManager instance = SophixManager.getInstance();
        instance.setContext(this)
                .setAppVersion(appVersion)
                .setSecretMetaData(ALI_SOPHIX_APP_KEY, ALI_SOPHIX_APP_SECRET, ALI_SOPHIX_APP_RSA)
                .setEnableDebug(true)
                .setEnableFullLog()
                .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                    @Override
                    public void onLoad(final int mode, final int code, final String info, final int handlePatchVersion) {
                        if (code == PatchStatus.CODE_LOAD_SUCCESS) {

                        } else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
                            // 如果需要在后台重启，建议此处用SharePreference保存状态。
                        }

                        Log.i(TAG, "sophix load patch success!" +"[mode:" + mode + "\n code : " + code+ "\n + info:" + info +"\n + handlepathversio n：  " + handlePatchVersion+"]");
                    }
                }).initialize();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        loadNewPatch();

        Log.d(TAG, "onCreate");
    }


    private void loadNewPatch() {
        // queryAndLoadNewPatch不可放在attachBaseContext 中，否则无网络权限，建议放在后面任意时刻，如onCreate中
        SophixManager.getInstance().queryAndLoadNewPatch();
    }
}
