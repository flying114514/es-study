package com.atguigu.es.test;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;


public class ESTest_Test_Delete {
    public static void main(String[] args) throws Exception {
        //创建ES客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                //指定要连接的ES集群
                RestClient.builder(new HttpHost("192.168.88.131", 9200, "http"))
        );

        DeleteIndexRequest request = new DeleteIndexRequest("user");

        AcknowledgedResponse delete = esClient.indices().delete(request, RequestOptions.DEFAULT);

        System.out.println(delete.isAcknowledged());
        //关闭es客户端
        esClient.close();
    }
}
