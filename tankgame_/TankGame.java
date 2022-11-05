package stu.tankgame_;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

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
        System.out.println("请输入指令1.新游戏 2.继续游戏");
        Scanner myScanner = new Scanner(System.in);
        int key = myScanner.nextInt();
        mp = new MyPanel(key);
        this.add(mp);//把面板(就是游戏的绘图区域)
        new Thread(mp).start();
        this.setSize(1300, 750);
        this.addKeyListener(mp);//让JFrame 监听mp的键盘事件
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Recorder.mkRecorder();
                System.exit(0);
            }
        });
    }
}
