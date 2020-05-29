package com.ziroom.zftools.utils.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class HttpUtil {

    @Resource
    private HttpClient httpClient;
    @Resource
    private HttpClient sslClient;

    // 维护一个本类的静态变量
    public static HttpUtil httpUtil;

    // 初始化的时候，将本类中的httpClient和sslclient赋值给静态的本类变量
    @PostConstruct
    public void init(){
        httpUtil = this;
        httpUtil.httpClient = this.httpClient;
        httpUtil.sslClient = this.sslClient;
    }

    /**
     * Get请求：带header，带querys
     *
     * @param host
     * @param path
     * @param headers
     * @param querys
     * @return
     * @throws Exception
     */
    public static HttpResponse doGet(String host, String path, Map<String, String> headers, Map<String, String> querys) throws Exception {
        HttpClient httpClient = wrapClient(host, path);
        HttpGet request = new HttpGet(buildUrl(host, path, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
            request.addHeader(e.getKey(), e.getValue());
        }
        return httpClient.execute(request);
    }

    /**
     * Get请求：带header
     *
     * @param host
     * @param path
     * @param headers
     * @return
     * @throws Exception
     */
    public static HttpResponse doGet(String host, String path, Map<String, String> headers) throws Exception {
        HttpClient httpClient = wrapClient(host, path);
        HttpGet request = new HttpGet(buildUrl(host, path, null));
        for (Map.Entry<String, String> e : headers.entrySet()) {
            request.addHeader(e.getKey(), e.getValue());
        }
        return httpClient.execute(request);
    }

    /**
     * Get请求
     *
     * @param host
     * @param path
     * @return
     * @throws Exception
     */
    public static HttpResponse doGet(String host, String path) throws Exception {
        HttpClient httpClient = wrapClient(host, path);
        HttpGet request = new HttpGet(buildUrl(host, path, null));
        return httpClient.execute(request);
    }

    /**
     * Post请求：带header，带querys，带bodys（Map<String, String>）
     *
     * @param host
     * @param path
     * @param headers
     * @param querys
     * @param bodys
     * @return
     * @throws Exception
     */
    public static HttpResponse doPost(String host, String path, Map<String, String> headers, Map<String, String> querys, Map<String, String> bodys) throws Exception {
        HttpClient httpClient = wrapClient(host, path);
        HttpPost request = new HttpPost(buildUrl(host, path, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
            request.addHeader(e.getKey(), e.getValue());
        }
        if (bodys != null) {
            List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();

            for (String key : bodys.keySet()) {
                nameValuePairList.add(new BasicNameValuePair(key, bodys.get(key)));
            }
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(nameValuePairList, "utf-8");
            formEntity.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
            request.setEntity(formEntity);
        }
        return httpClient.execute(request);
    }

    /**
     * Post请求：带header，带querys，带bodys（String）
     *
     * @param host
     * @param path
     * @param headers
     * @param querys
     * @param body
     * @return
     * @throws Exception
     */
    public static HttpResponse doPost(String host, String path, Map<String, String> headers, Map<String, String> querys, String body) throws Exception {
        HttpClient httpClient = wrapClient(host, path);
        HttpPost request = new HttpPost(buildUrl(host, path, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
            request.addHeader(e.getKey(), e.getValue());
        }
        if (StringUtils.isNotBlank(body)) {
            request.setEntity(new StringEntity(body, "utf-8"));
        }
        return httpClient.execute(request);
    }

    /**
     * Post请求：带header，带querys，带bodys（byty[]）
     *
     * @param host
     * @param path
     * @param headers
     * @param querys
     * @param body
     * @return
     * @throws Exception
     */
    public static HttpResponse doPost(String host, String path, Map<String, String> headers, Map<String, String> querys, byte[] body) throws Exception {
        HttpClient httpClient = wrapClient(host, path);
        HttpPost request = new HttpPost(buildUrl(host, path, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
            request.addHeader(e.getKey(), e.getValue());
        }
        if (body != null) {
            request.setEntity(new ByteArrayEntity(body));
        }
        return httpClient.execute(request);
    }

    /**
     * Put请求：带header，带querys，带bodys（String）
     *
     * @param host
     * @param path
     * @param headers
     * @param querys
     * @param body
     * @return
     * @throws Exception
     */
    public static HttpResponse doPut(String host, String path, Map<String, String> headers, Map<String, String> querys, String body) throws Exception {
        HttpClient httpClient = wrapClient(host, path);
        HttpPut request = new HttpPut(buildUrl(host, path, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
            request.addHeader(e.getKey(), e.getValue());
        }
        if (StringUtils.isNotBlank(body)) {
            request.setEntity(new StringEntity(body, "utf-8"));
        }
        return httpClient.execute(request);
    }

    /**
     * Put请求：带header，带querys，带bodys（byte[]）
     *
     * @param host
     * @param path
     * @param headers
     * @param querys
     * @param body
     * @return
     * @throws Exception
     */
    public static HttpResponse doPut(String host, String path, Map<String, String> headers, Map<String, String> querys, byte[] body) throws Exception {
        HttpClient httpClient = wrapClient(host, path);
        HttpPut request = new HttpPut(buildUrl(host, path, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
            request.addHeader(e.getKey(), e.getValue());
        }
        if (body != null) {
            request.setEntity(new ByteArrayEntity(body));
        }
        return httpClient.execute(request);
    }

    /**
     * Delete请求：带header，带querys，带bodys（Map<String, String>）
     *
     * @param host
     * @param path
     * @param headers
     * @param querys
     * @return
     * @throws Exception
     */
    public static HttpResponse doDelete(String host, String path, Map<String, String> headers, Map<String, String> querys) throws Exception {
        HttpClient httpClient = wrapClient(host, path);
        HttpDelete request = new HttpDelete(buildUrl(host, path, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
            request.addHeader(e.getKey(), e.getValue());
        }
        return httpClient.execute(request);
    }

    /**
     * 构建请求的Url
     *
     * @param host
     * @param path
     * @param querys
     * @return
     * @throws UnsupportedEncodingException
     */
    private static String buildUrl(String host, String path, Map<String, String> querys) throws UnsupportedEncodingException {
        StringBuilder sbUrl = new StringBuilder();
        if (!StringUtils.isBlank(host)) {
            sbUrl.append(host);
        }
        if (!StringUtils.isBlank(path)) {
            sbUrl.append(path);
        }
        if (null != querys) {
            StringBuilder sbQuery = new StringBuilder();
            for (Map.Entry<String, String> query : querys.entrySet()) {
                if (0 < sbQuery.length()) {
                    sbQuery.append("&");
                }
                if (StringUtils.isBlank(query.getKey()) && !StringUtils.isBlank(query.getValue())) {
                    sbQuery.append(query.getValue());
                }
                if (!StringUtils.isBlank(query.getKey())) {
                    sbQuery.append(query.getKey());
                    if (!StringUtils.isBlank(query.getValue())) {
                        sbQuery.append("=");
                        sbQuery.append(URLEncoder.encode(query.getValue(), "utf-8"));
                    }
                }
            }
            if (0 < sbQuery.length()) {
                sbUrl.append("?").append(sbQuery);
            }
        }
        return sbUrl.toString();
    }

    /**
     * 获取HttpClient
     *
     * @param host
     * @param path
     * @return
     */
    private static HttpClient wrapClient(String host, String path) {
        if (host != null && host.startsWith("https://")) {
            return httpUtil.sslClient;
        } else if (StringUtils.isBlank(host) && path != null && path.startsWith("https://")) {
            return httpUtil.sslClient;
        }
        return httpUtil.httpClient;
    }

    /**
     * 将结果转换成JSONObject
     *
     * @param httpResponse
     * @return
     * @throws IOException
     */
    public static JSONObject getJson(HttpResponse httpResponse) throws IOException {
        HttpEntity entity = httpResponse.getEntity();
        String resp = EntityUtils.toString(entity, "UTF-8");
        EntityUtils.consume(entity);
        return JSON.parseObject(resp);
    }

}
