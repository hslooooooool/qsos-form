#保留行号
-keepattributes SourceFile,LineNumberTable
#包名不混合大小写
-dontusemixedcaseclassnames
#不跳过非公共的库的类
-dontskipnonpubliclibraryclasses
#混淆时记录日志
-verbose
#关闭预校验
-dontpreverify
#不优化输入的类文件
-dontoptimize
#保护注解
-keepattributes *Annotation*
#保持所有拥有本地方法的类名及本地方法名
-keepclasseswithmembernames class * {
    native <methods>;
}
#保持自定义View的get和set相关方法
-keepclassmembers public class * extends android.view.View {
    void set*(***);
    *** get*();
}
#保持Activity中View及其子类入参的方法
-keepclassmembers class * extends android.app.Activity {
    public void *(android.view.View);
}
#枚举
-keepclassmembers enum * {
    **[] $VALUES;
    public *;
}
#R文件的静态成员
-keepclassmembers class **.R$* {
    public static <fields>;
}
-dontwarn android.support.**
#keep相关注解
-keep class android.support.annotation.Keep
-keep @android.support.annotation.Keep class * {*;}
-keepclasseswithmembers class * {
    @android.support.annotation.Keep <methods>;
}
-keepclasseswithmembers class * {
    @android.support.annotation.Keep <fields>;
}
-keepclasseswithmembers class * {
    @android.support.annotation.Keep <recycle>(...);
}

#OKHTTP
-dontwarn com.squareup.okhttp3.**
-keep class com.squareup.okhttp3.** { *;}
-dontwarn okio.**
-dontwarn okio.**
-dontwarn javax.annotation.Nullable
-dontwarn javax.annotation.ParametersAreNonnullByDefault

# Arouter
-keep public class com.alibaba.android.arouter.routes.**{*;}
-keep class * implements com.alibaba.android.arouter.facade.template.ISyringe{*;}
# 如果使用了 byType 的方式获取 Service，需添加下面规则，保护接口
-keep interface * implements com.alibaba.android.arouter.facade.template.IProvider
# 如果使用了 单类注入，即不定义接口实现 IProvider，需添加下面规则，保护实现
-keep class * implements com.alibaba.android.arouter.facade.template.IProvider