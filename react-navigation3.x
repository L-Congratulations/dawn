一、创建几个辅助页面：
1.1、根目录下创建js——page然后创建：
WelcomePage、HomePage、DetailPage

import React, {Component} from 'react';
import {Platform, StyleSheet, Text, View} from 'react-native';



type Props = {};
export default class HomePage extends Component<Props> {
    render() {
        return (
            <View style={styles.container}>
                <Text style={styles.welcome}>Home Page!</Text>
            </View>
        );
    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        backgroundColor: '#fcff1b',
    },
    welcome: {
        fontSize: 20,
        textAlign: 'center',
        margin: 10,
    },
    instructions: {
        textAlign: 'center',
        color: '#333333',
        marginBottom: 5,
    },
});

1.2、在js下创建AppNavigator文件夹
用来存放导航器（路由）相关内容，方便管理
（1）安装react-navigation3.x：
npm i react-navigation -S
npm i react-native-gesture-handler -S
react-native link react-native-gesture-handler

然后重新启动App：run-ios

（2）在AppNavigator文件夹下创建AppNavigators.js:
导入要使用的导航器以及createAppContainer组件，然后编写导航器包括一个用来封装WelcomPage、一个用来封装应用页面；然后用SwitchNavigator来做单向跳转限制；最后用AppContainer来包裹整个导航组件，并在index.js中替代App

