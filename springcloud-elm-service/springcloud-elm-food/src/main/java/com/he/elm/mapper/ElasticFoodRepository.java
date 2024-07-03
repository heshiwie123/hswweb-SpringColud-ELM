package com.he.elm.mapper;

import com.he.elm.entity.ElasticFood;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElasticFoodRepository extends ElasticsearchRepository<ElasticFood,String> {
    public ElasticFood findFoodByFoodName(String name);

    public List<ElasticFood> findFoodsByFoodNameIsLike(String name);
}
