package com.customer1.service.impl;

import com.customer1.service.EurekaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class EurekaServiceImpl implements EurekaService {
    @Autowired
    private DiscoveryClient discoveryClient;

    @Override
    public String discoverTest() {
        RestTemplate restTemplate = new RestTemplate();
        List<ServiceInstance> instances = discoveryClient.getInstances("provider-2");
        if (instances.size() == 0) {
            return "无实例";
        } else {
            String serviceUrl = instances.get(0).getUri().toString() + "/discoverTest";
            ResponseEntity<String> responseEntity = restTemplate.exchange(serviceUrl, HttpMethod.POST, null, String.class);
            String result = responseEntity.getBody();
            return result;
        }
    }
}
