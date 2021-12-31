package cn.lxy.bookManagementSystem.service;

import cn.lxy.bookManagementSystem.dao.TicketDAO;
import cn.lxy.bookManagementSystem.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    @Autowired
    TicketDAO ticketDAO;

    public Ticket getTicket(int userId) {
        try {
            return ticketDAO.selectTicketByUserId(userId);
        } catch (Exception e) {
//            logger.error(e);
            return null;
        }
    }

    public Ticket getTicket(String t) {
        try {
            return ticketDAO.selectTicketByTicket(t);
        } catch (Exception e) {
//            logger.error(e);
            return null;
        }
    }

    public int addTicket(Ticket t) {
        try {
            return ticketDAO.addTicket(t);
        } catch (Exception e) {
//            logger.error(e);
            return 0;
        }
    }

    public int deleteTicket(int id) {
        try {
            return ticketDAO.removeTicketById(id);
        } catch (Exception e) {
//            logger.error(e);
            return 0;
        }
    }

    public int deleteTicket(String ticket) {
        try {
            return ticketDAO.removeTicketByTicket(ticket);
        } catch (Exception e) {
//            logger.error(e);
            return 0;
        }
    }
}
