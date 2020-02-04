package com.bridgelabz.springboot.utility;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;

@Component
public class Jwt {

	private long EXPIRATIONTIME = 1000 * 60 * 60; // 1 hr

	private static final String SECRET_KEY = "secret";

	public String createToken(String email) {
		// generate token using algorithm
		Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
		String token_key = JWT.create().withClaim("email", email)
				.withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATIONTIME)).sign(algorithm);
		return token_key;
	}

	public String getUserToken(String token) {
		Claim claim = JWT.require(Algorithm.HMAC256(SECRET_KEY)).build().verify(token).getClaim("email");
		System.out.println("claim " + claim);
		return claim.asString();

	}
}
