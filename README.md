# wondersperson
### 快速部署方法
1、在oracle里建立WD_PERSON账号(账号密码均为WD_PERSON)
2、执行一下sql
````
insert into SYS_ROLE(id,name)values('1','ROLE_ADMIN');
insert into SYS_ROLE(id,name)values('2','ROLE_USER');
````
3、新建一个超级管理员（账号admin，密码自定义）


默认端口8080，可切换prod配置文件换成80端口