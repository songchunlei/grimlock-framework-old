package org.grimlock.thread.interrupt;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * @Author:chunlei.song@live.com
 * @Description:
 * @Date Create in 11:16 2017-12-29
 * @Modified By:
 */
public class OverrideInterrupt extends Thread {
    private final Socket socket;
    private final InputStream in;

    public OverrideInterrupt(Socket socket,InputStream in)
    {
        this.socket = socket;
        this.in = in;
    }

    @Override
    public void interrupt(){
        try {
            //关闭底层的套接字
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            super.interrupt();
        }
    }


}
