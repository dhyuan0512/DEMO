package com.example.demo.mapper;

import com.example.demo.bean.WechatUser;
import com.example.demo.bean.WechatUserKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface WechatUserMapper {
    int deleteByPrimaryKey(WechatUserKey key);

    int insert(WechatUser record);

    int insertSelective(WechatUser record);

    WechatUser selectByPrimaryKey(WechatUserKey key);

    List<WechatUser> page();

    int updateByPrimaryKeySelective(WechatUser record);

    int updateByPrimaryKey(WechatUser record);

    int conunt();

    List<WechatUser> pages(@Param("bindex") int bindex, @Param("num") int num);
}