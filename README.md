# 概要
ABCL-L层提供基础模块的封装和抽象，主要为BaseActivity/BaseFragment/BaseAdapter/BaseHodler和一些框架必要的接口与工具类等。

# 使用配置
添加仓库，l_version=[![](https://jitpack.io/v/hslooooooool/abcl-l.svg)](https://jitpack.io/#hslooooooool/abcl-l)

```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    implementation 'com.github.hslooooooool:abcl-l:l_version'
}
```
引用后APP配置参考，其它配置参考项目根目录下base.gradle配置文件：
```
apply plugin: 'com.android.application'

apply plugin: 'kotlin-android'

apply plugin: 'kotlin-android-extensions'

android {
    compileSdkVersion 29
    buildToolsVersion "29.0.1"
    defaultConfig {
        applicationId "com.example.myapplication"
        minSdkVersion 17
        targetSdkVersion 29
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"

        // apk 方法数超过 65536 限制，进行分包处理
        multiDexEnabled true
    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
}

dependencies {
    api fileTree(dir: 'libs', include: ['*.jar'])

    api 'com.github.hslooooooool:abcl-l:l_version'
}
```

## 选型清单
- 网络请求OKHttp&Retrofit
- 请求控制Kotlin Coroutine
- 网络回调数据触发展示响应LiveData.postValue
- 网络回调数据触发动作响应Function.()->Unit
- 官方组件LifeCycle&LiveData&ViewMode&ROOM
- 页面路由ARouter
- 日志工具Timber
- 事件总线LiveDataBus&EventBus

> 关于LiveData：若View在不处于活动状态时IO线程多次变更LiveData，当View重新处于活动状态时将收到最后一条对LiveData的变化推送，不会像消息队列一样重新发送未到达的消息，因此规定在LiveData.Observe中仅用于数据展示变化，不进行逻辑处理，否者可能导致View.stop->View.resume时LiveData.Observe重复执行，此前项目中由于对观察者的滥用，导致某个接口重复调用，才发现此问题，重点强调一下，避免新入手LiveData的朋友出现我这个问题。现在框架引入了kotlin协程，并注重了这方面的检查，避免这个问题的再次发生。

## 模块设计

### 1层-数据结构与常量管理模块（仅开发时。推荐在你的项目最底层引用abcl-l。）
数据结构作为系统设计的最重要一环，承载了后续接口设计、逻辑处理等工作的开展，所以作为最底层依赖，供所有模块使用。

常量管理包括路由地址管理、全局常量管理、配置、通用实体类等。

### 2层-基础架构模块(abcl-l)
基础架构定义项目后续研发的基准开发与编码方式，如MVC/MVP/MVVM的开发方式，尽可能的统一团队的开发规范。

包括以下几点：
- BaseActivity/BaseFragment/BaseAdapter/BaseHolder等抽象实现
- 各场景下的逻辑处理标准——抽象实现

### 2层-独立功能模块(abcl-c)
独立功能模块涵盖所有可单独实现的功能，如以下功能：
- 网络请求-OkHttp/Retrofit/RxJava/Coroutines
- 图片加载-Glide封装
- 异常捕获-Timber封装
- 动态表单
- 动态流程
- 异常捕获
- 日志系统-Timber封装
- 埋点统计
- 缓存管理
- 文件上传与下载，断点续传
- 文件选择（拍照、录像、录音、文件）
- 文件解压缩、读写工具
- web容器
- JsBridge调用

### 3层-基础业务模块(abcl-b)
基础业务架构定义项目领域类业务的统一处理逻辑与实现，如登录、注册、聊天、流程模板与管理等，以及各类业务的通用处理逻辑。

### 3层-独立业务模块(abcl-b)
独立业务模块为具体业务的唯一实现，如个人中心中对用户个人进行的相关业务的集合（登录、注册、密码找回/修改、个人信息更新、IM系统等）。

### 4层-Module壳(abcl-a)
作为独立模块的壳工程，使Module可独立打包运行并进行测试，这里就不使用传统的模块化gradle配置方式了，直接采用新建application module模块来实现对独立业务的demo撰写。

每一个Module壳工程理应在创建独立业务模块的时候自动生成，减少工作量。

可开发插件，在创建abcl-b基础业务模块的时候自动生成。

### 4层-APP壳(abcl-a)
打包、混淆配置、进行工程初始化操作、配置等。

# 参考
[有赞商城-Android组件化方案](https://tech.youzan.com/you-zan-yi-dong-androidzu-jian-hua-fang-an/)

# 相关
- [ABCL安卓快速开发框架](https://github.com/hslooooooool/abcl)
- [ABCL安卓快速开发框架之L层](https://github.com/hslooooooool/abcl-l)
- [ABCL安卓快速开发框架之C层](https://github.com/hslooooooool/abcl-c)
- [ABCL安卓快速开发框架之B层](https://github.com/hslooooooool/abcl-b)
- [ABCL测试使用的后台代码](https://github.com/hslooooooool/ktorm-demo)