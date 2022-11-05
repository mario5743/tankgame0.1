package stu.tankgame_;

import java.util.Vector;

/**
 * @author mario
 * @version 1.0
 */
public class Bullet implements Runnable {
    private int x;
    private int y;
    private int direct;
    private int speed = 4;
    boolean loop = true;
    boolean isLive = true;

    public Bullet(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    @Override
    public void run() {
        while (loop) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            switch (direct) {
                case 0:
                    y -= speed;
                    break;
                case 1:
                    x += speed;
                    break;
                case 2:
                    y += speed;
                    break;
                case 3:
                    x -= speed;
                    break;
            }
            if (!((x >= 0 && x <= 1000) && (y >= 0 && y <= 750))) {
                loop = false;
                isLive = false;
                System.out.println("子弹销毁");
                break;
            }
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
