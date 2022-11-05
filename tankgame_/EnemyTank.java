package stu.tankgame_;

import java.util.Vector;

/**
 * @author mario
 * @version 1.0
 */
public class EnemyTank extends Tank implements Runnable {
    Vector<Bullet> bullets = new Vector<>();
    Vector<EnemyTank> enemyTanks = new Vector<>();

    public EnemyTank(int x, int y) {
        super(x, y);
    }

    public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }

    public Vector<EnemyTank> getEnemyTanks() {
        return enemyTanks;
    }

    public boolean isTouch() {
        //判断自身的方向
        switch (this.getDirect()) {
            case 0:
                //遍历所有的敌人坦克
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank != this) {
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            //自身坦克左定位点进入敌人坦克 (x,y)
                            if (this.getX() >= enemyTank.getX() && this.getX() <= enemyTank.getX() + 40
                                && this.getY() >= enemyTank.getY() && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                            //自身坦克右定位点进入敌人坦克 (x + 40 ,y)
                            if (this.getX() + 40 >= enemyTank.getX() && this.getX() + 40 <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY() && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                        }
                        //自身向上，敌人向左或者向右
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            //自身坦克左定位点进入敌人坦克 (x,y)
                            if (this.getX() >= enemyTank.getX() && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY() && this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }
                            //自身坦克右定位点进入敌人坦克 (x + 40 ,y)
                            if (this.getX() + 40 >= enemyTank.getX() && this.getX() + 40 <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY() && this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 1:
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank != this) {
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            //自身坦克上定位点进入敌人坦克 (x + 60 ,y)
                            if (this.getX() + 60 >= enemyTank.getX() && this.getX() + 60 <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY() && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                            //自身坦克下定位点进入敌人坦克 (x + 60,y + 40)
                            if (this.getX() +60 >= enemyTank.getX() && this.getX() + 60 <= enemyTank.getX() + 40
                                    && this.getY() + 40 >= enemyTank.getY() && this.getY() +40 <= enemyTank.getY() + 60) {
                                return true;
                            }
                        }
                        //自身向上，敌人向左或者向右
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            //自身坦克上定位点进入敌人坦克 (x + 60 ,y)
                            if (this.getX() + 60  >= enemyTank.getX() && this.getX() + 60 <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY() && this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }
                            //自身坦克下定位点进入敌人坦克 (x +60 ,y + 40)
                            if (this.getX() + 60 >= enemyTank.getX() && this.getX() + 60 <= enemyTank.getX() + 60
                                    && this.getY() + 40 >= enemyTank.getY() && this.getY() + 40 <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 2:
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank != this) {
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            //自身坦克左定位点进入敌人坦克 (x,y + 60)
                            if (this.getX() >= enemyTank.getX() && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() + 60 >= enemyTank.getY() && this.getY() + 60 <= enemyTank.getY() + 60) {
                                return true;
                            }
                            //自身坦克右定位点进入敌人坦克 (x + 40 ,y + 60)
                            if (this.getX() + 40 >= enemyTank.getX() && this.getX() + 40 <= enemyTank.getX() + 40
                                    && this.getY() + 60 >= enemyTank.getY() && this.getY() + 60 <= enemyTank.getY() + 60) {
                                return true;
                            }
                        }
                        //自身向上，敌人向左或者向右
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            //自身坦克左定位点进入敌人坦克 (x,y + 60)
                            if (this.getX() >= enemyTank.getX() && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() + 60 >= enemyTank.getY() && this.getY() + 60 <= enemyTank.getY() + 40) {
                                return true;
                            }
                            //自身坦克右定位点进入敌人坦克 (x + 40 ,y + 60)
                            if (this.getX() + 40 >= enemyTank.getX() && this.getX() + 40 <= enemyTank.getX() + 60
                                    && this.getY() + 60 >= enemyTank.getY() && this.getY() + 60 <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 3:
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank != this) {
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            //自身坦克上定位点进入敌人坦克 (x,y)
                            if (this.getX() >= enemyTank.getX() && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY() && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                            //自身坦克下定位点进入敌人坦克 (x ,y + 40)
                            if (this.getX() >= enemyTank.getX() && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() + 40 >= enemyTank.getY() && this.getY() + 40 <= enemyTank.getY() + 60) {
                                return true;
                            }
                        }
                        //自身向上，敌人向左或者向右
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            //自身坦克左定位点进入敌人坦克 (x,y)
                            if (this.getX() >= enemyTank.getX() && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY() && this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }
                            //自身坦克右定位点进入敌人坦克 (x ,y +40)
                            if (this.getX() >= enemyTank.getX() && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() + 40 >= enemyTank.getY() && this.getY() + 40 <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
        }
        return false;
    }

    @Override
    public void run() {
        //实现移动功能
        while (true) {
            if (bullets.size() == 0 && isLive) {
                //敌人坦克发射子弹
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
                //控制敌人坦克移动范围在面板上
                /**
                 * i 坦克单次移动次数
                 * */
                case 0:
                    for (int i = 0; i < 30; i++) {
                        if (getY() > 0 && !isTouch()) {
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
                        if (getX() + 60 < 1000 && !isTouch()) {
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
                        if (getY() + 60 < 750 && !isTouch()) {
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
                        if (getX() > 0 && !isTouch()) {
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
