package rtsp.TransPort;

public class Route {
    public Route() {}
    //jPortMap绑定的IP
    String  LocalIP="";
    //监听的端口
    int     LocalPort=0;
    //转发数据的目标机器IP
    String  DestHost="";
    //转发的目标端口
    int     DestPort=0;
    //这个转发上许可进入的IP列表
    String  AllowClient="";

    //重写的toString方法，输出具体Route对象的信息以便debug    
    public String toString() {
        StringBuffer stb = new StringBuffer();
        stb.append(" LocalADD  " + LocalIP);
        stb.append(" :" + LocalPort);
        stb.append(" --->DestHost " + DestHost);
        stb.append(" :" + DestPort);
        stb.append("   (AllowClient) " + AllowClient);
        return stb.toString();
    }
}