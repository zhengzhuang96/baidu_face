/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.rain.baidu_face;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.baidu.idl.face.platform.FaceSDKManager;
import com.baidu.idl.face.platform.FaceStatusNewEnum;
import com.baidu.idl.face.platform.model.ImageInfo;
import com.rain.baidu_face.platform.ui.FaceLivenessActivity;
import com.rain.baidu_face.platform.ui.utils.IntentUtils;
import com.rain.baidu_face.platform.ui.utils.TimeoutDialog;


import java.util.HashMap;

public class OfflineFaceLivenessActivity extends FaceLivenessActivity implements
        TimeoutDialog.OnTimeoutDialogClickListener {

    private TimeoutDialog mTimeoutDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 添加至销毁列�?//        ExampleApplication.addDestroyActivity(FaceLivenessExpActivity.this,
//                "FaceLivenessExpActivity");
    }

    @Override
    public void onLivenessCompletion(FaceStatusNewEnum status, String message,
                                     HashMap<String, ImageInfo> base64ImageCropMap,
                                     HashMap<String, ImageInfo> base64ImageSrcMap, int currentLivenessCount) {
        super.onLivenessCompletion(status, message, base64ImageCropMap, base64ImageSrcMap, currentLivenessCount);
        if (status == FaceStatusNewEnum.OK && mIsCompletion) {
            Log.e("SecRequest", mBmpStr);
            final Intent resultIntent = new Intent();
            resultIntent.putExtra("bestImage", mBmpStr);
            setResult(RESULT_OK, resultIntent);
            finish();
//            new SecRequest().
 //           SecRequest.sendMessage(getBaseContext(), mBmpStr, 0);
//            final Intent resultIntent = new Intent();
//            resultIntent.putExtra("bestImage", mBmpStr);
//            setResult(RESULT_OK, resultIntent);

            //   showMessageDialog("活体检�?, "检测成�?);
//            IntentUtils.getInstance().setBitmap(mBmpStr);
//            Intent intent = new Intent(FaceLivenessExpActivity.this,
//                    CollectionSuccessExpActivity.class);
//            intent.putExtra("destroyType", "FaceLivenessExpActivity");
//            startActivity(intent);
        } else if (status == FaceStatusNewEnum.DetectRemindCodeTimeout) {
            if (mViewBg != null) {
                mViewBg.setVisibility(View.VISIBLE);
            }
            showMessageDialog();
        }
    }

    private void showMessageDialog() {
        mTimeoutDialog = new TimeoutDialog(this);
        mTimeoutDialog.setDialogListener(this);
        mTimeoutDialog.setCanceledOnTouchOutside(false);
        mTimeoutDialog.setCancelable(false);
        mTimeoutDialog.show();
        onPause();
    }

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    public void onRecollect() {
        if (mTimeoutDialog != null) {
            mTimeoutDialog.dismiss();
        }
        if (mViewBg != null) {
            mViewBg.setVisibility(View.GONE);
        }
        onResume();
    }

    @Override
    public void onReturn() {
        if (mTimeoutDialog != null) {
            mTimeoutDialog.dismiss();
        }
        finish();
    }
//    @Override
//    public void onLivenessCompletion(FaceStatusNewEnum status, String message, HashMap<String, ImageInfo> hashMap, HashMap<String, ImageInfo> hashMap1, int i) {
//        super.onLivenessCompletion(status, message, hashMap,hashMap1,i);
//        System.out.println("OfflineFaceLivenessActivity");
//        if (status == FaceStatusNewEnum.OK && mIsCompletion) {
//            final Intent resultIntent = new Intent();
//            ///需要确认base64ImageMap是hashMap 还是hashMap1，次代码原来插件是有用的
//           // resultIntent.putExtra("bestImage", base64ImageMap.get("bestImage0"));
//            setResult(RESULT_OK, resultIntent);
//            finish();
//        } else if (status == FaceStatusNewEnum.DetectRemindCodeTimeout ||
//                status == FaceStatusNewEnum.FaceLivenessActionCodeTimeout
//
//
//        ) {
//            finish();
//        }
//    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        FaceSDKManager.release();
    }
}