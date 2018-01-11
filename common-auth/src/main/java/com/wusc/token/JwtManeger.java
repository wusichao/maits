package com.wusc.token;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wusc.token.pojo.AccountPOJO;
import com.wusc.token.pojo.JWTCheckResult;
import io.jsonwebtoken.*;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

public class JwtManeger {
	private String jwtId;
	private long ttlMillis;
	/**
	 * 由字符串生成加密key
	 * @return
	 */
	public SecretKey generalKey(){
		String stringKey = Constant.JWT_SECRET;
		byte[] encodedKey = Base64.decodeBase64(stringKey);
	    SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
	    return key;
	}

	/**
	 * 创建jwt
	 * @param subject
	 * @return
	 * @throws Exception
	 */
	public String createJWT( String subject){
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		SecretKey key = generalKey();
		JwtBuilder builder = Jwts.builder()
			.setId(jwtId)
			.setIssuedAt(now)
			.setSubject(subject)
		    .signWith(signatureAlgorithm, key);
		if (ttlMillis >= 0) {
		    long expMillis = nowMillis + ttlMillis;
		    Date exp = new Date(expMillis);
		    builder.setExpiration(exp);
		}
		return builder.compact();
	}
	
	/**
     *  验证JWT
     * @param token
     * @return
     */
    public JWTCheckResult validateJWT(String token) {
    	JWTCheckResult checkResult = new JWTCheckResult();
        Claims claims = null;
        try {
            claims = parseJWT(token);
            checkResult.setStatusCode(Constant.JWT_VERIFY_SUCCESS);
            checkResult.setClaims(claims);
        } catch (ExpiredJwtException e) {
        	checkResult.setStatusCode(Constant.JWT_TOKEN_EXPIRE);
        } catch (SignatureException e) {
        	checkResult.setStatusCode(Constant.JWT_TOKEN_INVALID);
        } catch (Exception e) {
        	checkResult.setStatusCode(Constant.JWT_VERIFY_ERROR);
        }
        return checkResult;
    }
	/**
	 * 解密jwt
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public Claims parseJWT(String token) throws Exception{
		SecretKey key = generalKey();
		Claims claims = Jwts.parser()         
		   .setSigningKey(key)
		   .parseClaimsJws(token).getBody();
		return claims;
	}
}
