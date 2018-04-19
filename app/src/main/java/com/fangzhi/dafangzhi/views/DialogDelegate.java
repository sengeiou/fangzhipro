package com.fangzhi.dafangzhi.views;

/**
 * Created by zhangyu on 2016/7/6.
 */
public interface DialogDelegate {


    void showProgressDialog(boolean canCancel, String msg);  //等待加载框


    //不能关闭 并提示信息
    void showNormalDialog(String option, String msg, final OnDialogListener confirmListener,
                          final OnDialogListener cancelListener);

    //确定和取消
    void showWarningDialog(String option, String msg, OnDialogListener listener);

    //确定和取消
    void showWarningDialog1(String option, String msg, OnDialogListener listener,OnDialogListener listener1);

    //提示成功
    void showSuccessDialog(String option, String msg, OnDialogListener listener);

    //提示错误
    void showErrorDialog(String option, String msg, OnDialogListener listener);

    //配合showProgressDialog使用 在这是成功
    void stopProgressWithSuccess(String option, String msg, OnDialogListener listener);

    //配合showProgressDialog使用 在这是错误
    void stopProgressWithFailed(String option, String msg);

    //配合showProgressDialog使用 在这是警告
    void stopProgressWithWarning(String option, String msg, OnDialogListener listener);

    void clearDialog();

    interface OnDialogListener {
        void onClick();
    }
}
