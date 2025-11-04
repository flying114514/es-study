package com.atguigu.es.test;

import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.util.Arrays;


public class ESTest_Doc_Insert_Batch {
    public static void main(String[] args) throws Exception {
        //创建ES客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                //指定要连接的ES集群
                RestClient.builder(new HttpHost("192.168.88.131", 9200, "http"))
        );

        //批量插入数据
        BulkRequest request = new BulkRequest();
        request.add(new IndexRequest("user").id("1001").source(XContentType.JSON, "name", "zhangsan"));
        request.add(new IndexRequest("user").id("1002").source(XContentType.JSON, "name", "lisi"));
        request.add(new IndexRequest("user").id("1003").source(XContentType.JSON, "name", "wangwu"));

        BulkResponse response = esClient.bulk(request, RequestOptions.DEFAULT);
        System.out.println(response.getTook());
        System.out.println(Arrays.toString(response.getItems()));

        //关闭es客户端
        esClient.close();
    }
}
