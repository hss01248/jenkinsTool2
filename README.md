# jenkins Android打包下载工具

![image-20211202180754333](https://cdn.jsdelivr.net/gh/hss01248/picbed@master/pic/1638439674379-image-20211202180754333.jpg)

示例

```java
JenkinsTool.init("http://host:port/job/项目名/api/json","你的jenkins用户名",  "你的apitoken");

 JenkinsTool.showBuildList(this);

```



### apk安装工具:

> 内部处理了:

* apk安装权限申请
* file uri 7.0 exposed兼容

```java
ApkInstallUtil.checkAndInstallApk(String filePath, InstallCallback callback)
```







## gradle依赖

```groovy

```

# 截图

![image-20220713173606529](https://cdn.jsdelivr.net/gh/shuiniuhss/myimages@main/imagemac2/1657704971797-image-20220713173606529.jpg)

一般只有一个包,那么点击就立刻下载

如果之前下载过,则直接发起安装.

![image-20220713173845487](https://cdn.jsdelivr.net/gh/shuiniuhss/myimages@main/imagemac2/1657705125517-image-20220713173845487.jpg)

aab加固时自动过滤apk供选择:

![image-20220713173706346](https://cdn.jsdelivr.net/gh/shuiniuhss/myimages@main/imagemac2/1657705026375-image-20220713173706346.jpg)

下载完后请求权限:

![image-20220713173801892](https://cdn.jsdelivr.net/gh/shuiniuhss/myimages@main/imagemac2/1657705081922-image-20220713173801892.jpg)

即使不给权限也发起安装,系统会帮忙兼容:

![image-20220713173823151](https://cdn.jsdelivr.net/gh/shuiniuhss/myimages@main/imagemac2/1657705103180-image-20220713173823151.jpg)