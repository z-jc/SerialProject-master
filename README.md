# SerialProject-master
这是一个串口Demo,里面SerialportLibrary是封装好的串口库
1、在Activity使用的时候首先设置接收串口数据的回调接口,
  implements SerialCallBack{}
  SerialPortCallBackUtils.setCallBack(this);
2、然后打开串口
  boolean isOpen = SerialPortUtil.open("/dev/ttyS3", 115200, 0);//里面的参数根据自己的需求自己更改
  if (isOpen) {
      Log.e("TAG", "打开成功");
  } else {
      Log.e("TAG", "打开失败");
  }
3、发送数据
  byte[] mByte = {0x7E, 0x01, 0x00, 0x00};
  SerialPortUtil.sendString(ByteUtil.getSum16(mByte, mByte.length));
4、接收数据
  在实现的回调接口内读取数据
  @Override
  public void onSerialPortData(String serialPortData) {
      Log.e("TAG", "来自串口的数据:" + serialPortData);
  }
5、关闭数据
  SerialPortUtil.close();
