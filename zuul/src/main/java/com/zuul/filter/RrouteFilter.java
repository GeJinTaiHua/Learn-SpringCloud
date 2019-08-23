//package com.zuul.filter;
//
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import com.zuul.model.AbTestingRoute;
//import org.apache.http.Header;
//import org.apache.http.HttpHost;
//import org.apache.http.HttpRequest;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpPatch;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.client.methods.HttpPut;
//import org.apache.http.entity.ContentType;
//import org.apache.http.entity.InputStreamEntity;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.message.BasicHeader;
//import org.apache.http.message.BasicHttpRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.netflix.zuul.filters.ProxyRequestHelper;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.client.HttpClientErrorException;
//import org.springframework.web.client.RestTemplate;
//
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
///**
// * 路由过滤器
// */
//@Component
//public class RrouteFilter extends ZuulFilter {
//
//    @Autowired
//    FilterUtils filterUtils;
//
//    @Autowired
//    RestTemplate restTemplate;
//
//    @Override
//    public String filterType() {
//        return "route";
//    }
//
//    @Override
//    public int filterOrder() {
//        return 1;
//    }
//
//    @Override
//    public boolean shouldFilter() {
//        return true;
//    }
//
//    /**
//     * A/B测试
//     * 部分路由转到B服务
//     */
//    @Override
//    public Object run() {
//        // 查看路由记录是否存在
//        AbTestingRoute abTestRoute = getAbRoutingInfo(filterUtils.getServiceId());
//
//        if (abTestRoute != null && useSpecialRoute(abTestRoute)) {
//            // 将完整url构建到B服务
//            RequestContext ctx = RequestContext.getCurrentContext();
//            String route = buildRouteString(ctx.getRequest().getRequestURI(),
//                    abTestRoute.getEndpoint(),
//                    ctx.get("serviceId").toString());
//            // 转发到其他服务
//            forwardToSpecialRoute(route);
//        }
//
//        return null;
//    }
//
//    /**
//     * 调用B服务查看路由记录是否存在
//     */
//    private AbTestingRoute getAbRoutingInfo(String serviceName) {
//        ResponseEntity<AbTestingRoute> restExchange = null;
//        try {
//            restExchange = restTemplate.exchange(
//                    "http://B/v1/{serviceName}",
//                    HttpMethod.GET,
//                    null, AbTestingRoute.class, serviceName);
//        } catch (HttpClientErrorException ex) {
//            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
//                return null;
//            }
//            throw ex;
//        }
//        return restExchange.getBody();
//    }
//
//    /**
//     * 是否将请求转发到替代服务
//     */
//    private boolean useSpecialRoute(AbTestingRoute testRoute) {
//        Random random = new Random();
//
//        // 检查路由是否活跃
//        if (testRoute.getActive().equals("N")) {
//            return false;
//        }
//
//        int value = random.nextInt((10 - 1) + 1) + 1;
//        if (testRoute.getWeight() < value) {
//            return true;
//        }
//
//        return false;
//    }
//
//    private String buildRouteString(String oldEndpoint, String newEndpoint, String serviceName) {
//        int index = oldEndpoint.indexOf(serviceName);
//        String strippedRoute = oldEndpoint.substring(index + serviceName.length());
//        System.out.println("Target route: " + String.format("%s/%s", newEndpoint, strippedRoute));
//        return String.format("%s/%s", newEndpoint, strippedRoute);
//    }
//
//    /**
//     * 用于代理服务请求的辅助方法
//     */
//    private ProxyRequestHelper helper = new ProxyRequestHelper();
//
//    /**
//     * 调用替代服务
//     */
//    private void forwardToSpecialRoute(String route) {
//        RequestContext context = RequestContext.getCurrentContext();
//        HttpServletRequest request = context.getRequest();
//
//        // 创建将发送到服务的所有HTTP请求首部的副本
//        MultiValueMap<String, String> headers = this.helper.buildZuulRequestHeaders(request);
//        // 创建所有HTTP请求参数的副本
//        MultiValueMap<String, String> params = this.helper.buildZuulRequestQueryParams(request);
//        // 创建将被转发到替代服务的HTTP主体的副本
//        InputStream requestEntity = getRequestBody(request);
//
//        if (request.getContentLength() < 0) {
//            context.setChunkedRequestBody();
//        }
//
//        this.helper.addIgnoredHeaders();
//
//        CloseableHttpClient httpClient = null;
//        try {
//            httpClient = HttpClients.createDefault();
//            HttpResponse response = forward(httpClient, request.getMethod().toUpperCase(), route, request, headers,
//                    params, requestEntity);
//
//            this.helper.setResponse(response.getStatusLine().getStatusCode(),
//                    response.getEntity() == null ? null : response.getEntity().getContent(),
//                    revertHeaders(response.getAllHeaders()));
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        } finally {
//            try {
//                httpClient.close();
//            } catch (IOException ex) {
//            }
//        }
//    }
//
//    private Header[] convertHeaders(MultiValueMap<String, String> headers) {
//        List<Header> list = new ArrayList<>();
//        for (String name : headers.keySet()) {
//            for (String value : headers.get(name)) {
//                list.add(new BasicHeader(name, value));
//            }
//        }
//        return list.toArray(new BasicHeader[0]);
//    }
//
//    private InputStream getRequestBody(HttpServletRequest request) {
//        InputStream requestEntity = null;
//        try {
//            requestEntity = request.getInputStream();
//        } catch (IOException ex) {
//            // no requestBody is ok.
//        }
//        return requestEntity;
//    }
//
//    private MultiValueMap<String, String> revertHeaders(Header[] headers) {
//        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
//        for (Header header : headers) {
//            String name = header.getName();
//            if (!map.containsKey(name)) {
//                map.put(name, new ArrayList<String>());
//            }
//            map.get(name).add(header.getValue());
//        }
//        return map;
//    }
//
//    /**
//     * 实际调用服务
//     */
//    private HttpResponse forward(HttpClient httpclient, String verb, String uri,
//                                 HttpServletRequest request, MultiValueMap<String, String> headers,
//                                 MultiValueMap<String, String> params, InputStream requestEntity) throws Exception {
//        URL host = new URL(uri);
//        HttpHost httpHost = new HttpHost(host.getHost(), host.getPort(), host.getProtocol());
//
//        HttpRequest httpRequest;
//        int contentLength = request.getContentLength();
//        InputStreamEntity entity = new InputStreamEntity(requestEntity, contentLength,
//                request.getContentType() != null ? ContentType.create(request.getContentType()) : null);
//
//        switch (verb.toUpperCase()) {
//            case "POST":
//                HttpPost httpPost = new HttpPost(uri);
//                httpRequest = httpPost;
//                httpPost.setEntity(entity);
//                break;
//            case "PUT":
//                HttpPut httpPut = new HttpPut(uri);
//                httpRequest = httpPut;
//                httpPut.setEntity(entity);
//                break;
//            case "PATCH":
//                HttpPatch httpPatch = new HttpPatch(uri);
//                httpRequest = httpPatch;
//                httpPatch.setEntity(entity);
//                break;
//            default:
//                httpRequest = new BasicHttpRequest(verb, uri);
//        }
//
//        try {
//            httpRequest.setHeaders(convertHeaders(headers));
//            HttpResponse zuulResponse = httpclient.execute(httpHost, httpRequest);
//            return zuulResponse;
//        } finally {
//        }
//    }
//}
