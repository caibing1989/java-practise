package jvm.reference;

import java.lang.ref.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 演示不同引用
 * @Author: mtdp
 * @Date: 2020-08-07
 */

public class ReferenceType {

    private static ReferenceQueue<User> referenceQueue = new ReferenceQueue<User>();

    private static void printReferenceQueue(String str) {
        Reference<? extends User> reference = referenceQueue.poll();

        if (reference != null) {
            // 如果前面垃圾正常回收了，这块应该获取不到对象 null
            System.out.println("the gc Object reference =" + str + " = " + reference.get());
        }
    }

    private static void testSoftReference() throws InterruptedException {
        List<SoftReference<User>> softReferenceList = new ArrayList<SoftReference<User>>();

        // 初始化好100个软引用对象，及其列表
        for (int i = 0; i < 10; i++) {
            // 垃圾回收过后，才会进入referenceQueue
            SoftReference<User> softReference = new SoftReference<User>(new User("soft" + i), referenceQueue);

            // 从软引用中去获取，表示软引用成功了
            System.out.println("now the soft user :" + softReference.get());

            softReferenceList.add(softReference);
        }

        // 主动触发垃圾回收
        System.gc();

        // 等待1秒之后，再去队列中验证
        Thread.sleep(1000);

        printReferenceQueue("soft");
    }

    private static void testWeekReference() throws InterruptedException {
        List<WeakReference<User>> weakReferenceList = new ArrayList<WeakReference<User>>();

        for (int i = 0; i < 10; i++) {
            WeakReference<User> weakReference = new WeakReference<User>(new User("weak" + i), referenceQueue);

            System.out.println("now the weak user :" + weakReference.get());

            weakReferenceList.add(weakReference);
        }

        System.gc();

        Thread.sleep(1000);

        printReferenceQueue("weak");
    }

    private static void testPhantomReference() throws InterruptedException {
        List<PhantomReference<User>> phantomReferenceList = new ArrayList<PhantomReference<User>>();

        for (int i = 0; i < 10; i++) {
            PhantomReference<User> phantomReference = new PhantomReference<User>(new User("phantom" + i), referenceQueue);

            System.out.println("now the phantom user :" + phantomReference.get());

            phantomReferenceList.add(phantomReference);
        }

        System.gc();

        Thread.sleep(1000);

        printReferenceQueue("phantom");
    }

    public static void main(String[] args) throws InterruptedException {
        testSoftReference();

        testWeekReference();

        testPhantomReference();
    }
}
