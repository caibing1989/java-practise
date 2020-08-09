package threadlocal;

/**
 * @Description: 避免传递参数的麻烦
 * @Author: mtdp
 * @Date: 2020-07-27
 */

public class ThreadLocalGlobalUsage {
    public static void main(String[] args) {
        new Service1().process();
    }
}

class Service1 {
    public void process() {
        User user = new User("超哥");
        UserContextHolder.holder.set(user);

        new Service2().process();
    }
}

class Service2 {
    public void process() {
        User user = UserContextHolder.holder.get();
        System.out.println(user.getName());
    }
}

class UserContextHolder {
    public static ThreadLocal<User> holder = new ThreadLocal<>();
}

class User {
    String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
