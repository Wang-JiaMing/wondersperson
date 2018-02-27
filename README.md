# wondersperson
>作者:小明同学
>时间：2018-02-27
###相关技术
1、spring<br/>
2、spring boot<br/>
3、spring security<br/>
4、spring data jpa<br/>
5、spring mvc<br/>
6、js、jq<br/>
7、bootstrap<br/>

### 快速部署方法
1、在oracle里建立WD_PERSON账号(账号密码均为WD_PERSON)

2、执行一下sql
````
insert into SYS_ROLE(id,name)values('1','ROLE_ADMIN');
insert into SYS_ROLE(id,name)values('2','ROLE_USER');
````

3、登录页面注册一个超级管理员（账号admin，密码自定义）

默认端口8080，可切换prod配置文件换成80端口
