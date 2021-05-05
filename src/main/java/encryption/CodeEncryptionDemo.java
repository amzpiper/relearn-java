package encryption;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

/**
 * @author guoyh
 */
public class CodeEncryptionDemo {
    public static void main(String[] args) throws UnsupportedEncodingException {
        //URL编码
        //出于兼容性考虑，很多服务器只识别ASCII字符。但如果URL中包含中文、日文这些非ASCII字符怎么办？不要紧，URL编码有一套规则：
        //如果字符是A~Z，a~z，0~9以及-、_、.、*，则保持不变；
        //如果是其他字符，先转换为UTF-8编码，然后对每个字节以%XX表示。
        //例如：字符中的UTF-8编码是0xe4b8ad，因此，它的URL编码是%E4%B8%AD。URL编码总是大写。
        //Java标准库提供了一个URLEncoder类来对任意字符串进行URL编码：
        String encode = URLEncoder.encode("中文！", String.valueOf(StandardCharsets.UTF_8));
        System.out.println(encode);
        //服务器收到URL编码的字符串，就可以对其进行解码，还原成原始字符串。Java标准库的URLDecoder就可以解码：
        String decode = URLDecoder.decode(encode, String.valueOf(StandardCharsets.UTF_8));
        System.out.println(decode);
        //特别注意：URL编码是编码算法，不是加密算法。URL编码的目的是把任意文本数据编码为%前缀表示的文本，
        //编码后的文本仅包含A~Z，a~z，0~9，-，_，.，*和%，便于浏览器和服务器处理。

        //Base64编码
        //URL编码是对字符进行编码，表示成%xx的形式，而Base64编码是对二进制数据进行编码，表示成文本格式
        //Base64编码可以把任意长度的二进制数据变为纯文本，且只包含A~Z、a~z、0~9、+、/、=这些字符。
        //它的原理是把3字节的二进制数据按6bit一组，用4个int整数表示，然后查表，把int整数用索引对应到字符，得到编码后的字符串。
        //举个例子：3个byte数据分别是e4、b8、ad，按6bit分组得到39、0b、22和2d
        //因为6位整数的范围总是0~63，所以，能用64个字符表示：字符A~Z对应索引0~25，字符a~z对应索引26~51，字符0~9对应索引52~61，最后两个索引62、63分别用字符+和/表示。
        //在Java中，二进制数据就是byte[]数组。Java标准库提供了Base64来对byte[]数组进行编解码：
        byte[] bytes = new byte[]{(byte) 0xe4, (byte) 0xb8, (byte) 0xad};
        String b64encode = Base64.getEncoder().encodeToString(bytes);
        System.out.println(b64encode);
        //要对Base64解码，仍然用Base64这个类
        bytes = Base64.getDecoder().decode(b64encode);
        System.out.println(Arrays.toString(bytes));
        //如果输入的byte[]数组长度不是3的整数倍肿么办？这种情况下，需要对输入的末尾补一个或两个0x00，
        //编码后，在结尾加一个=表示补充了1个0x00，加两个=表示补充了2个0x00，解码的时候，去掉末尾补充的一个或两个0x00即可。
        //实际上，因为编码后的长度加上=总是4的倍数，所以即使不加=也可以计算出原始输入的byte[]。
        //Base64编码的时候可以用withoutPadding()去掉=，解码出来的结果是一样的：
        byte[] input = new byte[] { (byte) 0xe4, (byte) 0xb8, (byte) 0xad, 0x21 };
        String b64encoded = Base64.getEncoder().encodeToString(input);
        String b64encoded2 = Base64.getEncoder().withoutPadding().encodeToString(input);
        System.out.println(b64encoded);
        System.out.println(b64encoded2);
        byte[] output = Base64.getDecoder().decode(b64encoded2);
        System.out.println(Arrays.toString(output));

        input = new byte[]{0x01, 0x02, 0x7f, 0x00};
        b64encoded = Base64.getUrlEncoder().encodeToString(input);
        System.out.println(b64encoded);
        output = Base64.getUrlDecoder().decode(b64encoded);
        System.out.println(Arrays.toString(output));
        //Base64编码的目的是把二进制数据变成文本格式，这样在很多文本中就可以处理二进制数据。
        //例如，电子邮件协议就是文本协议，如果要在电子邮件中添加一个二进制文件，就可以用Base64编码，然后以文本的形式传送。
        //Base64编码的缺点是传输效率会降低，因为它把原始数据的长度增加了1/3。
        //和URL编码一样，Base64编码是一种编码算法，不是加密算法。
        //如果把Base64的64个字符编码表换成32个、48个或者58个，就可以使用Base32编码，Base48编码和Base58编码。
        //字符越少，编码的效率就会越低。
    }
}
