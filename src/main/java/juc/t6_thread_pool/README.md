阻塞队列、拒绝策略、生产者消费者模式

**线程池是什么**

**自定义线程池以及七个参数**
- 最大线程执行maximumPoolSize：核心线程不足，启用临时线程
- 最小启动线程执行corePoolSize:核心线程执行
- 释放临时线程时间keepAliveTime：线程充足，等待时间杀死临时线程， 
- keepAliveTime时间单位：TimeUnit.SECONDS
- 阻塞队列LinkedBlockingQueue：
- 工厂模式设置产生线程的模式：defaultThreadFactory，默认线程工厂。
- 拒绝策略：new ThreadPoolExecutor.DiscardOldestPolicy()，抛弃最老的策略。

**线程池核心属性**
```java
        // 线程池属性值
        // int类型数值表达两个值 1：声明当前线程池的状态 2：声明线程池的线程数
        // 高三位是线程池状态    低29为是线程池的线程个数
        private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
        private static final int COUNT_BITS = Integer.SIZE - 3;     // 29 位，方便后续位运算
        private static final int CAPACITY   = (1 << COUNT_BITS) - 1;    // 线程池的最大容量

        // 线程池状态
        private static final int RUNNING    = -1 << COUNT_BITS;  // 111 代表线程池的running状态
        private static final int SHUTDOWN   =  0 << COUNT_BITS;  // 000 代表线程池为shutdown状态：表示不接受先任务但是还会处理队列中的任务
        private static final int STOP       =  1 << COUNT_BITS;  // 001 代表线程池为 stop 状态：表示不接受新任务同时中断现在执行的任务
        private static final int TIDYING    =  2 << COUNT_BITS;  // 010 代表线程池为 tidying 状态：过渡状态，代表线程池即将结束
        private static final int TERMINATED =  3 << COUNT_BITS;  // 011 代表线程池为 terminated 状态，代表线程池已经结束

        private static int runStateOf(int c)     { return c & ~CAPACITY; } // 得到线程池的状态
        private static int workerCountOf(int c)  { return c & CAPACITY; }  // 得到当前线程池的线程数量
        private static int ctlOf(int rs, int wc) { return rs | wc; }
```

**线程池状态变化**
- RUNNING状态：运行状态，正常接收任务 RUNNING -> SHUTDOWN、STOP
- SHUTDOWN状态：关闭状态，不接受新任务，可以处理阻塞队列中的任务 shutdown()
- STOP状态：停止状态，不接受新任务，不处理阻塞队列中的任务，中断正在工作的线程 shutdownNow()
- TIDYING状态：过渡状态，SHUTDOWN、STOP -> TIDYING 队列为空、工作现场为空/工作线程全空
- TERMINATED状态：结束状态，terminated()