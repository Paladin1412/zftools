package com.ziroom.zftools.utils.http;

import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.*;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Arrays;

@Configuration
public class HttpClientConfig {

    Logger logger = LoggerFactory.getLogger(HttpClientConfig.class);

    // 连接池的最大连接数
    @Value("${http.max-total}")
    private Integer maxTotal;
    // 每个host请求最大的连接数
    @Value("${http.default-max-perrout}")
    private Integer defaultMaxPerRoute;
    // 连接空闲多长时间（单位：毫秒）进行检查（官方推荐使用这个来检查永久链接的可用性，而不推荐每次请求的时候才去检查）
    @Value("${http.validate-after-inactivity}")
    private Integer validateAfterInactivity;
    // 从连接池中获取可用连接超时
    @Value("${http.connection-request-timeout}")
    private Integer connectionRequestTimeout;
    // 连接目标url的连接超时时间，即客服端发送请求到与目标url建立起连接的最大时间
    @Value("${http.connection-timeout}")
    private Integer connectionTimeout;
    // 等待响应超时、读取数据超时（连接上一个url后，获取response的返回等待时间 ，即在与目标url建立连接后，等待放回response的最大时间）
    @Value("${http.socket-timeout}")
    private Integer socketTimeout;
    // 设置是否开启 客户端在发送Request Message之前，先判断服务器是否愿意接受客户端发送的消息主体
    @Value("${http.expect-continue-enabled}")
    private boolean expectContinueEnabled;


    /**
     * 显示修改httpClient连接池参数，注：若未显示设置，应该有默认配置！
     *
     * @return
     */
    @Bean
    public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager() {
        // 创建出来的对象，已经设置了：协议Http和Https对应的处理Socket链接工厂对象
        PoolingHttpClientConnectionManager httpClientConnectionManager = new PoolingHttpClientConnectionManager();
        if (defaultMaxPerRoute != null && defaultMaxPerRoute != 0 && maxTotal != null && maxTotal != 0 && validateAfterInactivity != null && validateAfterInactivity != 0) {
            httpClientConnectionManager.setDefaultMaxPerRoute(defaultMaxPerRoute);
            httpClientConnectionManager.setMaxTotal(maxTotal);
            httpClientConnectionManager.setValidateAfterInactivity(validateAfterInactivity);
        }
        return httpClientConnectionManager;
    }

    /**
     * 设置网络配置器
     *
     * @return
     */
    @Bean
    public RequestConfig requestConfig() {
        return RequestConfig.custom().setConnectionRequestTimeout(connectionRequestTimeout)
                // 与服务器连接超时时间，创建socket连接的超时时间
                .setConnectTimeout(connectionTimeout)
                // socket读取数据的超时时间，从服务器获取数据的超时时间
                .setSocketTimeout(socketTimeout)
                // 设置是否开启 客户端在发送Request Message之前，先判断服务器是否愿意接受客户端发送的消息主体
                .setExpectContinueEnabled(expectContinueEnabled)
                .build();
    }

    /**
     * 实例化连接池，设置连接池管理器
     *
     * @param poolingHttpClientConnectionManager
     * @return
     */
    @Bean
    public HttpClientBuilder httpClientBuilder(PoolingHttpClientConnectionManager poolingHttpClientConnectionManager) {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // 设置连接池
        httpClientBuilder.setConnectionManager(poolingHttpClientConnectionManager);
        // 设置超时时间
        httpClientBuilder.setDefaultRequestConfig(requestConfig());
        // 定义连接管理器将由多个客户端实例共享。如果连接管理器是共享的，则其生命周期应由调用者管理，如果客户端关闭则不会关闭。
        httpClientBuilder.setConnectionManagerShared(expectContinueEnabled);
        // 设置Keep-Alive
        ConnectionKeepAliveStrategy myStrategy = new ConnectionKeepAliveStrategy() {
            public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
                // Honor 'keep-alive' header
                HeaderElementIterator it = new BasicHeaderElementIterator(
                        response.headerIterator(HTTP.CONN_KEEP_ALIVE));
                while (it.hasNext()) {
                    HeaderElement he = it.nextElement();
                    String param = he.getName();
                    String value = he.getValue();
                    if (value != null && param.equalsIgnoreCase("timeout")) {
                        try {
                            return Long.parseLong(value) * 1000;
                        } catch (NumberFormatException e) {
                            logger.info(String.valueOf(e.getStackTrace()));
                        }
                    }
                }
                HttpHost target = (HttpHost) context.getAttribute(
                        HttpClientContext.HTTP_TARGET_HOST);
                if ("intsall.q.ziroom.com".equalsIgnoreCase(target.getHostName()) || "intsall.t.ziroom.com".equalsIgnoreCase(target.getHostName()) || "zwhite.q.ziroom.com".equalsIgnoreCase(target.getHostName()) || "zwhite.t.ziroom.com".equalsIgnoreCase(target.getHostName())) {
                    // Keep alive for 5 seconds only
                    return 60 * 1000;
                } else {
                    // otherwise keep alive for 30 seconds
                    return 30 * 1000;
                }
            }
        };
        httpClientBuilder.setKeepAliveStrategy(myStrategy);
//        httpClientBuilder.setRetryHandler(new MyHttpRequestRetryHandler());
//        httpClientBuilder.disableAutomaticRetries();
        // 启动线程，5秒钟清空一次失效连接
        new IdleConnectionMonitorThread(poolingHttpClientConnectionManager).start();
        return httpClientBuilder;
    }

    /**
     * 创建HttpClient对象
     *
     * @param httpClientBuilder
     * @return
     */
    @Bean
    public HttpClient httpClient(HttpClientBuilder httpClientBuilder) {
        return httpClientBuilder.build();
    }

    /**
     * 在调用SSL之前需要重写验证方法，取消检测SSL，创建ConnectionManager，添加Connection配置信息
     *
     * @return
     */
    @Bean
    public HttpClient sslClient() {
        try {
            // 在调用SSL之前需要重写验证方法，取消检测SSL
            X509TrustManager trustManager = new X509TrustManager() {
                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                @Override
                public void checkClientTrusted(X509Certificate[] xcs, String str) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] xcs, String str) {
                }
            };
            SSLContext ctx = SSLContext.getInstance(SSLConnectionSocketFactory.TLS);
            ctx.init(null, new TrustManager[]{trustManager}, null);
            SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(ctx, NoopHostnameVerifier.INSTANCE);
            // 创建Registry
            RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT)
                    .setExpectContinueEnabled(Boolean.TRUE).setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
                    .setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC)).build();
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.INSTANCE)
                    .register("https", socketFactory).build();
            // 创建ConnectionManager，添加Connection配置信息
            PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            CloseableHttpClient closeableHttpClient = HttpClients.custom().setConnectionManager(connectionManager).setDefaultRequestConfig(requestConfig).build();
            return closeableHttpClient;
        } catch (KeyManagementException ex) {
            throw new RuntimeException(ex);
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }

}
