package com.example.spark.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SparkRequest {

    private String collectionName;
    private String databaseName;
    private List<OrderBy> orderBy;
    private List<SortBy> sortBy;
    private int limit;
    private String columnFilter;
    private CacheFilter cacheFilter;

}
