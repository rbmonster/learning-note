package com.learning.algorithm;

import javafx.util.Pair;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: Demo
 * @Author: sanwu
 * @Date: 2020/8/2 15:25
 */
public class Demo {

    public static void main(String[] args) {
        Demo main = new Demo();
//        System.out.println(main.matrixReshape(matrix, 2,4));

        StringBuilder sb = new StringBuilder();
        sb.toString().replace(" ","");
//        sb.toString().substring(0,1)
        int [] candi = new int[]{2,3,6,7};
//        System.out.println(main.combinationSum3(3,7));
//        System.out.println(main.isBipartite(new int[][]
//                {{2,4},{2,3,4},{0,1},{1},{0,1},{7},{9},{5},{},{6},{12,14},{},{10},{},{10},{19},{18},{},{16},{15},{23},{23},{},{20,21},{},{},{27},{26},{},{},{34},{33,34},{},{31},{30,31},{38,39},{37,38,39},{36},{35,36},{35,36},{43},{},{},{40},{},{49},{47,48,49},{46,48,49},{46,47,49},{45,46,47,48}}));

        int[][] matrix = {
                {1,2,2,3,5},
                {3,2,3,4,4},
                {2,4,5,3,1},
                {6,7,1,4,5},
                {5,1,1,2,4}
        };
        int[][] xx = {{0,1,0,0,1,1,0},{1,0,0,0,0,0,0},{1,0,0,1,1,1,1},{0,1,0,0,0,0,0},{1,0,0,0,0,0,1},{1,0,0,1,0,0,0},{1,0,1,0,0,1,0}};
        System.out.println(main.shortestPathBinaryMatrix(xx));
    }




    int[][] footprints = new int[][]{{1,0},{-1,0},{0,1},{0,-1},{1,1},{1,-1},{-1,1},{-1,-1}};
    public int shortestPathBinaryMatrix(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        if(grid[0][0] ==1 || grid[m-1][n-1] == 1) return -1;
        dfs(grid,0,0,1);
        return result==Integer.MAX_VALUE? -1:result;
    }

    int result = Integer.MAX_VALUE;
    public void dfs(int[][] grid, int x, int y, int step) {
        int m = grid.length;
        int n = grid[0].length;
        if(x<0|| x>=m||y<0||y>=n || grid[x][y] ==1) return;
        if(x==m-1&& y == n-1) {
            result = Math.min(result, step+1);
            return;
        }
        grid[x][y] = 1;
        for (int[] footprint : footprints) {
            dfs(grid,x+footprint[0],y+footprint[1], step+1);
        }
        grid[x][y] = 0;
    }

    public int shortestPathBinaryMatrix2(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        if(grid[0][0] ==1 || grid[m-1][n-1] == 1) return -1;
        Queue<int[]> queue =new LinkedList<>();
        queue.add(new int[]{0,0});
        int step = 0;
//        int result = Integer.MAX_VALUE;
        grid[0][0] = 1;
        while(!queue.isEmpty()) {
//            int size = queue.size();
//            step++;
//            for (int i = 0; i < size; i++) {
            int[] node = queue.poll();
            int x = node[0];
            int y = node[1];
            int preLen = grid[x][y];
//            grid[x][y] = -1;
            for (int[] footprint : footprints) {
                int x1 = x+footprint[0];
                int y1 = y+footprint[1];
                if(isValid(grid,x1, y1)) {
//                    if (x1 == m-1 && y1 == n-1) {
//                        result = Math.min(result, step+1);
//                        return result;
//                    }else {
                    grid[x1][y1] = preLen+1;
                    queue.add(new int[] {x1,y1});
//                    }
                }
            }
//            }
        }

        return grid[m - 1][n - 1] == 0 ? -1 : grid[m - 1][n - 1];
    }

    private boolean isValid(int[][] grid, int x, int y) {
        int m = grid.length;
        int n = grid[0].length;
        if(x<0|| x>=m||y<0||y>=n || grid[x][y] != 0) return false;
        return true;
    }


}