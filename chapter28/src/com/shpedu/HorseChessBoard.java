package com.shpedu;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class HorseChessBoard {
    private static int X = 6; //表示col
    private static int Y = 6; //表示row
    private static int[][] chessBoard = new int[Y][X]; //棋盘
    private static boolean[] visited = new boolean[X * Y]; //记录某个位置是否走过
    private static boolean finished = false; //记录马儿是否遍历完棋盘

    public static void main(String[] args) {
        int row = 5;
        int col = 5;

        //test
        long start = System.currentTimeMillis();
        traversalChessBoard(chessBoard, row - 1, col -1, 1);
        long end = System.currentTimeMillis();
        System.out.println("遍历耗时=" + (end - start));
        for(int[] rows : chessBoard) {
            for (int step : rows) { //step表示该位置是马尔应该走的第几步
                System.out.print(step + "\t");
            }
            System.out.println();
        }
    }

    //对ps的各个位置，可以走的下一个位置的次数进行排序，把可能走的下一个位置从小到大排序
    public static void sort(ArrayList<Point> ps) {
        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return next(o1).size() - next(o2).size();
            }
        });
    }

    //遍历棋盘，如果遍历成功，就把finished 设置为ture
    //且马儿走的每一步step，记录到chessBoard
    public static void traversalChessBoard(int[][] chessBoard, int row, int col, int step) {
        chessBoard[row][col] = step;
        visited[row * X + col] = true;//把这个位置设置为已访问
        ArrayList<Point> ps = next(new Point(col, row));//col - X, row - Y
        sort(ps);//排序
        while (!ps.isEmpty()) {
            Point p = ps.remove(0);
            if(!visited[p.y * X + p.x]) {
                traversalChessBoard(chessBoard, p.y, p.x, step + 1);
            }
        }
        if(step < X * Y && !finished) {
            //重置
            chessBoard[row][col] = 0;
            visited[row * X + col] = false;
        } else {
            finished = true;
        }
    }

    //该方法可以获取当前位置，可以走的下一步的所以位置(Point表示 x,y)
    public static ArrayList<Point> next(Point curPoint) {

        ArrayList<Point> ps = new ArrayList<>();

        Point p1 = new Point();
        //5
        if((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        //6
        if((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        //7
        if((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        //0
        if((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        //1
        if((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        //2
        if((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        //3
        if((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        //4
        if((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        return ps;
    }
}
