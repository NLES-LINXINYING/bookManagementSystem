package cn.lxy.bookManagementSystem.dao;

import cn.lxy.bookManagementSystem.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDAO {

    String table_name = " user ";
    String insert_field = " name, email, password ";

    @Select({"select * from", table_name, "where email=#{email}"})
    User selectUserByEmail(String email);

    @Select({"select * from", table_name, "where id=#{id}"})
    User selectUserById(int id);

    @Insert({"insert into", table_name, "(", insert_field, ") values (#{name}, #{email}, #{password})"})
    int addUser(User user);
}
