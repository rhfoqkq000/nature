package com.donga.nature.npe;
import android.support.multidex.MultiDexApplication;

import org.acra.ACRA;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;

@ReportsCrashes
        (
                // Google Docs 로 전송시에만 사용됨 비워두면 된다.
//                formKey                 = "",
                // Crash 발생 즉시 Toast 메시지로 알림 : Toast 내용
                resToastText            = R.string.crash_toast_text,
                // Dialog 형태로 알림
                mode                    = ReportingInteractionMode.DIALOG,
                // Dialog 표시 아이콘
                resDialogIcon           = android.R.drawable.ic_dialog_info,
                // Dialog Title 표시 문구
                resDialogTitle          = R.string.crash_dialog_title,
                // Dialog 본문 표시 문구
                resDialogText           = R.string.crash_dialog_text,
                // Dialog OK 선택시 발생 Toast
                resDialogOkToast        = R.string.crash_dialog_ok_toast,
                // Dialog OK 선택시 메일 발송
                mailTo       = "npe.dongauniv@gmail.com"  // 마지막에 세미콜론이 붙지 않음.
        )


public class CrashApplication extends MultiDexApplication
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        ACRA.init( this );
    }
}