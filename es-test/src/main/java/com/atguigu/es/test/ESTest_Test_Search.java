package com.atguigu.es.test;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;


public class ESTest_Test_Search {
    public static void main(String[] args) throws Exception {
        //创建ES客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                //指定要连接的ES集群
                RestClient.builder(new HttpHost("192.168.88.131", 9200, "http"))
        );

        GetIndexRequest request = new GetIndexRequest("user");

        GetIndexResponse getIndexResponse = esClient.indices().get(request, RequestOptions.DEFAULT);
        //得到响应状态
        System.out.println(getIndexResponse.getAliases());
        System.out.println(getIndexResponse.getMappings());
        System.out.println(getIndexResponse.getSettings());

        //关闭es客户端
        esClient.close();
    }
}
