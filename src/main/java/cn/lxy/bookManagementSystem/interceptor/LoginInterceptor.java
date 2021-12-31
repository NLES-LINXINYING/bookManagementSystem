package cn.lxy.bookManagementSystem.interceptor;

import cn.lxy.bookManagementSystem.model.Ticket;
import cn.lxy.bookManagementSystem.service.TicketService;
import cn.lxy.bookManagementSystem.utils.CookieUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private TicketService ticketService;

    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        logger.info("this is a good thing");

        // 没有t票，去登录
        String t = CookieUtils.getCookie("t", request);
        if (StringUtils.isEmpty(t)) {
            response.sendRedirect("/users/login");
            return false;
        }

        // 无效t票，去登录
        Ticket ticket = ticketService.getTicket(t);
        if (ticket == null) {
            response.sendRedirect("/users/login");
            return false;
        }

        // 过期t票，去登录
        if (ticket.getExpireAt().before(new Date())) {
            response.sendRedirect("/users/login");
            return false;
        }

        return true;
    }
}
