package com.maybe.ssm.mapper;

import com.maybe.ssm.entity.SsmUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SsmUserMapper {

    /**
     * 查找全部
     *
     * @return 结果列表
     */
    @Select("select * from ssm_user")
    List<SsmUser> findAll();

    /**
     * 根据姓名模糊查询
     *
     * @param name 姓名
     * @return 模糊查询结果
     */
    @Select("select * from ssm_user where name like #{name}")
    List<SsmUser> findLikeName(String name);

    /**
     * 根据ID查找一个
     *
     * @param id 主键
     * @return 一条记录
     */
    @Select("select * from ssm_user where id = #{id}")
    SsmUser findById(int id);

    /**
     * 新增一个
     *
     * @param ssmUser 新增的对象
     */
    /*
     * 不使用@Options则返回成功条数
     * 使用@Options可以返回save记录的主键,有三个属性需要注意
     * useGeneratedKeys：是否要开启自增长主键key
     * keyColumn：对应的DB中的名称
     * keyProperty：对应的entity中的属性名称
     * 返回的主键不用使用返回值，直接返回到传入的entity中.
     */
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    @Insert("insert into ssm_user(name,age,birthday) values(#{name},#{age},#{birthday});")
    void saveOne(SsmUser ssmUser);

    /**
     * 根据主键修改内容
     *
     * @param ssmUser
     * @return
     */
    @Update("update ssm_user set name = #{name},age = #{age},birthday = #{birthday} where id = #{id}")
    int updateById(SsmUser ssmUser);

    /**
     * 根据ID删除一条记录
     *
     * @param id 主键
     * @return 成功返回删除的条数
     */
    @Delete("delete from ssm_user where id = #{id}")
    int deleteById(int id);


}
