package com.learning.basic.java;

import scala.collection.parallel.ForkJoinTasks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ForkJoinPoolTest {

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        forkJoinPool.invoke(new ForkJoinSubAction(0,10000));
    }
}

class ForkJoinSubAction extends RecursiveAction {

    private final Integer startIdx;
    private final Integer endIdx;

    private static final int THRESHOLD = 10;

    public ForkJoinSubAction(Integer startIdx, Integer endIdx) {
        this.startIdx = startIdx;
        this.endIdx = endIdx;
    }

    @Override
    protected void compute() {
        if(endIdx - startIdx+1> THRESHOLD) {
            List<ForkJoinSubAction> subTasks = createSubTasks();
            ForkJoinTask.invokeAll(subTasks);
            return;
        }
        processing();
    }

    private List<ForkJoinSubAction> createSubTasks() {
        List<ForkJoinSubAction> actions = new ArrayList<>();
        int mid = startIdx+ (endIdx - startIdx)/2;
        actions.add(new ForkJoinSubAction(startIdx, mid));
        actions.add(new ForkJoinSubAction(mid, endIdx));
        return actions;
    }

    private void processing() {
        for (int i = startIdx; i <= endIdx; i++) {
            System.out.println(Thread.currentThread().getName() + " process:" +i);
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
