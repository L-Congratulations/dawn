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
2.8.1、赋值运算符：将右边的结果赋值给左边，左边的值必须是一个明确的变量。赋值实际上是对原值的拷贝引用，不会改变原值
与大部分编程语言相同，Java使用'='运算符来进行赋值操作。这种操作会将右边的计算结果（称为右值）赋给左边的变量。
右值可以为任意常数、变量或表达式而左值必须为一个明确的变量。
在Java中，使用'='对一个对象进行赋值时，真正操作的是它的引用，即是对对应的引用进行拷贝。这也正是上一节内容中引用类型的含义。

2.8.2、算术运算符：加减乘数
      数学运算中存在自动类型转换：
      byte、short与char进行数学运算，会自动转换为int
      不同类型的数据进行运算，会自动转换为“更高级别”的类型

2.8.3、关系运算符：关系运算符生成布尔(boolean)类型的结果，即返回true或者false。
> < == 
在java中'=='和'!='作为关系运算符只用来比较对象的引用。

如果想比较两个对象实际内容是否相同，需要调用对象的equals()方法。比如判断一个字符串str的内容是否为"abcd"，应该这样比较：
if (str.equals("abcd")) {
}
下面这种方式是一种错误的方式：

if (str == "abcd") {
}

2.8.4、逻辑运算符：&& || ！
逻辑短路：逻辑运算符支持短路操作，一旦能够明确表达整个表达式的值，我们就不需要计算表达式的余下部分了。只要判断出是false则编译器就不会在执行后边的代码

2.8.5、类型转换运算符：
我们在很多情况下会用到类型转换。在适当的时候，Java也会根据数据类型将数据类型自动转为另一种。比如我们为float变量赋一个整数值，
编译器会将int转化为float赋给变量。
int a = 10;
long b = (long) a;
long c = (long) 100;

三、程序的控制流：控制代码的执行顺序
java中存在三种控制结构：
顺序结构：按书写代码的先后顺序逐条执行语句
循环结构：语句重复执行
选择结构：满足特定条件执行

3.1、循环结构：while循环、do/while循环、for循环
3.1.1、while循环：
while( 布尔表达式 ) {
	//循环内容
}
只要布尔表达值是true，则循环体就会一直执行
如：不断的从控制台获得输入
public class Hello {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        while (true){
            String command = scan.next();
            System.out.println(command);
            if (command.equals("exit")){
                break;
            }
        }
        scan.close();
    }
}
我们将while语句中的布尔表达式设置为true，即循环体不断执行。那么程序就会不断地等待用户的输入，并且将用户的每一次输入打印出来。

3.1.2、do/while循环：do/while循环至少会执行一次。
do {
	//循环内容
} while( 布尔表达式 );
3.1.3、for循环：
for(初始化; 布尔表达式; 更新循环控制变量) {
    //循环内容
}
3.2、选择结构：if结构、switch结构
3.2.1、

四、定义类：
编写java程序简言之就是两件事：定义类和使用类
定义类的工作包括：
定义类的属性（也称为成员变量）
定义类的方法
定义类的构造方法（也称构造器或者构造函数）

使用类的工作包括：
基于类创建对象（即访问类的构造器）
访问类的属性
访问类的方法
4.1、定义类：
在实际工作中一个类一般是用来表示一个概念或者事物，比如定义Post来表示博客，定义Car来表示汽车。所以命名的时候要尽量明确
类和java文件的关系：
关于类和Java文件的关系:
一般情况下，都是一个类一个.java文件
如果一个.java文件里有多个类，只可能有一个public的类，而且文件名必须和public类同名
如果文件里所有类都不是public的，那么文件名和任意一个类同名即可
4.2、定义成员变量：注意成员变量的命名通常首字母小写，使用驼峰形式。
定义成员变量时，可以用private、protected或者public进行修饰，可以控制外部的可见性：
private：表示任何其他类不能直接访问该成员变量，只有该类自身可以访问
protected：表示只有该类自身及其子类可以访问该成员变量
public：表示任何类都可以直接访问该成员变量
没有修饰：表示同一个包的类可以访问该成员变量
4.3、定义方法：方法的返回类型可以为基本类型或者引用类型；如果一个方法定义了返回类型，在方法体内必须有return语句返回该类型的数据。
		（return后面可以跟字面量、变量或者表达式。return也可以单独使用，不跟任何表达式，表示立即结束当前方法的执行。）
类似于成员变量，方法也可以控制可见性：
private：表示任何其他类不能直接访问该成员方法，只有该类自身可以访问
protected：表示只有该类自身及其子类可以访问该成员方法
public：表示任何类都可以直接访问该成员方法
没有修饰：表示同一个包的类可以访问该成员方法

4.4、创建和使用类：
4.4.1、堆和栈的区别：堆和栈都是Java中常用的存储结构，都是内存中存放数据的地方：


五、字符串操作：
5.1、String的format方法：用于字符串拼接，效率更高，更直观
String name = "David";
int age = 18;
String hobby = "篮球";
String formatString = "我的名字是%s，我今年%d岁，我的爱好是%s";
String output = String.format(formatString, name, age, hobby);
System.out.println(output);
其中的%s和%d表示将会用字符串和整数的值来替代它们。是占位符

