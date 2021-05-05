package encryption;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * @author guoyh
 */
public class HmacDigestDemo {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
        //哈希算法时，我们说，存储用户的哈希口令时，要加盐存储，目的就在于抵御彩虹表攻击。
        //digest = hash(salt + input)
        //这个salt可以看作是一个额外的“认证码”，同样的输入，不同的认证码，会产生不同的输出。因此，要验证输出的哈希，必须同时提供“认证码”。
        //Hmac算法就是一种基于密钥的消息认证码算法，它的全称是Hash-based Message Authentication Code，是一种更安全的消息摘要算法。
        //Hmac算法总是和某种哈希算法配合起来用的。例如，我们使用MD5算法，对应的就是HmacMD5算法，它相当于“加盐”的MD5：
        //HmacMD5 ≈ md5(secure_random_key, input)

        //HmacMD5可以看作带有一个安全的key的MD5。使用HmacMD5而不是用MD5加salt，有如下好处：
        //HmacMD5使用的key长度是64字节，更安全；
        //Hmac是标准算法，同样适用于SHA-1等其他哈希算法；
        //Hmac输出和原有的哈希算法长度一致。

        //为了保证安全，我们不会自己指定key，而是通过Java标准库的KeyGenerator生成一个安全的随机的key。下面是使用HmacMD5的代码：
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacMD5");
        SecretKey key = keyGenerator.generateKey();
        // 打印随机生成的key:
        byte[] bytes = key.getEncoded();
        System.out.println(new BigInteger(1, bytes).toString(16));

        Mac mac = Mac.getInstance("HmacMD5");
        mac.init(key);
        mac.update("HelloWorld".getBytes(StandardCharsets.UTF_8));
        byte[] result = mac.doFinal();
        System.out.println(new BigInteger(1, result).toString(16));
        //dbcc775e3715c10ab08b27c869decd72e8ef6f68fec8297721600a7dbdf310848d967f554c91d2dba5a2b195b3aee8255babda2abc214bd06e8f4cbe1df0112d
        //bf947f452bdecf875d13dc368fe3073a

        //和MD5相比，使用HmacMD5的步骤是：
        //通过名称HmacMD5获取KeyGenerator实例；
        //通过KeyGenerator创建一个SecretKey实例；
        //通过名称HmacMD5获取Mac实例；
        //用SecretKey初始化Mac实例；
        //对Mac实例反复调用update(byte[])输入数据；
        //调用Mac实例的doFinal()获取最终的哈希值。

        //我们可以用Hmac算法取代原有的自定义的加盐算法，因此，存储用户名和口令的数据库结构如下：
        //username	secret_key (64 bytes)	password
        //bob	    a8c06e05f92e...5e16	    7e0387872a57c85ef6dddbaa12f376de
        //alice	    e6a343693985...f4be	    c1f929ac2552642b302e739bc0cdbaac
        //tim	    f27a973dfdc0...6003	    af57651c3a8a73303515804d4af43790

        //想要验证怎么办？这时，SecretKey不能从KeyGenerator生成，而是从一个byte[]数组恢复：
        //恢复SecretKey的语句就是new SecretKeySpec(hkey, "HmacMD5")
        String password = "HelloWorld";
        String passwordSql = "bf947f452bdecf875d13dc368fe3073a";
        String stringKey = "dbcc775e3715c10ab08b27c869decd72e8ef6f68fec8297721600a7dbdf310848d967f554c91d2dba5a2b195b3aee8255babda2abc214bd06e8f4cbe1df0112d";
        byte[] keys = stringKey.getBytes(StandardCharsets.UTF_8);
        System.out.println(Arrays.toString(keys));

        SecretKey key1 = new SecretKeySpec(keys, "HmacMD5");
        Mac mac1 = Mac.getInstance("HmacMD5");
        mac1.init(key1);
        mac1.update(password.getBytes(StandardCharsets.UTF_8));
        result = mac1.doFinal();
        System.out.println(new BigInteger(1, result).toString(16));
    }
}
