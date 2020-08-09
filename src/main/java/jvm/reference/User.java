package jvm.reference;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-08-07
 */

public class User {
    private byte[] bytes = new byte[1024 * 10];

    private String userId;

    public User(String userId) {
        this.userId = userId;
    }

    public String toString() {
        return "userId = " + userId;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("now finalize userId =" + userId);
    }
}
