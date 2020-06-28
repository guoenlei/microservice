package com.aldwx.app.modules.demo.dao.stat;

import com.aldwx.app.modules.demo.bean.Demo;
import com.aldwx.app.modules.demo.entity.DemoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author
 * @description
 * @createTime
 **/
@Mapper
public interface DemoStatDao {


    /**
     * 机型 品牌
     * @param demo
     * @return
     */
    @Results(
            value = {
                    @Result(property = "uname", column = "uname"),
                    @Result(property = "name", column = "name"),
                    @Result(property = "brand", column = "brand"),
                    @Result(property = "update_at", column = "update_at"),

            })
    @Select("select uname, name, brand, update_at from ${tableName} grlimit ${limitNum}")
    List<DemoEntity> queryList(Demo demo);




}
