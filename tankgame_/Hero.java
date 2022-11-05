package stu.tankgame_;

import java.util.Vector;

/**
 * @author mario
 * @version 1.0
 */
public class Hero extends Tank{
    Bullet bullet;

    public Hero(int x, int y) {
        super(x, y);
    }

    public Bullet shot() {
        if (this.getDirect() == 0) {
            return new Bullet(getX() + 18, getY() - 2, getDirect());
        }else if (this.getDirect() == 1) {
            return new Bullet(getX() + 58, getY() + 18, getDirect());
        }else if (this.getDirect() == 2) {
            return new Bullet(getX() + 18, getY() + 58, getDirect());
        }else if (this.getDirect() == 3) {
            return new Bullet(getX() - 2, getY() + 18, getDirect());
        }
        return null;
    }
}
