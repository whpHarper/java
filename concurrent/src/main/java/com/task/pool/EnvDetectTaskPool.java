package com.task.pool;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 自定义线程池
 */
public class EnvDetectTaskPool {
    private static EnvDetectTaskPool instance;

    private final int POOL_SIZE=10;
    private final int TASK_NUM=5;
    //线程池执行器
    private ThreadPoolExecutor executor;
    private Map<Integer,Task> taskMap;

    private EnvDetectTaskPool(){
        //使用固定线程池大小类型线程池
        executor=(ThreadPoolExecutor) Executors.newFixedThreadPool(POOL_SIZE);
        taskMap=new HashMap<Integer, Task>();
    }

    /**
     * 单例模式（懒汉式）获得线程池实例，懒汉式容易发生并发问题，使用sychronized
     * @return
     */
    public static synchronized EnvDetectTaskPool getInstance(){
        if(instance==null){
            instance=new EnvDetectTaskPool();
        }
        return instance;
    }

    /**
     * 根据id移除task
     */
    public synchronized void remove(int id){
        Task task=taskMap.get(id);
        task.stop();
        taskMap.remove(id);
    }

    /**
     * 每个任务创建5个线程进行执行
     * @param task
     */
    public synchronized void execute(Task task){
        taskMap.put(task.getId(),task);
        for(int i=0;i<TASK_NUM;i++){
            executor.execute(task);
        }
    }

    /**
     * 获取任务进度
     * @param id
     * @return
     */
    public synchronized int progress(int id){
        Task task=taskMap.get(id);
        if(task==null){
            return 0;
        }
        return task.progress();
    }

    /**
     * 停止任务
     * @param id
     */
    public synchronized void stop(int id){
        Task task=taskMap.get(id);
        if(task!=null){
            task.stop();
            taskMap.remove(id);
        }
    }
}
