对象的创建过程
- new一个内存空间
- invoke构造方法给空间赋值
- 局部变量指向该空间

DCL单例要不要加volatile 防止半初始化状态发生指令重排
- 双重检查锁加volatile，不加上述创建对象过程发生指令重排序
- 参考DoubleCheckLock.java

**volatile** 修饰的内存空间禁止乱序

1.保证线程可见性
- MESI
- 缓存一致性协议

2.禁止指令重排序(CPU)
> *重排序，流水线执行，为了提高效率，编译器会针对指令进行重排序*
- DLC单例
- Double Check Lock 双重检查锁：需要加volatile吗？
- DoubleCheckLock.java
- 防止指令重排添加内存屏障
  - loadfence 写屏障 
  - storefence 读屏障
- happens-before原则，八种重排序必须遵守的原则

3.并不能保证原子性
- Count.java