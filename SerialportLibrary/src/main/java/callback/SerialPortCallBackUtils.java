package callback;

public class SerialPortCallBackUtils {

    private static SerialCallBack mCallBack;

    public static void setCallBack(SerialCallBack callBack) {
        mCallBack = callBack;
    }

    public static void doCallBackMethod(String info){
        mCallBack.onSerialPortData(info);
    }
}