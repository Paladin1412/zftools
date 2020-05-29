package com.ziroom.zftools.utils.http;

import org.apache.http.conn.HttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class IdleConnectionMonitorThread extends Thread {

    Logger logger = LoggerFactory.getLogger(IdleConnectionMonitorThread.class);

    private final HttpClientConnectionManager connMgr;
    private volatile boolean shutdown;

    public IdleConnectionMonitorThread(HttpClientConnectionManager connMgr) {
        super();
        this.connMgr = connMgr;
    }

    @Override
    public void run() {
        try {
            while (!shutdown) {
                synchronized (this) {
                    wait(5000);
                    logger.info("【定时清除过期连接开始...】");
                    // 关闭超时的连接
                    connMgr.closeExpiredConnections();
                    // 关闭空闲时间大于30s的连接
                    connMgr.closeIdleConnections(30, TimeUnit.SECONDS);
                }
            }
        } catch (InterruptedException e) {
            // terminate
            logger.info(String.valueOf(e.getStackTrace()));
        }
    }

    public void shutdown() {
        shutdown = true;
        synchronized (this) {
            notifyAll();
        }
    }

}
