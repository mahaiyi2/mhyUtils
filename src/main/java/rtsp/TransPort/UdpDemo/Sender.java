package rtsp.TransPort.UdpDemo;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class Sender {  
    public static void main(String[] args) {  
        DatagramSocket ds = null;  
        try {  
            //定义一个UDP的Socket来发送数据  
            ds = new DatagramSocket();  
            String hello = "hello world";  
            //定义一个UDP的数据发送包来发送数据，inetSocketAddress表示要接收的地址  
            DatagramPacket dp = new DatagramPacket(hello.getBytes(),   
                    hello.getBytes().length, new InetSocketAddress("127.0.0.1", 8080));  
              
            for (int i = 0; i < 10; i++) {  
                ds.send(dp);  
                Thread.sleep(1000);  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            if (ds != null) ds.close();  
        }  
    }  
}  
