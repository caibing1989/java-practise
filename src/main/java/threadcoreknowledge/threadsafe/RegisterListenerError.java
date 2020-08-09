package threadcoreknowledge.threadsafe;

/**
 * @Description: 观察者模式隐藏的线程安全问题，可以通过工厂模式来解决
 * @Author: mtdp
 * @Date: 2020-07-19
 */

public class RegisterListenerError {
    private int count;

    private RegisterListenerError(MySource mySource) {
        mySource.registerListener(new EventListener() {
            @Override
            public void onEvent(Event e) {
                System.out.println("我得到了数字:" + count);
            }
        });

        for (int i = 0; i < 100000; i++) {
            System.out.print(i);
        }

        count = 100;
    }

    static class MySource {
        private EventListener listener;

        void registerListener(EventListener listener) {
            this.listener = listener;
        }

        void eventCome(Event e) {
            if (listener != null) {
                listener.onEvent(e);
            } else {
                System.out.println("还未初始化完毕");
            }
        }
    }

    interface EventListener {
        void onEvent(Event e);
    }

    interface Event {
    }

    public static void main(String[] args) {
        MySource mySource = new MySource();
        new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mySource.eventCome(new Event() {
            });
        }).start();
        RegisterListenerError registerListenerError = new RegisterListenerError(mySource);
    }
}
