package com.run.mapper;

import com.run.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    //批量插入用户(练习批处理)
    public int batchInsertUserInfo(@Param("user") User user);

    //批量插入用户信息
    public int foreachInsertUserInfo(List<User> users);

    //分页查询用户信息(deptId送空的请求下，查询所有用户信息)
    public List<User> selectUsers(int deptId);

    /**
     * 根据性别和年龄查询用户信息（懒加载）
     *
     * @param gender
     * @param age
     * @return
     */
    public User selectUserByGenderAndAge(int gender, int age);

    /**
     * 根据用户编号查询用户信息（左外查询）
     *
     * @param id
     * @return
     */
    public User selectUserByUserId(int id);

    /**
     * 插入用户信息
     *
     * @param user
     * @return
     */
    public int insertUser(User user);

}
