package com.rn.modules;

/**
 * Created by amelo on 12/10/16.
 */

import android.webkit.WebView;


import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.uimanager.NativeViewHierarchyManager;
import com.facebook.react.uimanager.UIBlock;
import com.facebook.react.uimanager.UIManagerModule;

import java.util.HashMap;
import java.util.Map;



public class JSContextModule extends ReactContextBaseJavaModule {
    public JSContextModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "JSContext";
    }

    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        return constants;
    }


    @ReactMethod
    public void findWebView(final int reactTag, final Promise promise) {
        ReactApplicationContext reactContext = this.getReactApplicationContext();
        UIManagerModule uiManager = reactContext.getNativeModule(UIManagerModule.class);

        uiManager.addUIBlock(new UIBlock() {
            @Override
            public void execute(NativeViewHierarchyManager nativeViewHierarchyManager) {
                WebView view = null;

                try {
                    view = (WebView) nativeViewHierarchyManager.resolveView(reactTag);
                } catch (Exception e) {
                    promise.reject("Error", e.getMessage());
                    return;
                }

                promise.resolve(view.getUrl());
            }
        });
    }
}