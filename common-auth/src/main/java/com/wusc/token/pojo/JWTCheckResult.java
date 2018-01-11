package com.wusc.token.pojo;

import io.jsonwebtoken.Claims;
public class JWTCheckResult {
	private Claims claims;
	private int StatusCode;

	public Claims getClaims() {
		return claims;
	}

	public void setClaims(Claims claims) {
		this.claims = claims;
	}

	public int getStatusCode() {
		return StatusCode;
	}

	public void setStatusCode(int statusCode) {
		StatusCode = statusCode;
	}
}
