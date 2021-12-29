package cn.lxy.bookManagementSystem.service;

import cn.lxy.bookManagementSystem.model.User;
import cn.lxy.bookManagementSystem.utils.ConcurrentUtils;
import org.springframework.stereotype.Service;

@Service
public class HostHolder {

    public User getUser() {
        return ConcurrentUtils.getHost();
    }

    public void setUser(User user) {
        ConcurrentUtils.setHost(user);
    }
}
