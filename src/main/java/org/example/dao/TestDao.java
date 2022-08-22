package org.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.domain.Test;

import java.util.List;
//@Mapper
public interface TestDao extends BaseMapper<Test> {
    @Select("select * from test")
    List<Test> selectAll();
}
