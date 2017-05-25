package rtsp.TransPort;

import java.net.*;
import java.io.*;

/**
 * Title: 端口转发器
 * Description: 对连结进行转发处理
 * Copyright: Copyright (c) 2005
 * Company: www.NetJava.org
 * @author javafound
 * @version 1.0
 */

public class Transfer extends Thread {
     /**
     * 创建传输对象
     * @param s Socket   :进入的socket
     * @param route Route:转发配置
     */
    public Transfer(Socket s, Route route) {
        this.route = route;
        this.socket = s;
        this.start();
    }
    // 执行操作的线程
    public void run() {
        Socket outbound = null;
        try {
            outbound = new Socket(route.DestHost, route.DestPort);
            socket.setSoTimeout(TIMEOUT);
            //InputStream is = socket.getInputStream();
            outbound.setSoTimeout(TIMEOUT);
            //OutputStream os = outbound.getOutputStream();
            pipe(socket.getInputStream(), outbound.getInputStream(), outbound.getOutputStream(), socket.getOutputStream());
        } catch (Exception e) {
        	e.printStackTrace();
            SysLog.severe(" transfer error:" +route.toString()+ " :" + e);
        } finally {
            SysLog.warning("Disconnect :"+ route.toString());
            closeSocket(outbound);
            closeSocket(socket);
        }
    }
    
/**
 *传输的实现方法
 *private   void pipe(InputStream is0, InputStream is1,
              OutputStream os0, OutputStream os1) {
 */
 private   void pipe(InputStream localIn, InputStream destIn,
              OutputStream destOut, OutputStream localOut) {
        try {
            int ir;
            byte bytes[] = new byte[BUFSIZ];
            while (true) {
                try {
                    if ((ir = localIn.read(bytes)) > 0) {
                    	System.out.println("收到请求：" + new String(bytes));
                        destOut.write(bytes, 0, ir);
                    } else if (ir < 0) {
                        break;
                    }
                } catch (InterruptedIOException e) {
                	e.printStackTrace();
                }
                try {
                    if ((ir = destIn.read(bytes)) > 0) {
                        localOut.write(bytes, 0, ir);
                        // if (logging) writeLog(bytes,0,ir,false);
                    } else if (ir < 0) {
                        break;
                    }
                } catch (InterruptedIOException e) {
                	e.printStackTrace();
                }
            }
        } catch (Exception e0) {
        	//e0.printStackTrace();
            SysLog.warning(" Method pipe" + this.route.toString() + " error:" +
                           e0);
        }
    }
    //关闭socket
     void closeSocket(Socket s) {
        try {
            s.close();
        } catch (Exception ef) {
        }
    }
   //传输任务的Route对象
Route route = null;
     // 传入数据用的Socket
Socket socket;

   //超时
   static private int TIMEOUT = 150;
   //缓存
   static private int BUFSIZ = 2024;
}