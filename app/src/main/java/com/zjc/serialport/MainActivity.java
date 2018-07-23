package com.zjc.serialport;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import callback.SerialCallBack;
import callback.SerialPortCallBackUtils;
import util.ByteUtil;
import android_serialport_api.SerialPortUtil;

public class MainActivity extends Activity implements View.OnClickListener, SerialCallBack {

    private Button mBtnOpen;
    private Button mBtnSend;
    private Button mBtnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SerialPortCallBackUtils.setCallBack(this);
        initView();
    }

    private void initView() {
        mBtnOpen = (Button) findViewById(R.id.btn_open);
        mBtnSend = (Button) findViewById(R.id.btn_send);
        mBtnClose = (Button) findViewById(R.id.btn_close);

        mBtnOpen.setOnClickListener(this);
        mBtnSend.setOnClickListener(this);
        mBtnClose.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_open:
                boolean isOpen = SerialPortUtil.open("/dev/ttyS3", 115200, 0);
                if (isOpen) {
                    Log.e("TAG", "打开成功");
                } else {
                    Log.e("TAG", "打开失败");
                }
                break;
            case R.id.btn_send:
                /**
                 * 读取温湿度指令
                 */

                byte[] mByte = {0x7E, 0x01, 0x00, 0x00};
                SerialPortUtil.sendString(ByteUtil.getSum16(mByte, mByte.length));
                break;
            case R.id.btn_close:
                boolean isClose = SerialPortUtil.close();
                if (isClose) {
                    Log.e("TAG", "关闭成功");
                }
                break;
        }
    }

    @Override
    public void onSerialPortData(String serialPortData) {
        Log.e("TAG", "来自串口的数据:" + serialPortData);
    }
}