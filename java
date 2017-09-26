一、环境搭建：
1.1、安装jdk：访问http://www.oracle.com/technetwork/java/javase/downloads/index.html下载jdk
1.2、配置环境变量：https://course.tianmaying.com/java-basic+java-environment#2
1.3、jdk：java开发包，jre：java的运行平台，jvm：java的虚拟机
JDK在包含JRE之外，提供了开发Java应用的各种工具，比如编译器和调试器。
JRE包括JVM和JAVA核心类库和支持文件，是Java的运行平台，所有的Java程序都要在JRE下才能运行。
JVM是JRE的一部分，Java虚拟机的主要工作是将Java字节码（通过Java程序编译得到）映射到本地的 CPU 的指令集或 OS 的系统调用。JVM回根据不同的操作系统
  使用不同的JVM映射规则，从而使得Java平台与操作系统无关，实现了跨平台的特性性。
1.4、安装maven：（项目构建，版本控制，库依赖大概用到的功能就这三个吧）
下载：http://maven.apache.org/download.cgi
配置环境变量：http://jingyan.baidu.com/article/cb5d61050b8ee7005d2fe04e.html
1.5、安装Tomcat：


二、编写java代码：
2.1、java大小写敏感   
程序的文件名称必须和类的名称完全相同，Java代码的文件都以类名加.java后缀进行命名  比如HelloWorld类的代码保存在HelloWorld.java文件中
public static void main(String[] args)是一个方法，这是Java程序的入口 任何Java程序的代码都是从这个方法开始执行的
java关键字是系统定义的，标识符是自己定义的（变量）
2.2、在java中类可以看成一个模板。
public是一个修饰符，表示外部可以访问这个类，我们已经在HelloWorld程序中遇见过了。

Car的状态和行为就分别体现为内部定义的成员变量和成员方法上。
public class Car {
    int color; // 成员变量 也称为属性  一个对象的状态是通过成员变量的值决定的。
    int speed; // 成员变量
 
    // 成员方法   方法定义了类的行为，一个类可以有很多方法，在方法中可以编写逻辑，操纵数据，执行特定动作。我们有时也称方法为函数。
    void startup() {
        System.out.println("启动!");
    }

    // 成员方法 tartup()和print()方法是一个没有返回值和参数的方法。没有返回值的情况，我们使用void表示。
    void run(int speed) {
        System.out.println("我的速度是" + speed);
    }
}
