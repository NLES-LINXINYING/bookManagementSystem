package cn.lxy.bookManagementSystem.biz;

import cn.lxy.bookManagementSystem.model.Ticket;
import cn.lxy.bookManagementSystem.model.User;
import cn.lxy.bookManagementSystem.model.exceptions.LoginRegisterException;
import cn.lxy.bookManagementSystem.service.TicketService;
import cn.lxy.bookManagementSystem.service.UserService;
import cn.lxy.bookManagementSystem.utils.ConcurrentUtils;
import cn.lxy.bookManagementSystem.utils.MD5;
import cn.lxy.bookManagementSystem.utils.TicketUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LoginBiz {

    @Autowired
    private UserService userService;

    @Autowired
    private TicketService ticketService;

    /**
     * 登录逻辑，先检查邮箱和密码，然后更新ticket
     * @return 返回最新ticket
     * @throws Exception 账号密码错误
     */
    public String login(String email, String password) throws Exception {
        User user = userService.getUser(email);

        // 登录信息检查
        if (user == null) {
            throw new LoginRegisterException("邮箱不存在");
        }
        if (!StringUtils.equals(MD5.next(password), user.getPassword())) {
            throw new LoginRegisterException("密码不正确");
        }

        // 检查ticket
        Ticket t = ticketService.getTicket(user.getId());
        if (t == null) {
            t = TicketUtils.next(user.getId());
            ticketService.addTicket(t);
            return t.getTicket();
        }
        // 是否过期
        if (t.getExpireAt().before(new Date())) {
            // 删除
            ticketService.deleteTicket(t.getId());
        }

        t = TicketUtils.next(user.getId());
        ticketService.addTicket(t);

        ConcurrentUtils.setHost(user);
        return t.getTicket();
    }

    /**
     * 用户退出登录，只需要删除数据库中用户的ticket
     */
    public void logout(String t) {
        ticketService.deleteTicket(t);
    }

    /**
     * 注册一个用户，并返回用户ticket
     */
    public String register(User user) {
        // 信息检查
        if (userService.getUser(user.getEmail()) != null) {
            throw new LoginRegisterException("用户邮箱已经存在");
        }

        // 密码加密
        String plain = user.getPassword();
        String md5 = MD5.next(plain);
        user.setPassword(md5);
        // 数据库添加用户
        userService.addUser(user);

        // 生成用户ticket
        Ticket ticket = TicketUtils.next(user.getId());
        // 数据库添加ticket
        ticketService.addTicket(ticket);

        ConcurrentUtils.setHost(user);
        return ticket.getTicket();
    }
}