5.2、
String的比较
equals()与==的区别
String的查找
String的替换
String的截取

六、静态变量和静态方法：
当为一个类创建实例时，每个不同的实例的成员变量都有自己特定的值。
有时我们希望定义一个类成员，使其作为该类的公共成员，所有实例都共享该成员变量，此时需要使用static关键字
根据字面意思我们可以猜测static是静态的意思，被它们修饰的变量或者方法就含有“静态”的性质，与“静态”对应的就是“实例”，
因为“实例“都是程序在运行时动态生成的。

6.1、static修饰变量：所有的实例都会对原变量产生影响，只分配一个内存。普通变量是对原始值的拷贝，静态变量则直接改变原始值。静态变量可以通过类名直接访问
类的成员变量中，用static修饰的变量称为静态变量或者类变量，而没有用static修饰的变量则是普通变量。
对于普通成员变量，每创建一个该类的实例就会创建该成员变量的一个拷贝，分配一次内存。由于成员变量是和类的实例绑定的，
所以需要通过对象名进行访问，而不能直接通过类名对它进行访问。
而对于静态变量在内存中只有一份，Java虚拟机（JVM）只为静态变量分配一次内存，在加载类的过程中完成静态变量的内存分配。
由于静态变量属于类，与类的实例无关，因而可以直接通过类名访问这类变量。
我们来修改Post类，增加一个计数器，记录Post对象的个数。

public class Post {

    private String title;
    private String content;
    
    public static int count = 0;
    
    public Post(){
        count++;
    }
    ...
}
title和content是我们之前定义的实例成员变量，每个Post实例都独立的拥有title和content属性，此时修改这两个字段的值，对其他Post实例不会有影响。

我们再来看count，我们将其声明为static，表明count是个静态变量。当我们修改count的值时，所有实例的count值都会改变。每当我们实例化一个Post对象，
我们使count值+1，此时，我们可以直接通过Post.count 得到一共有多少Post实例。
6.2、static修饰方法：在方法前加修饰符，表明该方法与某个具体实例无关，仅仅是该类的一个公共方法。
public class Post {
        
    private String title;
    private String content;
    
    private static int count = 0;
    
    public Post(){
        count++;
    }
    
    public static int getCount(){
        return count;
    }
        ...
}
这里我们不希望count属性直接暴露为公开的属性，因为公开后，任何地方都可以通过Post.count++来改变count值。我们只希望在实例化时使count值+1。
因此我们可以将count声明为私有变量。
同时我们又希望能够直接通过Post类得到当前Post实例的数量，因此我们声明了一个静态的getCount方法。声明后，我们不需要实例化Post类，直接通过:
Post.getCount()   // 这里我们是通过类名.方法的方式访问静态方法getCount()的。
得到当前Post实例的数量。而此时也没有count值会在外部被修改的风险。
声明成static的方法有几条限制：

仅能调用其他的static方法。
只能访问static数据。
不能以任何方式引用this或super
静态方法可以直接通过类名调用，任何的该类的实例也都可以调用它的静态方法，因此静态方法中不能用this和super关键字。
在一个static方法中引用任何实例变量都是非法的。

6.3、static修饰代码块：
类似于静态变量和静态方法，有static修饰的代码块称为静态代码块。

它独立于类成员，可以有多个，JVM加载类的时候会执行这些静态代码块，如果static代码块有多个，JVM则会按照它们在类中出现的顺序依次执行它们，
且每个代码块只能执行一次。我们可以利用静态代码块可以对一些static变量进行赋值。

如果我们的Post数量是从数据库中取得的，此时如果简单的给count赋值为1明显是不对的，此时我们可以通过静态代码段在类加载进来时执行获取count值的代码块：

public class Post {
        
    private String title;
    private String content;
    
    private static int count = 0;
    
    static {
        count = 100; //这里假设100是从数据库中获取Post的数量
    }
    
    public Post(){
        count++;
    }
    
    public static int getCount(){
        return count;
    }
        ...
}

七、泛型：在使用泛型时,我们可以把类型作为参数传入到泛型类中。类似于把参数传入到方法中一样。
一个类的成员变量，一个函数中的参数，都具有一种数据类型，可以为基本数据类型（如int类型）或者引用类型（如Car类型）。但是当数据类型不确定时，代码就无法
复用，为了让代码通用性更高，我们可以把类型作为参数传入到泛型类中。
使用泛型类时，注意实际传入的类型参数不能是原生类型，必须是引用类型，因此如果希望传入int类型的话，那么需要传入int对应的包装类Interger。
对应地，double类型则要传入包装类Double
泛型类支持多个类型参数。比方说我们需要实现一个三元组Triple类，存储三个类型的变量，我们可以实现如下：
public class Triple<A, B, C> {
    private A a;
    private B b;
    private C c;

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }

    public C getC() {
        return c;
    }

    public void setC(C c) {
        this.c = c;
    }

}
使用Triple类的方式如下：

Triple<String, Integer, Float> triple = new Triple<String, Integer, Float>();
triple.setA("something");
triple.setB(1);
triple.setC(1.0f);
