package com.atguigu.es.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;


public class ESTest_Doc_Insert {
    public static void main(String[] args) throws Exception {
        //创建ES客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                //指定要连接的ES集群
                RestClient.builder(new HttpHost("192.168.88.131", 9200, "http"))
        );

        //插入数据
        IndexRequest request = new IndexRequest();
        request.index("user").id("1001");
        User user = new User();
        user.setName("zhangsan");
        user.setSex("男");
        user.setAge(30);

        //向ES插入数据必须将数据转换为json格式
        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(user);
        request.source(userJson, XContentType.JSON);

        IndexResponse response = esClient.index(request, RequestOptions.DEFAULT);

        System.out.println(response.getResult());



        //关闭es客户端
        esClient.close();
    }
}
