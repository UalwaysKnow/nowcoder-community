package com.nowcoder.community.dao;

import com.nowcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {

    // 分行，一页能放多少字
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);

    // Param用来取别名，如果这个方法只有一个参数并且在<if>中使用，那么必须加别名
    int selectDiscussPostRows(@Param("userId")int userId);

}
