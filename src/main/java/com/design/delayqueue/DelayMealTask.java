package com.design.delayqueue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * <pre>
 * @Description:
 * 延迟队列元素
 * </pre>
 *
 * @version v1.0
 * @ClassName: DelayTaskOri
 * @Author: sanwu
 * @Date: 2020/12/15 18:56
 */
public class DelayMealTask implements Delayed {
    private static final AtomicLong atomicLong = new AtomicLong();

    private long startTime;

    private long taskId;

    public DelayMealTask(long startTime) {
        this.startTime = startTime;
        this.taskId = atomicLong.incrementAndGet();
    }
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.startTime - System.nanoTime(), TimeUnit.NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        if (o == this) {
            return 0;
        }
        if (o instanceof DelayMealTask) {
            DelayMealTask task = (DelayMealTask)o;
            if(this.startTime > task.getStartTime() ) {
                return 1;
            } else if(this.startTime < task.getStartTime() ) {
                return -1;
            } else{
                if (this.taskId< task.getTaskId()){
                    return -1;
                } else {
                    return 1;
                }
            }
        }
        long diff = this.getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS);
        if (diff<0) {
            return -1;
        } else if(diff>0) {
            return 1;
        } else {
            return 0;
        }
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }
}
