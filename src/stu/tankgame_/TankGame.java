package stu.tankgame_;

import javax.swing.*;

/**
 * @author mario
 * @version 1.0
 */
@SuppressWarnings({"all"})
public class TankGame extends JFrame {
    public static void main(String[] args) {
        TankGame tankGame01 = new TankGame();
    }
    private MyPanel mp = null;
    public TankGame() {
        mp = new MyPanel();
        this.add(mp);//把面板(就是游戏的绘图区域)
        new Thread(mp).start();
        this.setSize(1000, 750);
        this.addKeyListener(mp);//让JFrame 监听mp的键盘事件
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
