package com.nowcoder.community.dao.elasticsearch;

import com.nowcoder.community.entity.DiscussPost;
//import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository // 此为spring提供的针对数据访问层的注解，而Mapper是mybatis专用的注解
public interface DiscussPostRepository extends ElasticsearchRepository<DiscussPost,Integer> {
}