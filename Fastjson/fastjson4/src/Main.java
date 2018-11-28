import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.ParserConfig;
import org.apache.log4j.pattern.LogEvent;

public class Main {

    public static void main(String[] args) {


        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);


        String payload = "{\"@type\":\"User\", \"name\":\"hahha\",\"age\":\"666\", \"_test\":{}}";

        //System.out.println(payload);



        JSONObject obj = JSON.parseObject(payload);
        System.out.println(obj);
    }
}
