package cn.lxy.bookManagementSystem.dao;

import cn.lxy.bookManagementSystem.model.Ticket;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TicketDAO {

    String table_name = " ticket ";

    String insert_field = "user_id, ticket, expire_at";

    @Select({"select * from", table_name, "where user_id=#{userId}"})
    Ticket selectTicketByUserId(int userId);

    @Select({"select * from", table_name, "where ticket=#{ticket}"})
    Ticket selectTicketByTicket(String t);

    @Insert({"insert into", table_name, "(", insert_field, ") values(#UserId, #ticket, #expireAt})"})
    int addTicket(Ticket t);

    @Delete({"delete from", table_name, "where id=#{id}"})
    int removeTicketById(int id);

    @Delete({"delete from", table_name, "where ticket=#{ticket}"})
    int removeTicketByTicket(String ticket);
}
