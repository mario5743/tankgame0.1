package stu.tankgame_;

import java.io.*;
import java.util.Vector;

/**
 * @author mario
 * @version 1.0
 */
public class Recorder {
    private static int enemyTankNum;
    private static String path = "src\\stu\\tankgame_\\myRecoder.txt";
    private static BufferedWriter bw = null;
    private static Vector<EnemyTank> enemyTanks = null;
    public static Vector<Node> nodes = new Vector<>();
    private static BufferedReader br = null;

    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        Recorder.enemyTanks = enemyTanks;
    }

    public static int getEnemyTankNum() {
        return enemyTankNum;
    }

    public static void setEnemyTankNum(int enemyTankNum) {
        Recorder.enemyTankNum = enemyTankNum;
    }

    public static void addEnemyTankNum() {
        enemyTankNum++;
    }

    public static Vector<Node> rdRecorder() {
        //读取文件中的信息
        File file = new File(path);
        if (file.exists()) {
            try {
                br = new BufferedReader(new FileReader(path));
                enemyTankNum = Integer.parseInt(br.readLine());
                String line = "";
                while ((line = br.readLine()) != null) {
                    String[] str = line.split(" ");
                    Node node = new Node(Integer.parseInt(str[0]), Integer.parseInt(str[1]), Integer.parseInt(str[2]));
                    nodes.add(node);
                }
                return nodes;
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            System.out.println("文件不存在，只能开启新游戏");
        }
        return null;
    }

    public static void mkRecorder() {
        try {
            bw = new BufferedWriter(new FileWriter(path));
            bw.write(enemyTankNum + "\n");
            //遍历所有坦克
            for (int i = 0; i < enemyTanks.size(); i++) {
                //记录坦克的位置信息
                EnemyTank enemyTank = enemyTanks.get(i);
                String record = enemyTank.getX() + " " + enemyTank.getY() + " " + enemyTank.getDirect();
                bw.write(record + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
