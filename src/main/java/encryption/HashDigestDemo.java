package encryption;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 对任意一组输入数据进行计算，得到一个固定长度的输出摘要
 *
 * @author guoyh
 */
public class HashDigestDemo {
    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //哈希算法最重要的特点就是：
        //相同的输入一定得到相同的输出；
        //不同的输入大概率得到不同的输出。

        //一个安全的哈希算法必须满足：
        //碰撞概率低；
        //不能猜测输出。

        //常用的哈希算法有：
        //算法	        输出长度（位）	    输出长度（字节）
        //MD5	        128 bits	    16 bytes
        //SHA-1	        160 bits	    20 bytes
        //RipeMD-160	160 bits	    20 bytes
        //SHA-256	    256 bits	    32 bytes
        //SHA-512	    512 bits	    64 bytes

        //Java标准库提供了常用的哈希算法，并且有一套统一的接口。我们以MD5算法为例，看看如何对输入计算哈希：
        //MD5
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update("Hello".getBytes(StandardCharsets.UTF_8));
        md5.update("World".getBytes(StandardCharsets.UTF_8));
        byte[] result = md5.digest();
        System.out.println(new BigInteger(1, result).toString(16));
        //使用MessageDigest时，我们首先根据哈希算法获取一个MessageDigest实例，然后，反复调用update(byte[])输入数据。
        //当输入结束后，调用digest()方法获得byte[]数组表示的摘要，最后，把它转换为十六进制的字符串。

        //哈希算法的另一个重要用途是存储用户口令。如果直接将用户的原始口令存放到数据库中，会产生极大的安全风险：
        //数据库管理员能够看到用户明文口令；
        //数据库数据一旦泄漏，黑客即可获取用户明文口令。
        //不存储用户的原始口令，那么如何对用户进行认证？
        //方法是存储用户口令的哈希，例如，MD5。
        //在用户输入原始口令后，系统计算用户输入的原始口令的MD5并与数据库存储的MD5对比，如果一致，说明口令正确，否则，口令错误。

        //使用哈希口令时，还要注意防止彩虹表攻击:
        //常用口令	MD5
        //hello123	f30aa7a662c728b7407c54ae6bfd27d1
        //12345678	25d55ad283aa400af464c76d713c07ad
        //passw0rd	bed128365216c019988915ed3add75fb
        //19700101	570da6d5277a646f6552b8832012f5dc
        //…	…
        //20201231	6879c0ae9117b50074ce0a0d4c843060
        //这个表就是彩虹表。如果用户使用了常用口令，黑客从MD5一下就能反查到原始口令：
        //bob的MD5：f30aa7a662c728b7407c54ae6bfd27d1，原始口令：hello123；
        //alice的MD5：25d55ad283aa400af464c76d713c07ad，原始口令：12345678；
        //tim的MD5：bed128365216c019988915ed3add75fb，原始口令：passw0rd。
        //这就是为什么不要使用常用密码，以及不要使用生日作为密码的原因。
        //即使用户使用了常用口令，我们也可以采取措施来抵御彩虹表攻击，方法是对每个口令额外添加随机数，这个方法称之为加盐（salt）：
        //digest = md5(salt+inputPassword)

        //SHA-1
        //SHA-1也是一种哈希算法，它的输出是160 bits，即20字节。SHA-1是由美国国家安全局开发的
        //SHA算法实际上是一个系列，包括SHA-0（已废弃）、SHA-1、SHA-256、SHA-512等。
        //在Java中使用SHA-1，和MD5完全一样，只需要把算法名称改为"SHA-1"
        //类似的，计算SHA-256，我们需要传入名称"SHA-256"，计算SHA-512，我们需要传入名称"SHA-512"。
        //Java标准库支持的所有哈希算法可以在查到。
        //https://docs.oracle.com/en/java/javase/14/docs/specs/security/standard-names.html#messagedigest-algorithms
        //注意：MD5因为输出长度较短，短时间内破解是可能的，目前已经不推荐使用。
    }
}
