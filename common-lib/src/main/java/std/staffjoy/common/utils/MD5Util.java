package std.staffjoy.common.utils;

import com.github.structlog4j.ILogger;
import com.github.structlog4j.SLoggerFactory;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import std.staffjoy.common.error.GlobalExceptionTranslator;

public class MD5Util {
  static final ILogger logger = SLoggerFactory.getLogger(GlobalExceptionTranslator.class);


  public static String hex(byte[] array) {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < array.length; ++i) {
      sb.append(Integer.toHexString((array[i]
          & 0xFF) | 0x100).substring(1, 3));
    }
    return sb.toString();
  }

  public static String md5Hex(String message) {
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      return hex(md.digest(message.getBytes("CP1252")));
    } catch (NoSuchAlgorithmException e) {
      logger.error("md5Hex error", e);
    } catch (UnsupportedEncodingException e) {
      logger.error("md5Hex error", e);
    }
    return null;
  }

}
