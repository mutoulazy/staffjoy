package std.staffjoy.common.crypto;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.springframework.util.StringUtils;
import std.staffjoy.common.error.ServiceException;

/**
 * 签名工具类
 */
public class Sign {

  public static final String CLAIM_EMAIL = "email";
  public static final String CLAIM_USER_ID = "userId";
  public static final String CLAIM_SUPPORT = "support";

  private static Map<String, JWTVerifier> verifierMap = new HashMap<>();
  private static Map<String, Algorithm> algorithmMap = new HashMap<>();


  private static Algorithm getAlgorithm(String signingToken) {
    Algorithm algorithm = algorithmMap.get(signingToken);
    if (algorithm == null) {
      synchronized (algorithmMap) {
        algorithm = algorithmMap.get(signingToken);
        if (algorithm == null) {
          algorithm = Algorithm.HMAC512(signingToken);
          algorithmMap.put(signingToken, algorithm);
        }
      }
    }
    return algorithm;
  }

  /**
   * 通过userId email signingToken 获取token 默认2小时过期
   */
  public static String generateEmailConfirmationToken(String userId, String email,
      String signingToken) {
    Algorithm algorithm = getAlgorithm(signingToken);
    String token = JWT.create().withClaim(CLAIM_EMAIL, email).withClaim(CLAIM_USER_ID, userId)
        .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(2)))
        .sign(algorithm);
    return token;
  }

  /**
   * userId support signingToken 获取token 过期时间由duration参数控制
   */
  public static String generateSessionToken(String userId, String signingToken, boolean support,
      long duration) {
    if (StringUtils.isEmpty(signingToken)) {
      throw new ServiceException("No signing token present");
    }
    Algorithm algorithm = getAlgorithm(signingToken);
    String token = JWT.create().withClaim(CLAIM_SUPPORT, support).withClaim(CLAIM_USER_ID, userId)
        .withExpiresAt(new Date(System.currentTimeMillis() + duration))
        .sign(algorithm);
    return token;
  }

  /**
   * 根据 tokenString signingToken 获取 DecodedJWT对象
   * @param tokenString
   * @param signingToken
   * @return
   */
  static DecodedJWT verifyToken(String tokenString, String signingToken) {
    JWTVerifier jwtVerifier = verifierMap.get(signingToken);
    if (jwtVerifier == null) {
      synchronized (verifierMap) {
        jwtVerifier = verifierMap.get(signingToken);
        if (jwtVerifier == null) {
          Algorithm algorithm = Algorithm.HMAC512(signingToken);
          jwtVerifier = JWT.require(algorithm).build();
          verifierMap.put(signingToken, jwtVerifier);
        }
      }
    }

    DecodedJWT jwt = jwtVerifier.verify(tokenString);
    return jwt;
  }

  public static DecodedJWT verifyEmailConfirmationToken(String tokenString, String signingToken) {
    return verifyToken(tokenString, signingToken);
  }

  public static DecodedJWT verifySessionToken(String tokenString, String signingToken) {
    return verifyToken(tokenString, signingToken);
  }
}
