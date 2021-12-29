package cn.lxy.bookManagementSystem.model;

import java.util.Date;

public class Ticket {

    private int id;

    /**
     * 相绑定的userId
     */
    private int userId;

    /**
     * t票实体
     */
    private String ticket;

    /**
     * 过期时间
     */
    private Date expireAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Date getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(Date expireAt) {
        this.expireAt = expireAt;
    }
}
