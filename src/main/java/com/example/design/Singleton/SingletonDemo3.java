package com.example.design.Singleton;

/**
 * @description 双重校验
 **/
public class SingletonDemo3 {

    private volatile static SingletonDemo3 singletonDemo3;

    private SingletonDemo3() {}

    public static SingletonDemo3 newInstance() {
        if (singletonDemo3 == null) {
            synchronized (SingletonDemo3.class) {
                if (singletonDemo3 == null) {
                    singletonDemo3 = new SingletonDemo3();
                }
            }
        }
        return singletonDemo3;
    }
}

// volatile的作用
// 保证变量的可见性
// volatile关键字的作用就是保证共享变量的可见性。什么是可见性呢，就是一个线程读变量，总是能读到它在内存中的最新的值，也就是说不同的线程看到的一个变量的值是相同的。CPU都是有行缓存的，volatile能让行缓存无效，因此能读到内存中最新的值。
// 保证赋值操作的原子性
// 原子性就是不能被线程调度打断的操作，是线程安全的操作，对于原子性操作，即使在多线程环境下，也不用担心线程安全问题或者数据不一致的问题。有些变量的赋值本身就是原子性的，比如对boolean，对int的赋值，但是像对于long或者double则不一定，如果是32位的处理器，对于64位的变量的操作可能会被分解成为二个步骤：高32位和低32位，由此可能会发生线程切换，从而导致线程不安全。如果变量声明为volatile，那么虚拟机会保证赋值是原子的，是不可被打断的。
// 禁止指令重排
// 正常情况下，虚拟机会对指令进行重排，当然是在不影响程序结果的正确性的前提下。volatile能够在一定程度上禁止虚拟机进行指令重排。还有就是对于volatile变量的写操作，保证是在读操作之前完成，假设线程A来读变量，刚好线程B正在写变量，那么虚拟机会保证写在读之前完成。