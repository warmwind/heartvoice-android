package com.HeartVoice;

import android.content.Context;
import android.widget.EditText;
import com.iflytek.speech.RecognizerResult;
import com.iflytek.speech.SpeechError;
import com.iflytek.ui.RecognizerDialog;
import com.iflytek.ui.RecognizerDialogListener;

import java.util.ArrayList;

public class RecognizerDialogInitializer {
    private final Context context;
    private final EditText editText;

    public RecognizerDialogInitializer(Context context, EditText editText) {
        this.context = context;
        this.editText = editText;
    }

    public void show() {
        final StringBuilder text = new StringBuilder();
        RecognizerDialogListener recognizeListener = new RecognizerDialogListener() {
            public void onResults(ArrayList<RecognizerResult> results, boolean isLast) {
                // 一般情况下会通过onResults接口多次返回结果,完整的识别内容是多次结果的累加.
                for (int i = 0; i < results.size(); i++) {
                    text.append(results.get(i).text);
                }
            }

            public void onEnd(SpeechError error) {
                // error为null表示会话成功,可在此处理text结果,error不为null,表示发生错误,对话框停留在错误页面
                if (error == null) {
                    editText.setText(text.toString());
                }
            }
        };

        // 创建识别对话框,需传入正确appid
        RecognizerDialog isrDialog = new RecognizerDialog(context, context.getString(R.string.appid));
        isrDialog.setEngine("sms", null, null);
        isrDialog.setListener(recognizeListener);
        isrDialog.show();
    }

}
