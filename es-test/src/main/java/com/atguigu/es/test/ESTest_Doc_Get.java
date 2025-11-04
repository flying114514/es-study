package com.atguigu.es.test;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;


public class ESTest_Doc_Get {
    public static void main(String[] args) throws Exception {
        //创建ES客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                //指定要连接的ES集群
                RestClient.builder(new HttpHost("192.168.88.131", 9200, "http"))
        );

        //查询文档
        GetRequest request = new GetRequest();
        request.index("user").id("1001");
        GetResponse response = esClient.get(request, RequestOptions.DEFAULT);

        System.out.println(response.getSourceAsString());

        //关闭es客户端
        esClient.close();
    }
}
