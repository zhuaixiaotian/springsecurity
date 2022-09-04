package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.domain.Menu;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface menumapper extends BaseMapper<Menu> {
    List<String> selectperm(@Param("userId") Long userId);

}
