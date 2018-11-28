#S2-001

1. https://vulhub.org/#/environments/struts2/s2-001/
2. https://xz.aliyun.com/t/2044


### 原理：
如果返回error，struts2会用拿input标签中的value去ognl的stack中查找（执行），从而执行了ognl表达式。