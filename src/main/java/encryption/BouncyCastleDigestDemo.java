package encryption;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

/**
 * BouncyCastle
 *
 * @author guoyh
 */
public class BouncyCastleDigestDemo {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        //java标准库的java.security包提供了一种标准机制，允许第三方提供商无缝接入。
        //我们要使用BouncyCastle提供的RipeMD160算法，需要先把BouncyCastle注册一下：
        //注册只需要在启动时进行一次，后续就可以使用BouncyCastle提供的所有哈希算法和加密算法
        Security.addProvider(new BouncyCastleProvider());
        MessageDigest messageDigest = MessageDigest.getInstance("RipeMD160");
        messageDigest.update("HelloWorld".getBytes(StandardCharsets.UTF_8));
        byte[] result = messageDigest.digest();
        System.out.println(new BigInteger(1, result).toString(16));
    }
}
