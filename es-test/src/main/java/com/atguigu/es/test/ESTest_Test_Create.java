package com.atguigu.es.test;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;


public class ESTest_Test_Create {
    public static void main(String[] args) throws Exception {
        //创建ES客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                //指定要连接的ES集群
                RestClient.builder(new HttpHost("192.168.88.131", 9200, "http"))
        );

        //创建新索引
        CreateIndexRequest user = new CreateIndexRequest("user");
        //传入新索引和默认配置
        CreateIndexResponse createIndexResponse = esClient.indices().create(user, RequestOptions.DEFAULT);

        //得到响应状态
        System.out.println(createIndexResponse.isAcknowledged());

        //关闭es客户端
        esClient.close();
    }
}
