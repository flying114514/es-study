package com.atguigu.es.test;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;


public class ESTest_Doc_Query {
    public static void main(String[] args) throws Exception {
        //创建ES客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                //指定要连接的ES集群
                RestClient.builder(new HttpHost("192.168.88.131", 9200, "http"))
        );

//        //查询索引中全部数据
//        SearchRequest request = new SearchRequest();
//        request.indices("user");
//
//        request.source(new SearchSourceBuilder().query(QueryBuilders.matchAllQuery()));
//        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
//        System.out.println(response.getTook());
//        SearchHits hits = response.getHits();
//        System.out.println(hits);
//        System.out.println("总条数：" + hits.getTotalHits());
//
//        for (SearchHit hit : hits) {
//            System.out.println(hit.getSourceAsString());
//        }

//        //条件查询索引中数据
//        SearchRequest request = new SearchRequest();
//        request.indices("user");
//
//        request.source(new SearchSourceBuilder().query(QueryBuilders.termQuery("age",18)));
//        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
//        System.out.println(response.getTook());
//        SearchHits hits = response.getHits();
//        System.out.println(hits);
//        System.out.println("总条数：" + hits.getTotalHits());
//
//        for (SearchHit hit : hits) {
//            System.out.println(hit.getSourceAsString());
//        }

//        //分页查询
//        SearchRequest request = new SearchRequest();
//        request.indices("user");
//
//        SearchSourceBuilder builder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
//        builder.from(0);
//        builder.size(2);
//
//        request.source(builder);
//
//        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
//
//        System.out.println(response.getTook());
//        SearchHits hits = response.getHits();
//        System.out.println(hits);
//        System.out.println("总条数：" + hits.getTotalHits());
//
//        for (SearchHit hit : hits) {
//            System.out.println(hit.getSourceAsString());
//        }

//        //查询排序
//        SearchRequest request = new SearchRequest();
//        request.indices("user");
//
//        SearchSourceBuilder builder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
//        builder.sort("age", SortOrder.DESC);
//
//        request.source(builder);
//
//        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
//
//        System.out.println(response.getTook());
//        SearchHits hits = response.getHits();
//        System.out.println(hits);
//        System.out.println("总条数：" + hits.getTotalHits());
//
//        for (SearchHit hit : hits) {
//            System.out.println(hit.getSourceAsString());
//        }

//        //过滤字段
//        SearchRequest request = new SearchRequest();
//        request.indices("user");
//
//        SearchSourceBuilder builder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
//        String[] excludes = {"sex"};
//        String[] includes = {"age"};
//        builder.fetchSource(includes, excludes);
//
//        request.source(builder);
//
//        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
//
//        System.out.println(response.getTook());
//        SearchHits hits = response.getHits();
//        System.out.println(hits);
//        System.out.println("总条数：" + hits.getTotalHits());
//
//        for (SearchHit hit : hits) {
//            System.out.println(hit.getSourceAsString());
//        }

//        //组合查询
//        //创建request
//        SearchRequest request = new SearchRequest();
//        //指定索引
//        request.indices("user");
//        //创建查询条件
//        SearchSourceBuilder builder = new SearchSourceBuilder();
//        //创建布尔查询
//        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//        //添加查询条件
////        boolQueryBuilder.must(QueryBuilders.matchQuery("age",18));
////        boolQueryBuilder.mustNot(QueryBuilders.matchQuery("sex","女"));
//        boolQueryBuilder.should(QueryBuilders.matchQuery("age",18));
//        boolQueryBuilder.should(QueryBuilders.matchQuery("age",28));
//
//
//        //设置查询条件
//        builder.query(boolQueryBuilder);
//        //设置查询
//        request.source(builder);
//
//        //执行查询
//        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
//
//        System.out.println(response.getTook());
//        SearchHits hits = response.getHits();
//        System.out.println(hits);
//        System.out.println("总条数：" + hits.getTotalHits());
//
//        for (SearchHit hit : hits) {
//            System.out.println(hit.getSourceAsString());
//        }

        //范围查询
        //创建request
        SearchRequest request = new SearchRequest();
        //指定索引
        request.indices("user");
        //创建查询条件
        SearchSourceBuilder builder = new SearchSourceBuilder();
        //创建布尔查询
        RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("age");

        rangeQuery.gte(30);
        rangeQuery.lte(40);
        //设置查询条件
        builder.query(rangeQuery);
        //设置查询
        request.source(builder);

        //执行查询
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);

        System.out.println(response.getTook());
        SearchHits hits = response.getHits();
        System.out.println(hits);
        System.out.println("总条数：" + hits.getTotalHits());

        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }
        //关闭es客户端
        esClient.close();
    }
}
