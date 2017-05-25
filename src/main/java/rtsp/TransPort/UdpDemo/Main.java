package rtsp.TransPort.UdpDemo;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;

public class Main {

	public static void main(String[] args) {
	
		try {
			/*DatagramSocket localIn = new DatagramSocket(8888);
			//DatagramSocket localIn = new DatagramSocket(8888);
			byte[] buf = new byte[1024];  
            //定义UDP的数据接收包  
            DatagramPacket dp = new DatagramPacket(buf, buf.length); 
			while(true){
				localIn.receive(dp);
			}
			*/
			Socket[] sockets = new Socket[20]; 
			for (int i = 0; i < 10; i++) {
				System.out.println("start"+i);
				sockets[i] = new Socket("168.168.13.14",8888);
				System.out.println(i);
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated constructor stub
	}
}
