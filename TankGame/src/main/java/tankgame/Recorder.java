package tankgame;

import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import tankgame.Enemytank;


/*
 * 该类用于记录相关信息的。和文件交互
 */

public class Recorder {
	// 定义变量，记录我方击毁敌人坦克数
	private static int allEnemyTankNum = 0;
	// 定义IO对象，准备写数据到文件中
	private static BufferedWriter bw = null;
	private static BufferedReader br = null;
	private static String recordFile = "e:\\myRecord.txt";
	//private static String recordFile = "src\\myRecord.txt";
	//定义Vector，指向MyPanel对象的敌人坦克Vector
	private static Vector<Enemytank> enemytanks = null;
	//定义一个Node的Vector，用于保存敌人的信息node
	private static Vector<Node> nodes = new Vector<>();
	
	public static void setEnemytanks(Vector<Enemytank> enemytanks) {
		Recorder.enemytanks = enemytanks;
	}
	
	//增加一个方法，用于读取recordFile，恢复相关信息
	public static Vector<Node> getNodesAndEnemyTankRec() {
		try {
			br = new BufferedReader(new FileReader(recordFile));
			allEnemyTankNum = Integer.parseInt(br.readLine());
			String line = "";
			while((line = br.readLine()) != null) {
				String[] xyd = line.split(" ");
				Node node = new Node(Integer.parseInt(xyd[0]), 
						Integer.parseInt(xyd[1]), 
						Integer.parseInt(xyd[2]));
				nodes.add(node);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return nodes;
	}
	// 当游戏退出时，我们将allEnemyTankNum保存到recordFile
	public static void keepRecord() {
		try {
			bw = new BufferedWriter(new FileWriter(recordFile));
			bw.write(allEnemyTankNum + "\r\n");
			//遍历敌人坦克的Vector，然后根据情况保存即可
			//OOP，定义一个属性，然后通过setXxx得到敌人坦克的Vector
			for(int i = 0; i < enemytanks.size(); i++) {
				//取出敌人坦克
				Enemytank enemytank = enemytanks.get(i);
				if(enemytank.isLive) {
					String record = enemytank.getX() + " " + enemytank.getY() + " " + enemytank.getDirect();
					bw.write(record + "\r\n");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null) {
					bw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static BufferedWriter BufferedWriter(FileWriter fileWriter) {
		// TODO Auto-generated method stub
		return null;
	}

	public static int getAllEnemyTankNum() {
		return allEnemyTankNum;
	}

	public static void setAllEnemyTankNum(int allEnemyTankNum) {
		Recorder.allEnemyTankNum = allEnemyTankNum;
	}

	// 当我方坦克击毁一个敌人坦克，就应当allEnemyTankNum++
	public static void addAllEnemyTankNum() {
		Recorder.allEnemyTankNum++;
	}

}
