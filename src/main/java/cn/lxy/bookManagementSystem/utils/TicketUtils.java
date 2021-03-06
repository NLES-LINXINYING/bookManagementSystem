package cn.lxy.bookManagementSystem.utils;

import cn.lxy.bookManagementSystem.model.Ticket;
import org.joda.time.DateTime;

public class TicketUtils {

    public static Ticket next(int uid) {
        Ticket ticket = new Ticket();
        ticket.setTicket(UuidUtils.next());
        ticket.setUserId(uid);
        // 设置t票过期时间
        DateTime expiredTime = new DateTime();
        expiredTime = expiredTime.plusMonths(3);
        ticket.setExpireAt(expiredTime.toDate());

        return ticket;
    }
}
