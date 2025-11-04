package com.atguigu.es.test;

import org.apache.http.HttpHost;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;


public class ESTest_Doc_Update {
    public static void main(String[] args) throws Exception {
        //创建ES客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                //指定要连接的ES集群
                RestClient.builder(new HttpHost("192.168.88.131", 9200, "http"))
        );

        //修改数据
        UpdateRequest request = new UpdateRequest();
        request.index("user").id("1001");

        //向ES修改入数据必须将数据转换为json格式
        request.doc(XContentType.JSON, "sex", "女");

        UpdateResponse response = esClient.update(request, RequestOptions.DEFAULT);

        System.out.println(response.getResult());


        //关闭es客户端
        esClient.close();
    }
}
