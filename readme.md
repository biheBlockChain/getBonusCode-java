### 软件下载

[https://github.com/biheBlockChain/getBonusCode-java/releases](https://github.com/biheBlockChain/getBonusCode-java/releases)

### 配置java8环境

[下载地址](https://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html)


![](https://i.loli.net/2018/10/24/5bd0759a75614.png)

如图，选中后，点击相应的版本下载安装


### 配置config.json

打开config.json文件,按照提示进行配置并保存

```
{
  "email": "iwrbug@gmail.com",
  "password": "xxx",
  "ruokuaiUsername": "xxx",
  "ruokuaiPassword": "xxx",
  "loopTimes": 5,
  "leadTimeMills": 20000,
  "thread": 10,
  "preCaptchaCount": 30
}

//email : 博纳云登录邮箱
//password: 博纳云登录密码
//ruokuaiUsername:  若快账号
//ruokuaiPassword: 若快密码
//loopTimes ： 每个线程抢购次数 ，建议 5
//leadTimeMills： 提前时间（毫秒）, 建议：20000
//thread: 线程数, 建议 10
//preCaptchaCount： 预抢购次数 （建议 线程数*3）
```

### 运行run.bat

![](https://i.loli.net/2018/10/24/5bd0764e1e23c.png)

出现上面的即为运行成功，等待抢码即可

关注公众号： **币合区块链**  获取更多

官方交流群： 490389116

![](https://mp.weixin.qq.com/mp/qrcode?scene=10000004&size=102&__biz=MzUyNzYzOTE1Mg==&mid=2247483718&idx=1&sn=39dbf5a7df237b6f5e87e8c555c85273&send_time=)