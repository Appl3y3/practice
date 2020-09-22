package com.appleye.io;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

/**
 * @author Appleye
 * @time 2020-09-11 16:20
 */
public class IOClient {
    public static void main(String[] args) {
        // TODO 创建多个线程，模拟多个客户端连接服务端
        new Thread(() -> {

            Socket socket = null;
            try {
                socket = new Socket("127.0.0.1", 3333);
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (;;) {

                    try {
                        socket.getOutputStream().write((new Date() + ": hello world").getBytes());
                        Thread.sleep(2000);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

        }).start();




        new Thread(() -> {

            Socket socket = null;
            try {
                socket = new Socket("127.0.0.1", 3333);
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (;;) {

                try {
                    socket.getOutputStream().write((new Date() + ": hello world - " + Thread.currentThread().getName()).getBytes());
                    Thread.sleep(2000);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }).start();



    }
}
