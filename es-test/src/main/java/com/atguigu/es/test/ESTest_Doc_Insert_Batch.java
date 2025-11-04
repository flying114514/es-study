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
        request.add(new IndexRequest("user").id("1001").source(XContentType.JSON, "name", "zhangsan", "sex", "男", "age", 18));
        request.add(new IndexRequest("user").id("1002").source(XContentType.JSON, "name", "lisi", "sex", "男", "age", 20));
        request.add(new IndexRequest("user").id("1003").source(XContentType.JSON, "name", "wangwu", "sex", "女", "age", 38));
        request.add(new IndexRequest("user").id("1004").source(XContentType.JSON, "name", "wangwu1", "sex", "男", "age", 48));
        request.add(new IndexRequest("user").id("1005").source(XContentType.JSON, "name", "wangwu2", "sex", "女", "age", 18));
        request.add(new IndexRequest("user").id("1006").source(XContentType.JSON, "name", "wangwu3", "sex", "男", "age", 58));

        BulkResponse response = esClient.bulk(request, RequestOptions.DEFAULT);
        System.out.println(response.getTook());
        System.out.println(Arrays.toString(response.getItems()));

        //关闭es客户端
        esClient.close();
    }
}
