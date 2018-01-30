import io.netty.handler.codec.base64.Base64Encoder;
import org.grimlock.uua.server.common.RSA;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.util.Base64;
import java.util.Map;

/**
 * @Author:chunlei.song@live.com
 * @Description:验证API接口安全问题，RSA等加密解密
 * @Date Create in 16:07 2018-1-30
 * @Modified By:
 */
public class TestOpenApiSec {

    public static void main(String[] args) throws Exception {

        /* 创建RSA公，私密钥
        Map<String,Object> keyMap = RSA.initKey();

        String publicKey = RSA.getPublicKey(keyMap);
        String privateKey = RSA.getPrivateKey(keyMap);
        System.out.println(publicKey);
        System.out.println(privateKey);
        */

        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCCTX7gxw/mI9SUmDSPG3l724sqOmVrxaZjC6Rx\n" +
                "JhCtT27MarnKb+JgHlb4HMYTWNQPpkQ/6EfnYoA82oZgX6SkY8DkIh+JSrW/a7GCXUPPwal9K/Tm\n" +
                "T8Xlz0JtePv9YXScBUBBbMClyW3z1c407ReQgB1eeR2XuM2bWlMFc8spCwIDAQAB";
        String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIJNfuDHD+Yj1JSYNI8beXvbiyo6\n" +
                "ZWvFpmMLpHEmEK1Pbsxqucpv4mAeVvgcxhNY1A+mRD/oR+digDzahmBfpKRjwOQiH4lKtb9rsYJd\n" +
                "Q8/BqX0r9OZPxeXPQm14+/1hdJwFQEFswKXJbfPVzjTtF5CAHV55HZe4zZtaUwVzyykLAgMBAAEC\n" +
                "gYASKJSO5I7Npp6gWpmwe8axQQYiy2KI41Ftqhivf/uA+3nYWOMtV+w3MuMxzmi4F3/t8mC+ezNS\n" +
                "BoMSd+2UDqAV6WBsyi/LldwmazrWajAjXPk7nTNLa631cY8y5ALaDBxsv1aptqcK8CykZIX283ZV\n" +
                "/ztHHmdt+hJkOMc2cMKkSQJBAM3FxsNUZZUNfy/Tsqfo07tSfNcZL5VDkhNwI7p9WPr3hF7n3Srv\n" +
                "BTcORgwXX95VToOorbFida5MSfGzKbEw4n0CQQCiG8sVnkT9uvef+7OX6H5ctlf4Rul2xwaUeh7S\n" +
                "75oyyaWZ+PKAasu7cyTbDgyLp0eh4eocZRj5JOTmIbAzs8gnAkB+5qbS1ZykFKPIehUm6jaRwqhM\n" +
                "+zJWKkrPBAx0uVMDy1vuL649CRU9Q+c9E7lC43c9SOx9hcwvhsrfbW+b4Br9AkBjdCe5AJuVCvLG\n" +
                "rPY2uUVk/d2kjWTIITyRNQbaJHN2uy2k9A1a6apKO08eMVVTDHKvB1nIx+F1YMQpx/tBDQg7AkEA\n" +
                "h8bGLJoDLIzojxGR86yKTBEi2nlSMy2o9xdOL897bzLMrWbjUJK9+1MINlFv3BaX9Ghb5lOxlA8B\n" +
                "xp9DaOteKg==";
        //公钥是对外的，有几个人都可以给
        //私钥是自己系统保留的，是用来解密的
        String inputStr = "123456";//被加密的字符串

        //加密
        byte[] data =  RSA.encryptByPublicKey(inputStr.getBytes(),publicKey);
        //用BASE64
        //为什么要用BASE64（用64个可见字符编码）? 因为HTTP内容是用BASE64传输的，这是编码方式
        String encodeData = new BASE64Encoder().encodeBuffer(data);
        System.out.println("加密前："+encodeData);

        //解密
        byte[] decodeData = RSA.decryptByPrivateKey(new BASE64Decoder().decodeBuffer(encodeData),privateKey);
        System.out.println("加密后："+new String(decodeData));








    }
}