关于导航器的相关配置可以查看官方文档，或者(https://blog.csdn.net/qq_39910762/article/details/85620312)里面是中文的介绍，需要哪个属性再去按照案例添加。

import {createStackNavigator,createAppContainer,createSwitchNavigator} from 'react-navigation'
import HomePage from "../page/HomePage";
import WelcomePage from "../page/WelcomePage";
import DetailPage from "../page/DetailPage";


const InitNavigator = createStackNavigator({
    Welcome:{
        screen:WelcomePage,
        navigationOptions:()=>({header:null})
    }
})

const MainNavigator = createStackNavigator({
    Home:{
        screen:HomePage,
        navigationOptions:()=>({
            header:null
        })
    },
    Detail:{
        screen:DetailPage,
        navigationOptions:()=>({header:null})
    }
})

const SwitchNavigator = createSwitchNavigator({
    Init:InitNavigator,
    Main:MainNavigator
},{
    initialRouteName:'Init'
})
export default createAppContainer(SwitchNavigator)

（3）在项目中使用导航组件：
在index.js中导入并使用导航组件：
import AppNavigator from './js/AppNavigator/AppNavigators'
AppRegistry.registerComponent(appName, () => AppNavigator);

二、从Welcome页面跳转到App主页面：
（1）在WelcomePage页面：
在componentDidMount生命周期里面写一个计时器，当计时结束之后触发函数，跳转到HomePage页面：
注意不要使用componentDidMount(): void:{};这种写法获取不到navigation属性

而是使用常规的写法：
    componentDidMount(){
        this.timer=setTimeout(()=>{            
            this.props.navigation.navigate('Main')
        },2000)
    }
    componentWillUnmount(){
        this.timer && clearTimeout(this.timer)
    }
时间计时器要在组件销毁的时候一同销毁，否则假如即使时间是10s，但是我们在10s之内关闭了页面，那么计时器就会导致内存泄漏。


三、应用内的导航操作（页面跳转）我们都封装在一个类里面，方便管理，这是一个全局导航的控制类,所有的跳转都封装在这个类里面。而某个页面单独使用的导航器就在页面内定义，但是导航器的跳转功能还是要在NavigationUtil里面进行定义
（1）在AppNavigator文件夹下创建一个NavigationUtil.js文件

（2）创建一个类，并将这个类导出，这个类就是全局导航的控制类，所以的导航跳转操作都通过这个类实现，方便管理。在里面创建一些静态方法：如返回上一页操作、重置到首页操作等，这样在页面中进行页面跳转的时候直接在这个类里面调用方法，不需要在每个页面中单独定义了。

export default class NavigationUtil {
    static resetToHomePage(params){
        // 重置到首页方法，我们在这个方法里面传入一个参数，然后从这个参数里面获取到navigation
        const {navigation} = params;
        navigation.navigate('Main')
    } 
}

（3）在页面中使用路由跳转类：
首先将NavigationUtil 导入到需要使用的页面中：
import NavigationUtil from '../AppNavigator/NavigationUtil'

然后调用这个类的方法，并传入参数：
 NavigationUtil.resetToHomePage({navigation:this.props.navigation})

四、创建BottomTab：
在AppNavigators.js中创建路由，然后在NavigationUtil中创建路由跳转的方法
4.1、创建几个底部tabBar的页面备用：
PopularPage（最热页面）、TrendingPage（趋势页面）、FavoritePage（收藏页面）、MyPage（我的页面）、

4.2、在HomePage中配置BottomeTabBar：
因为这个底部的tabBar只会在HomePage中使用，且底部TabBar共用，所有直接在HomePage中使用：
（1）导入导航组件：
import {createBottomTabNavigator} from 'react-navigation'

（2）导入TabBar需要使用的组件：
import PopularPage from './PopularPage';
import TrendingPage from './TrendingPage';
import FavortiePage from './FavoritePage';
import MyPage from './MyPage';

（3）在HomePage组件中使用createBottomTabNavigator封装一个方法，这样写是为了层级结构更加明显，管理更加方便：
与render并列封装一个私有方法
 _bottomTab(){
        return createBottomTabNavigator({
            Favorite:{
                screen:FavoritePage
            },
            My:{
                screen:MyPage
            },
            Popular:{
                screen:PopularPage
            },
            Trending:{
                screen:TrendingPage
            }
        })
    }

注意：在这方法里面要return createBottomTabNavigator() 因为我们需要用createAppContainer来包装createBottomTabNavigator执行的结果，所以要把这个函数完整的返回出来，如果不加return，那么_bottomTab输出的结果为空，

（4）在组件中使用：
因为导航器定义就是一个组件，所以在使用的时候需要使用<AppContainer />来调用
render() {
        const AppContainer = createAppContainer(this._bottomTab())
        return <AppContainer/>
    }
注意：在这要使用this._bottomTab()而不是this._bottomTab这个引用，因为我们要传递的参数是createBottomTabNavigator() 这个方法的结果，所以在这要加()
然后因为导航器定义的就是一个组件，所以在单独使用导航器的时候要使用组件的格式：<AppContainer/>


五、配置BottomBar的样式：
5.1、首先配置label（也就是）tabBar上显示的文字：
Favorite:{
                screen:FavoritePage,
                navigationOptions:{
                    tabBarLabel:'收藏'
                }
            },

5.2、配置icon：
使用react-native-vector-icons矢量图标来作为我们的icon
图标库：(https://oblador.github.io/react-native-vector-icons/)
（1）安装这个库到项目中：
npm i react-native-vector-icons -S

因为这个库不仅有js部分代码，还有native的代码，所以要将该组件库link（关联到）到我们的项目：
react-native link react-native-vector-icons
因为我们改动了原生的代码，所以需要重启项目。把原来的关闭，然后重新运行，如果不关闭原来的项目之间run-ios有时候依旧会报错

（2）在图标库中搜索图标，然后记住该图标的名字和所在的库：
例如whatshot图标：
搜索hot——找到MaterialIcons——whatshot

（3）在项目中使用图标：
首先将图标所在的组件库导入到需要图标的页面中：
import MaterialIcons from 'react-native-vector-icons/MaterialIcons'

然后在navigationOptions配置参数中使用：
navigationOptions:{
                    tabBarLabel:'收藏',
                    tabBarIcon:({tintColor,focused})=>(
                        <MaterialIcons name={'whatshot'} size={26} style={{color:tintColor}} />
                    )
                }

重启项目


六、为某个页面添加顶部的tab：
6.1、在热点页面引入导航组件和createAppContainer组件
PopularPage.js中：
import {createMaterialTopTabNavigator,createAppContainer} from 'react-navigation'

6.2、创建一个组件，来作为tab组件使用
class PopularTab extends Component{
    render(){
        const {tabLabel} = this.props
        return(
            <View style={styles.container}>
                <Text style={styles.welcome}>{tabLabel}</Text>
            </View>
        )
    }
}

6.3、在createMaterialTopTabNavigator组件中使用我们自定义的PopularTab组件：
_topNavigator(){
        return createMaterialTopTabNavigator({
            PopularTab1:{
                screen:PopularTab,
                navigationOptions:{
                    title:'Tab1'
                }
            },
            PopularTab2:{
                screen:PopularTab,
                navigationOptions:{
                    title:'Tab2'
                }
            },
            PopularTab3:{
                screen:PopularTab,
                navigationOptions:{
                    title:'Tab3'
                }
            },
            PopularTab4:{
                screen:PopularTab,
                navigationOptions:{
                    title:'Tab4'
                }
            },
            PopularTab5:{
                screen:PopularTab,
                navigationOptions:{
                    title:'Tab5'
                }
            }
        })
    }

6.4、在项目中使用createMaterialTopTabNavigator组件：
 const TopContainer = createAppContainer(this._topNavigator())
        return (
            <View style={styles.container}>
                <TopContainer />
                {/*<Text style={styles.welcome}>Popular Page!</Text>*/}
            </View>
        );

注意：我们要使用View进行布局，flex:1,marginTop:30
因为TopContainer 对应的单个Tab页面会占满整个页面，所以View内部只包含一个TopContainer 就行，当然如果里面还有别的元素就会按照flex默认的布局方式来分割View的空间。如果只有一个
TopContainer 那么需要定义Tab页面的时候就需要在PopularTab 组件里面去定义，

6.5、为PopularTab按钮提供跳转到详情页的功能
（1）打开NavigationUtil.js文件，在这里面定义所有的跳转功能
在里面定义一个共用的方法：这个方法会跳转到指定页面，而不是只指向Detail页面：
static goPage(params,page){
        // 跳转到指定页面
        const {navigation} = params;
        if(!navigation){
            console.log('navigation can not be null');
            return;
        }
        // 如果navigation不存在就打一个log并直接返回
        navigation.navigate(page,{...params})
        // 如果存在，那么传入打第一个参数就是路由也就是page，然后吧其他参数通过解构操作符直接传递进去
    }

（2）在需要使用的页面导入这个方法
import NavigationUtil from '../AppNavigator/NavigationUtil'

因为跳转到详情页是某个Tab自己的方法，所以我们需要在Tab中去定义，也就是在PopularTab 组件中去定义方法：
class PopularTab extends Component{
    render(){
        const {tabLabel} = this.props
        return(
            <View style={styles.container}>
                <Text style={styles.welcome}>{tabLabel}</Text>
                <Text onPress={NavigationUtil.goPage({
                    navigation:this.props.navigation
                },"Detail")}>跳转到详情页</Text>
            </View>
        )
    }
}

注意：在这至少要传入两个参数，一个是params中需要的
navigation：{navigation:this.props.navigation}
另一个参数是page："Detail"，这个名字是一个字符串，并且名字是来源于AppNavigators.js中MainNavigation里面定义的导航的名字

（3）刷新之后点击跳转到详情页没反应，这是因为PopularTab 是嵌套在PopularPage页面里面的，而PopularPage页面属于HomePage页面，但是HomePage页面和DetailPage页面属于并列关系，同在MainNavigator路由里面，所以嵌套路由不能直接调用父级及以上同级的页面。

一种处理方法就是将父级的navigation保存到一个静态变量里面，在这因为所有跳转的功能都是在这里定义的，而这个变量也是为了跳转而传递navigation定义的：
在HomePage页面的render函数中添加：
render() {
        NavigationUtil.navigation = this.props.navigation
        const AppContainer = createAppContainer(this._bottomTab())
        return <AppContainer/>
    }
因为我们是要将与DetailPage页面同级的HomePage的navigation属性传递给HomePage下面的子页面，而且我们只需要在子页面里面调用DetailPage所以只需要传递HomePage的navigation就可以了

在NavigationUtil拿到navigation之后，就需要将这个navigation传递给需要的方法，然后子组件在调用方法的时候引用的就会是HomePage的navigation：
打开NavigationUtil，然后找到goPage方法，在里面获取navigation的方法改为：
const navigation = NavigationUtil.navigation;
注意这里不能用this，因为this指向的是goPage这个函数，而我们的变量是存储在了NavigationUtil个类里面。
static goPage(page,params){
        // 跳转到指定页面
        const navigation = NavigationUtil.navigation;
        if(!navigation){
            console.log('NavigationUtil.navigation can not be null');
            return;
        }
        // 如果navigation不存在就打一个log并直接返回
        navigation.navigate(page,{...params})
        // 如果存在，那么传入打第一个参数就是路由也就是page，然后吧其他参数通过解构操作符直接传递进去
    }

（4）因为我们修改了goPage函数，所以需要在PopularPage中修改引用的地方：
<Button onPress={()=>{NavigationUtil.goPage('Detail',{})}} title={'跳转到详情页'} />

注意在这onPress里面要传递的是一个匿名函数，而不是直接传递NavigationUtil.goPage('Detail',{})}，如果这样直接传递那么只会在首次渲染的时候直接执行，就相当于一个表达式，和属性一样在渲染的时候只执行一次。就不会在每次点击onPress的时候触发了。所以要传递一个匿名函数，当点击的时候才会执行。


七、配置动态导航器
也就是可以更改的底部导航器和顶部导航器，包括导航器的内容和样式
7.1、配置HomePage动态的底部tab导航器
在AppNavigator文件夹下创建一个DynamicTabNavigator.js文件
用来存放动态导航器的相关配置
所以将HomePage整个页面的内容拷贝到DynamicTabNavigator.js文件里面，并修改class名，将它作为原始的导航器配置，并在他的基础上进行修改。

7.2、在运行项目的时候经常会报黄色的warning，我们可以使用js语法将warning给屏蔽掉：
在class类的constructor里面：
    constructor(props) {
        super(props);
        console.disableYellowBox = true;
    }

7.3、为了实现一个可以配置的导航，需要将封装好的私有的
 _bottomTab(){
        return createBottomTabNavigator({})}
里面的createBottomTabNavigator的配置项作为一个常量提到class类外面，然后根据需要去加载
const Tabs = {
    Favorite:{
        screen:FavoritePage,
        navigationOptions:{
            tabBarLabel:'收藏',
            tabBarIcon:({tintColor,focused})=>(
                <MaterialIcons name={'whatshot'} size={26} style={{color:tintColor}} />
            )
        }
    },
    My:{
        screen:MyPage,
        navigationOptions:{
            tabBarLabel:'我的',
            tabBarIcon:({tintColor,focused})=>(
                <EvilIcons name={'user'} size={26} style={{color:tintColor}} />
            )
        }
    },
    Popular:{
        screen:PopularPage,
        navigationOptions:{
            tabBarLabel:'热点',
            tabBarIcon:({tintColor,focused})=>(
                <MaterialIcons name={'favorite'} size={26} style={{color:tintColor}} />
            )
        }
    },
    Trending:{
        screen:TrendingPage,
        navigationOptions:{
            tabBarLabel:'趋势',
            tabBarIcon:({tintColor,focused})=>(
                <MaterialIcons name={'trending-up'} size={26} style={{color:tintColor}} />
            )
        }
    }
}
7.4、在类里面封装的私有方法里面提取将要显示的tab：
 _bottomTab(){
        const {Favorite,My,Popular,Trending} = Tabs
// 作用是将这些变量导入到 _bottomTab函数中，方便后面调用，然后我们还可以直接取出里面的某个值进行修改，如：Favorite.navigationOptions.tabBarLabel = '哈哈',这样就完成了动态配置tab的属性
        const tabs = {Favorite,My,Popular}
// 作用是将需要显示的tab集中在一起，方便一起导出，就不用一个一个使用了        
    }

7.5、将封装好的tabs使用createBottomTabNavigator进行处理，并return返回，方便被createBottomTabNavigator使用：
_bottomTab(){
        const {Favorite,My,Popular,Trending} = Tabs;
        const tabs = {Favorite,My,Popular};
        return createBottomTabNavigator(tabs)        
    }

7.6、在项目中使用tabs：
render() {        
        const AppContainer = createAppContainer(this._bottomTab())
        return <AppContainer/>
    }

7.7、在HomePage中使用我们封装好的DynamicTabNavigator.js：
因为我们在封装好的函数里面已经定义好了BottomTab，所以在HomePage中直接使用就可以了：
import DynamicTabNavigator from '../AppNavigator/DynamicTabNavigator'

render() {
        NavigationUtil.navigation = this.props.navigation        
        return <DynamicTabNavigator />
    }

这样我们通过修改DynamicTabNavigator.js中的tabs就可以控制bottomBar显示的个数样式等，而Tabs可以通过ajax传递进来，是可以通过服务器传入的。


核心就是将createBottomTabNavigator的配置项单独分离出来进行加工，然后再将加工好的传递回去这样就完成了定制

7.8、定制tab的样式
createBottomTabNavigator接收一个参数是tabBarComponent，tabBarComponent是一个组件，我们可以自定义这个组件，从而完成对tab的样式修改：
（1）首先定义一个class类，用来重新定义一个tabBarComponent组件

（2）首先在constructor函数中初始化一个属性theme，这个属性就是我们用来改造原始组件的属性：
tintColor: props.activeTintColor,

（3）又因为props属性经常会变，所以需要一个标志位，在这用时间作为标志位
this.theme={
            tintColor: props.activeTintColor,
            updateTime:new Date().getTime(),
        }

（4）在render方法中，我们需要用到一个react-navigation的基础组件BottomTabBar，react-navigation的bottomBar就是依赖react-navigation-tabs这个库里面的BottomTabBar这个组件完成的，我们因为需要修改style，所以需要修改这个组件来完成，所以先将这个组件导入进来，虽然react-navigation库中已经包含了BottomTabBar，但是我们在这直接使用了，还是重新安装一遍好，结构清晰：
npm i react-navigation-tabs -S
然后从这个库中导入BottomTabBar组件：
import {BottomTabBar} from 'react-navigation-tabs'

（5）我们要修改的话就是修改BottomTabBar的属性，所以在render函数中：
首先就是将它本来的属性集成过来，然后就是我们自定义的一些属性：
activeTinitColor={this.theme.tintColor || this.props.activeTintColor}
如果我们定义的them里面有自定义的颜色，那么就使用自定义的颜色，如果没有就是用它默认属性和属性值。

（6）将这个自定义的组件传递给createBottomTabNavigator的tabBarComponent属性：
return createBottomTabNavigator(tabs,{
            tabBarComponent:TabBarComponent
        })

（7）在项目中修改颜色：
