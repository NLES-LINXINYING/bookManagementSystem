package cn.lxy.bookManagementSystem.dao;

import cn.lxy.bookManagementSystem.model.Book;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookDAO {

    String table_name = " book ";
    String insert_field = "name, author, price";
    String select_field = "id, status, " + insert_field;

    @Insert({"insert into", table_name, "(", insert_field, ") values (#{name},#{author},#{price})"})
    int addBook(Book book);

    @Select({"select", select_field, "from", table_name})
    List<Book> selectAll();

    @Select({"select", select_field, "from", table_name, " where id=#{id}"})
    Book selectBookById(int id);

    @Select({"select", select_field, "from", table_name, " where name=#{name}"})
    Book selectBookByName(String name);
}
