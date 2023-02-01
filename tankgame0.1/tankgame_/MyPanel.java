package stu.tankgame_;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Scanner;
import java.util.Vector;

/**
 * @author mario
 * @version 1.0
 */
@SuppressWarnings({"all"})
public class MyPanel extends JPanel implements KeyListener, Runnable {
    Hero hero = null;
    Bullet bullet = null;
    //Bullet bt = null;
    Vector<EnemyTank> enemyTanks = new Vector<>();
    int enemyTankSize = 4;
    boolean loop = false;
    Vector<Bomb> bombs = new Vector<>();
    //定义三张图片
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;
    Vector<Bullet> bullets = new Vector<>();
    Vector<Node> nodes = null;


    public MyPanel(int key) {
        switch (key) {
            case 1:
                hero = new Hero(500, 500);
                Recorder.setEnemyTanks(enemyTanks);
                //hero.setSpeed(1);
                for (int i = 0; i < enemyTankSize; i++) {
                    EnemyTank enemyTank = new EnemyTank(100 * (i + 1), 0);
                    enemyTank.setDirect(2);
                    enemyTank.setEnemyTanks(enemyTanks);
                    new Thread(enemyTank).start();
                    enemyTanks.add(enemyTank);
                    Bullet bullet = new Bullet(enemyTank.getX() + 18, enemyTank.getY() + 58, enemyTank.getDirect());
                    enemyTank.bullets.add(bullet);
                    if (bullet.isLive) {
                        new Thread(bullet).start();
                    }
                }
                break;
            case 2:
                hero = new Hero(500, 500);
                Recorder.setEnemyTanks(enemyTanks);
                nodes = Recorder.rdRecorder();
                //hero.setSpeed(1);
                for (int i = 0; i < nodes.size(); i++) {
                    Node node = Recorder.nodes.get(i);
                    EnemyTank enemyTank = new EnemyTank(node.getX(), node.getY());
                    enemyTank.setDirect(node.getDirect());
                    enemyTank.setEnemyTanks(enemyTanks);
                    new Thread(enemyTank).start();
                    enemyTanks.add(enemyTank);
                    Bullet bullet = new Bullet(enemyTank.getX() + 18, enemyTank.getY() + 58, enemyTank.getDirect());
                    enemyTank.bullets.add(bullet);
                    if (bullet.isLive) {
                        new Thread(bullet).start();
                    }
                }
                break;
            default:
                System.out.println("请输入指定整数");
        }

        //初始化图片对象
        image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.gif"));
        image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.gif"));
        image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.gif"));

        //播放音乐
        new AePlayWave("src\\111.wav").start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);
        if (hero.isLive) {
            paintTank(hero.getX(), hero.getY(), g, hero.getDirect(), 0);
        }

        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            if (enemyTank.isLive) {
                paintTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 1);
                for (int j = 0; j < enemyTank.bullets.size(); j++) {
                    if (enemyTank.bullets.get(j).isLive) {
                        g.fill3DRect(enemyTank.bullets.get(j).getX(), enemyTank.bullets.get(j).getY(), 4, 4, false);
                    } else {
                        enemyTank.bullets.remove(j);
                    }
                }
            }
        }

        for (int i = 0; i < bombs.size(); i++) {
            Bomb bomb = bombs.get(i);
            if (bomb.life > 6) {
                g.drawImage(image1, bomb.x, bomb.y, 60, 60, this);
            } else if (bomb.life > 3) {
                g.drawImage(image2, bomb.x, bomb.y, 60, 60, this);
            } else {
                g.drawImage(image3, bomb.x, bomb.y, 60, 60, this);
            }
            //让炸弹生命值减少
            bomb.lifeDown();
            if (bomb.life == 0) {
                bombs.remove(bomb);
            }
        }
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bt = bullets.get(i);
            if (bt != null && bt.isLive == true) {
                g.fill3DRect(bt.getX(), bt.getY(), 4, 4, false);
            } else {
                bullets.remove(bt);
            }
        }
        paintRecord(g);
    }

    //画出记录面板方法
    public void paintRecord(Graphics g) {
        g.setColor(Color.black);
        Font font = new Font("宋体", Font.BOLD, 25);
        g.setFont(font);
        g.drawString("累计击毁敌方坦克", 1020, 30);
        paintTank(1020, 50, g, 0, 1);
        g.setColor(Color.black);
        g.drawString(Recorder.getEnemyTankNum() + "", 1080, 90);
    }

    public void paintTank(int x, int y, Graphics g, int direct, int type) {
        switch (type) {
            case 0:
                g.setColor(Color.cyan);
                break;
            case 1:
                g.setColor(Color.yellow);
                break;
        }
        switch (direct) {
            case 0:
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 30, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y + 30, x + 20, y);
                break;
            case 1:
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 30, y + 20, x + 60, y + 20);
                break;
            case 2:
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 30, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y + 30, x + 20, y + 60);
                break;
            case 3:
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 30, y + 20, x, y + 20);
                break;
            default:
                System.out.println("暂时没有处理");
        }

    }

    public void hitTank(Bullet b, Tank tank) {
        switch (tank.getDirect()) {
            case 0:
            case 2:
                if (b.getX() >= tank.getX() && b.getX() <= tank.getX() + 40
                        && b.getY() >= tank.getY() && b.getY() <= tank.getY() + 60) {
                    b.isLive = false;
                    tank.isLive = false;
                    //击中后移除坦克
                    enemyTanks.remove(tank);
                    //记录击毁数量增加
                    if (tank instanceof EnemyTank) {
                        Recorder.addEnemyTankNum();
                    }
                    //创建bomb对象
                    Bomb bomb = new Bomb(tank.getX(), tank.getY());
                    bombs.add(bomb);
                }
                break;
            case 1:
            case 3:
                if (b.getX() >= tank.getX() && b.getX() <= tank.getX() + 40
                        && b.getY() >= tank.getY() && b.getY() <= tank.getY() + 60) {
                    b.isLive = false;
                    tank.isLive = false;
                    //击中后移除坦克
                    enemyTanks.remove(tank);
                    //记录击毁数量增加
                    if (tank instanceof EnemyTank) {
                        Recorder.addEnemyTankNum();
                    }
                    Bomb bomb = new Bomb(tank.getX(), tank.getY());
                    bombs.add(bomb);
                }
                break;
        }
    }

    public void hitEnemyTank() {
        for (int j = 0; j < bullets.size(); j++) {
            Bullet bt = bullets.get(j);
            //判断是否击中了敌人坦克
            if (bt != null && bt.isLive) {//当我的子弹还存活

                //遍历敌人所有的坦克
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    hitTank(bt, enemyTank);
                }

            }
        }
    }

    public void hitHero() {
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            for (int j = 0; j < enemyTank.bullets.size(); j++) {
                Bullet bullet = enemyTank.bullets.get(j);
                if (hero.isLive && bullet.isLive){
                    hitTank(bullet, hero);
                }
            }

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        if (e.getKeyCode() == KeyEvent.VK_W) {
            hero.setDirect(0);
            if (hero.getY() > 0) {
                hero.moveUp();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            hero.setDirect(1);
            if (hero.getX() + 60 < 1000) {
                hero.moveRight();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            hero.setDirect(2);
            if (hero.getY() + 60 < 750) {
                hero.moveDown();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            hero.setDirect(3);
            if (hero.getX() > 0) {
                hero.moveLeft();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_J) {
            System.out.println("按下J键");
//            if (bullet == null || !bullet.isLive) {//等待子弹销毁才能下一次
                if (bullets.size() < 5) {
                    bullet = hero.shot();
                    bullets.add(bullet);
                    new Thread(bullet).start();
                }
//            }
        }
        //重绘
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            hitEnemyTank();
            hitHero();
            this.repaint();
        }
    }
}
