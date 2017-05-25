package rtsp.TransPort.UdpDemo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Receiver {  
    
    public static void main(String[] args) {  
        DatagramSocket ds = null;  
        try {  
            //UDP接收端  
            ds = new DatagramSocket(8080);  
            //定义将UDP的数据包接收到什么地方  
            byte[] buf = new byte[1024];  
            //定义UDP的数据接收包  
            DatagramPacket dp = new DatagramPacket(buf, buf.length);  
            while (true) {  
                //接收数据包  
                ds.receive(dp);  
                String string = new String(dp.getData(), 0, dp.getLength());  
                System.out.println("length:" + dp.getLength() + "->" + string);  
            }  
        } catch (SocketException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            if (ds != null)   
                ds.close();  
        }  
    }  
}  