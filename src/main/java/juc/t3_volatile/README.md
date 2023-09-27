**volatile**

1.保证线程可见性
- MESI
- 缓存一致性协议

2.禁止指令重排序(CPU)
> *重排序，流水线执行，为了提高效率，编译器会针对指令进行重排序*
- DLC单例
- Double Check Lock 双重检查锁：需要加volatile吗？
- DoubleCheckLock.java
