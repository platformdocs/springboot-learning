package com.example.design.Prototype;

/**
 * 通过clone方法创建的Prototype对象不会执行构造方法
 * 
 * 浅拷贝与深拷贝

浅拷贝：对值类型的成员变量进行值的复制，对引用类型的成员变量仅仅复制引用,不复制引用的对象。
深拷贝：对值类型的成员变量进行值的复制，对引用类型的成员变量也进行引用对象的复制。
使用场景：
• 类的初始化需要消耗非常多的资源。
• 通过new产生一个对象需要非常繁琐的数据准备或访问。
• 一个对象需要提供给其他对象访问，而且各个调用者可能都需要修改其值时。
 */
public class Prototype implements Cloneable {
    public static void main(String[] args) {
        System.out.println("【步骤1】开始执行Prototype类型的对象创建工作");
        Prototype prototype = new Prototype();

        /** 执行clone方法创建的Prototype对象 */
        System.out.println("【步骤2】开始执行clone操作");
        prototype.clone();
    }

    public Prototype() {
        System.out.println("-----Prototype的构造方法被执行了！-----");
    }

    @Override
    protected Prototype clone() {
        try {
            return (Prototype) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
