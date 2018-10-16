# WeChatOfficialAccount
基于SSM框架开发微信公众号
该项目主要是用来练习开发微信公众号的，使用SpringMVC、Spring、MyBatis框架构建项目，通过Maven管理jar包。
## 开发前你需要做的
- ### 申请测试账号
&emsp;&emsp;因为微信公众号的注册有一定的门槛，某些高级接口的权限需要微信认证后才可以获取，所以我申请的是测试号。申请成功后，在登录界面会有测试号的信息：
![image](https://github.com/JenkinWang/WeChatOfficialAccount/blob/master/images/%E5%BE%AE%E4%BF%A1%E5%85%AC%E4%BC%97%E5%B9%B3%E5%8F%B0%202018-10-09%2013-13-08.png)<br/>
- ### 服务器接口配置
&emsp;&emsp;公众号开发前需要对消息进行验证，是否来自于服务器，具体验证过程可以浏览微信公众号开发文档，官方也给出了代码示例。接下来就是接口信息配置，我使用的是一款内网穿透工具 [ngrok](https://ngrok.com/)，好处就是将本机地址映射到外网中，方便于测试使用：
![image](https://github.com/JenkinWang/WeChatOfficialAccount/blob/master/images/Screen%20Shot%202018-10-09%20at%2013.29.06.png)<br/>
![image](https://github.com/JenkinWang/WeChatOfficialAccount/blob/master/images/Screen%20Shot%202018-10-09%20at%2013.27.14.png)<br/>
&emsp;&emsp;token值应该与你代码中设置的token保持一致。填写好后提交，如果出现配置失败，多提交两三次，即可，如果还是失败，那就要检查你的代码了。到这里微信开发的基本配置算是弄好了。<br/>
- ### 设置access_token值
&emsp;&emsp;access_token是公众号的全局唯一接口调用凭据，公众号调用各接口时都需使用access_token。access_token的有效期目前为2个小时，需定时刷新，重复获取将导致上次获取的access_token失效。这里我使用了SpringMVC里的一个定时器，每两小时获取一次access_token的值存入数据库：
```java
    /**
     * 每两小时获取一次access_token值,并将access_token值存入数据库中
     */
    @Scheduled(cron = "0 0 */2 * * ?")
    public void getAccessToken() throws IOException {
        System.out.println("获取access_token值：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        AccessToken accessToken = CommonUtil.getAccessToken();
        System.out.println(accessToken.getAccess_token());
        System.out.println(accessToken.getExpires_in());

        // 将access_token存入数据库
        weChatService.insertAccessToken(accessToken);

    }
```
&emsp;&emsp;以及手动获取access_token的值：
```java
    /**
     * 手动获取access_token值: 如果当前时间与数据库里的最新access_token值的时间相差大于7200秒，则access_token过期，需要重新获取access_token值，并入库
     * @return
     * @throws IOException
     */
    public String queryLatestAccessToken() throws IOException {
        AccessToken accessToken = weChatService.queryLatestAccessToken();

        long curMillis = DateUtil.getCurrentMillis();
        long myCustomMillis = DateUtil.getCustomDateMillis(accessToken.getCreate_time());
        if ((curMillis - myCustomMillis) / 1000 >= 7200) {
            System.out.println("access_token值已过期，重新获取并入库......");
            accessToken = CommonUtil.getAccessToken();
            weChatService.insertAccessToken(accessToken);
        }

        return accessToken.getAccess_token();
    }
```
## 功能
&emsp;&emsp;可以对自定义菜单进行创建、删除、事件推送，创建、删除、查询个性化菜单；可以接收普通消息，接收事件推送、被动回复用户消息。
## 优点
&emsp;&emsp;使用SSM框架，对代码有很强的复用性和扩展性，并且很稳定。后续增加新的功能，可以直接在现有代码上进行二次开发。
## 交流一下
&emsp;&emsp;![image](https://github.com/JenkinWang/WeChatOfficialAccount/blob/master/images/qrcode_for_gh_7e1da67205e4_258.jpg)
