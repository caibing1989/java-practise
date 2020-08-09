package deadlock;

/**
 * @Description: 演示活锁
 * @Author: mtdp
 * @Date: 2020-07-26
 */

public class LiveLock {
    static class Spoon {
        private Diner owner;

        public Spoon(Diner owner) {
            this.owner = owner;
        }

        public void setOwner(Diner owner) {
            this.owner = owner;
        }

        public synchronized void user() {
            System.out.printf("%s has eaten!", owner.getName());
        }
    }

    static class Diner {
        private String name;
        private boolean isHungry;

        public Diner(String name) {
            this.name = name;
            this.isHungry = true;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setHungry(boolean hungry) {
            isHungry = hungry;
        }

        public String getName() {
            return name;
        }

        public boolean isHungry() {
            return isHungry;
        }

        public void eatWith(Spoon spoon, Diner spouse) {
            // 先看自己饿不饿
            while (isHungry) {
                // 如果饿，再看勺子是不是自己的

                // 如果勺子不是自己的，就等待
                if (spoon.owner != this) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                }

                // 如果勺子是自己的，再看对方饿不饿
                if (spouse.isHungry) {
                    System.out.println(name + ": 亲爱的，你先吃吧");

                    // 如果对方饿，就将勺子给对方
                    spoon.setOwner(spouse);
                    continue;
                }

                // 如果对方不饿，自己就开始吃饭
                spoon.user();
                isHungry = false;

                // 吃完饭之后，将勺子给对方
                spoon.setOwner(spouse);
            }
        }
    }

    public static void main(String[] args) {
        Diner husband = new Diner("牛郎");
        Diner wife = new Diner("织女");

        Spoon spoon = new Spoon(husband);

        new Thread(() -> {
            husband.eatWith(spoon, wife);
        }).start();

        new Thread(() -> {
            wife.eatWith(spoon, husband);
        }).start();
    }

}
