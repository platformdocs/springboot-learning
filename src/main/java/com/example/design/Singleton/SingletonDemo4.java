package com.example.design.Singleton;

/**
 * @description 静态内部类（线程安全，调用效率高，可以延时加载）
 **/
public class SingletonDemo4 {
    private SingletonDemo4(){
        System.out.println("SingletonDemo4");
    }

    /** 静态内部类 */
    private static class SingletonClassInstance {
        private static final SingletonDemo4 instance = new SingletonDemo4();
    }

    /** 只有在第一次调用时，才会被创建，可以认为是懒加载的升级版本 */
    public static SingletonDemo4 getInstance(){
        return SingletonClassInstance.instance;
    }
}

