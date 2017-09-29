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
java是一种面向对象的语言：意味着要从面向对象的角度出发去思考java程序
java中的主要代码都会写在类中，类是通过关键字class定义的
2.1、java注意点：
2.1.1、大小写敏感
2.1.2、程序的文件名必须和类的名称完全相同，Java代码的文件都以类名加.java后缀进行命名 比如HelloWorld类的代码保存在HelloWorld.java文件中
2.2、java入口程序：任何Java程序的代码都是从这个方法开始执行的
public static void main(String[] args)是一个方法，这是Java程序的入口 

2.3、关键字和标识符：
java关键字是系统定义的，
标识符是自己定义的（变量）：为了程序的可读性，一般情况下，类名以大写字母开头，比如HelloWorld以大写字母H开头；方法名一般以小写字母开头，
比如main方法以小写字母m开头。如果名称中包含几个单词，从第二个单词开始每个单词首字母大写，这种命名方式我们称之为驼峰命名法。

2.4、定义一个类：类描述一类对象的状态和行为的模板
在这的类是作为一个模板，并不作为程序的执行文件，所以只是定义了类的属性和方法，并没有调用public static void main(String[] args)方法：
在idea中创建一个Post.java的新文件作为模板，写入：
public class Post {
    String title; // 成员变量（属性）一个对象的状态是通过成员变量的值决定的。
    String content; // 成员变量
    
    // 成员方法（函数）方法定义了类的行为，一个类可以有很多方法，在方法中可以编写逻辑，操纵数据，执行特定动作。我们有时也称方法为函数。
    void print() {
        System.out.println(title);
        System.out.println(content);
    }
}
public是一个修饰符，表示外部可以访问这个类
Post的状态和方法分别体现为内部定义的成员变量和成员方法上
print()方法是一个没有返回值和参数的方法。没有返回值的情况，我们使用void表示。

当在别的java文件中调用这个类的时候首先要实例化，然后在实例化的对象上调用类的属性和方法

2.5、创建和使用类（模板）：
创建：我们需要给声明对象的类型为Post，并进行命名，代码中命名为post（post就是一个标识符）；然后我们需要使用new关键字创建Post对象。
这里创建的post变量是函数内的局部变量，你也可以命名为其它任何合法的标识符，比如：
 Post myPost = new Post(); // 创建博客对象   (变量用驼峰法命名)
 
public class HelloWorld {      
    public static void main(String[] args) {  
        Post post = new Post(); // 创建博客对象      
        post.title = "天码营的博客"; // 访问对象属性
        post.content = "这是我的第一篇博客";  // 访问对象属性
        post.print(); // 调用对象方法
        
    }
}
2.5.1、局部变量和成员变量：
区别：成员变量属于某个对象.是属性
局部变量是一个临时变量，方法执行结束后就不在起作用。在方法中声明的变量都属于局部变量
局部变量与成员变量不同，它不属于某个对象，是一个临时变量，当方法执行结束，变量就不再起作用了。一个方法中声明的变量都属于局部变量。
这些在main函数中定义的变量都是局部变量，和类的成员变量不同
2.6、包（Package）：在开发过程中，类的数量会越来越多，我们可以通过包（Package）来组织类。包可以看做是存放Java类的不同文件夹。
右键点击src文件夹，【New】->【Package】，输入com.demo，即创建成功。
在一个大型项目中，我们会将成百上千的类，放到不同的包中，不仅更易于查找，也能防止命名冲突。

2.7、基本数据类型：定义一个变量就是再内存中划分一个位置
2.7.1、变量（Variable）是在内存中动态存储值的地方
Java要求在使用一个变量前要求必须声明它的类型。
声明一个变量就是向计算机申请内存来存储值，JVM会根据变量的类型为变量分配相应的存储空间。因此，通过定义不同类型的变量，
可以在内存中储存整数、小数或者字符串等值
Java中的数据类型分为两类：
基本数据类型（或者称为原生数据类型）
引用数据类型
2.7.2、基本数据类型：
（1）变量声明：可以一次声明一个或者多个同一类型的变量，声明多个变量时用,隔开
    如果变量在声明时没有进行初始化，则在使用前需要进行赋值，否则会编译报错
（2）Java语言提供了八种基本类型，其中包括六种数字类型（四种整数型byte（8位）、short（16位）、int（32位）、long（64位），两种浮点型float（32位）
double（64位），还有一种布尔型boolean，一种字符类型char（char数据类型可以储存任何字符0-65535位）
（double比float现实的更精确如double可以显示1.2322222222222 而float则只能显示1.2322222）
（3）、类型转换：一个浮点数字面量默认是double类型，如果要定义float类型则要在字面量最后添加f或者F：
double a = 1.23;
float b = 1.23F;
float c = 1.23; // 编译错误
（4）、数组：
int[] anArray;
anArray = new int[10];//初始化一个长度为10的整形数组

anArray[0] = 100;//初始化第一个变量
anArray[1] = 200;
anArray[2] = 300;
anArray[3] = 400;
anArray[4] = 500;
int[] anArray = { 100, 200, 300, 400, 500, 600, 700, 800, 900, 1000};
这样会自动创建一个长度为10的int数组。我们通过anArray.length可以得到数组的长度。
输出一个数组：System.out.println(Arrays.toString(aArray));

（5）、string。一个字符串就是一个字符序列
String并不是基本的数据类型，是一个类
字符串可以通过+和+=操作符进行拼接，比如：String str1 = "abc";  String str2 = str1 + "def" （输出abcdef）; str1 += "def"（输出abcdef）;
String类的实例是一个不可变的对象，意味着对String的操作都会产生一个新的String对象。（每次操作都不会改变原对象）

（6）、获取控制台输入
为了获取控制台输入，首先需要一个创建一个Scanner对象：
Scanner scan = new Scanner(System.in);
然后调用对象上的方法获取数据
int number = scan.nextInt();  // 用户输入回车键之前的所有输入信息（不包括回车键）
将数据打印出来
System.out.println(number);
最后关闭
scan.close();

（7）、import
Java平台提供了很多方便我们编程的类，通常称之为类库(lib)，也称之为应用编程接口(Application Programming Interface, API)。
比如Scanner就是Java平台为我们提供的用于获取控制台输入的类，使用时要进行相应的import。
此前，我们已经在面向对象基础的课程中了解了package的概念。

除了逐个类的引入之外，也可以用通配符一次性将某个包内的所有类一起引入，例如：
import java.util.*;
表示将java.util包下的所有类都引入进来。

2.8、运算符
2.8.1、赋值运算符：
