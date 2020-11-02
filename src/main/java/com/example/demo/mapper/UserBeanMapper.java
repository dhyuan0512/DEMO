package com.example.demo.mapper;

import com.example.demo.bean.UserBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface UserBeanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserBean record);

    int insertSelective(UserBean record);

    UserBean selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserBean record);

    int updateByPrimaryKey(UserBean record);

    List<UserBean> page();

    UserBean getInfo(String name, String password);

    int insertInfo(String name, String password);

    List<UserBean> selectInfo(String name);

    int conuntUser();

    List<UserBean> pageUsers(@Param("bindex") int bindex, @Param("num") int num);
}