package com.example.lsposed;

import android.util.Log;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class MainHook implements IXposedHookLoadPackage {

    private static final String TAG = "喵喵助手";
    private static final String QQ_PACKAGE = "com.tencent.mobileqq";

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) {
        if (!lpparam.packageName.equals(QQ_PACKAGE)) return;
        Log.i(TAG, "🐱 喵喵助手已加载！");
        hookQQ(lpparam);
    }

    private void hookQQ(XC_LoadPackage.LoadPackageParam lpparam) {
        try {
            Class<?> clazz = XposedHelpers.findClass(
                "com.tencent.mobileqq.aio.msglist.holder.component.TextSendMsgComponent",
                lpparam.classLoader
            );
            XposedHelpers.findAndHookMethod(clazz, "sendTextMessage", String.class,
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        String original = (String) param.args[0];
                        if (original != null && !original.isEmpty()) {
                            param.args[0] = MiaoProcessor.process(original);
                        }
                    }
                }
            );
            Log.i(TAG, "✅ QQ Hook 成功");
        } catch (Throwable t) {
            Log.w(TAG, "⚠️ Hook 失败: " + t.getMessage());
        }
    }
}
