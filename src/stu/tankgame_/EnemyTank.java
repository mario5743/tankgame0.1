package stu.tankgame_;

import java.util.Vector;

/**
 * @author mario
 * @version 1.0
 */
public class EnemyTank extends Tank implements Runnable {
    Vector<Bullet> bullets = new Vector<>();

    public EnemyTank(int x, int y) {
        super(x, y);
    }

    @Override
    public void run() {
        //实现移动功能
        while (true) {
            if (bullets.size() == 0 && isLive) {
                Bullet bt = null;
                switch (getDirect()) {
                    case 0:
                        bt = new Bullet(getX() + 20, getY(), getDirect());
                        break;
                    case 1:
                        bt = new Bullet(getX() + 60, getY() + 20, getDirect());
                        break;
                    case 2:
                        bt = new Bullet(getX() + 20, getY() + 60, getDirect());
                        break;
                    case 3:
                        bt = new Bullet(getX(), getY() + 20, getDirect());
                        break;
                }
                bullets.add(bt);
                new Thread(bt).start();
            }
            switch (getDirect()) {
                case 0:
                    for (int i = 0; i < 30; i++) {
                        if (getY() > 0) {
                            moveUp();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 1:
                    for (int i = 0; i < 30; i++) {
                        if (getX() + 60 < 1000) {
                            moveRight();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i < 30; i++) {
                        if (getY() + 60 < 750) {
                            moveDown();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < 30; i++) {
                        if (getX() > 0) {
                            moveLeft();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }
            //实现随机转向功能
            setDirect((int) (Math.random() * 4));
            if (!isLive) {
                break;
            }
        }
    }
}
