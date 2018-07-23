package callback;

/**
 * @Params:一个串口数据回调接口
 */
public interface SerialCallBack {
    void onSerialPortData(String serialPortData);
}