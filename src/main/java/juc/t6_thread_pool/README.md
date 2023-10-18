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