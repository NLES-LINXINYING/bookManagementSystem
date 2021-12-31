package cn.lxy.bookManagementSystem.interceptor;

import cn.lxy.bookManagementSystem.model.Ticket;
import cn.lxy.bookManagementSystem.model.User;
import cn.lxy.bookManagementSystem.service.TicketService;
import cn.lxy.bookManagementSystem.service.UserService;
import cn.lxy.bookManagementSystem.utils.ConcurrentUtils;
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
public class HostInfoInterceptor implements HandlerInterceptor {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    /**
     * 未注入host信息
     */
    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {

        logger.info(HostInfoInterceptor.class.getSimpleName() + " say hello...");

        String t = CookieUtils.getCookie("t", request);
        if (!StringUtils.isEmpty(t)) {
            Ticket ticket = ticketService.getTicket(t);
            if (ticket != null && ticket.getExpireAt().after(new Date())) {
                User host = userService.getUser(ticket.getUserId());
                ConcurrentUtils.setHost(host);
            }
        }
        return true;
    }
}
