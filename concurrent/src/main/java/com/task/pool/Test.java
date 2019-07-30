package com.task.pool;

import java.util.List;

public class Test {
    public static void main(String[] args){
        List<IPSegment> ipSegments=IPSegment.parse("192.168.1.1-192.168.1.25");
        EnvDetectTaskPool envDetectTaskPool=EnvDetectTaskPool.getInstance();
        envDetectTaskPool.execute(new Task("root","123qwe",ipSegments,1));
    }
}
