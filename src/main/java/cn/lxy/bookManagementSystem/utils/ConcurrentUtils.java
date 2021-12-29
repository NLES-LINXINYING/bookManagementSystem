package cn.lxy.bookManagementSystem.utils;

import cn.lxy.bookManagementSystem.model.User;

/**
 * 用来保存当前访问者的容器
 */
public class ConcurrentUtils {

    private static ThreadLocal<User> host = new ThreadLocal<>();

    public static User getHost() {
        return host.get();
    }

    public static void setHost(User user) {
        host.set(user);
    }
}
