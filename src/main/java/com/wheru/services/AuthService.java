package com.wheru.services;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import io.jsonwebtoken.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;

import java.util.Arrays;
import java.util.Date;

import com.wheru.Exceptions.AuthException;
import com.wheru.Exceptions.DaoException;
import com.wheru.Exceptions.ParamNotFoundException;
import com.wheru.Exceptions.PasswordMismatchException;
import com.wheru.Exceptions.RecordNotFoundException;
import com.wheru.Exceptions.UserNotFoundException;
import com.wheru.dao.User;
import com.wheru.utilities.SecurityUtil;

public class AuthService extends BaseService {
	
	private static final AuthService authService = new AuthService();
	private String jwtSecretKey = null;

	private AuthService() {
		super();
		
		try {
			User jwtUser = User.get(new Long(1));
			jwtSecretKey = SecurityUtil.decryptDES(jwtUser.getEncryptedPassword(), jwtUser.getPasswordSalt());
		} catch(GeneralSecurityException gse) {
			// MCD TODO handle this better than just eating it
			//l.error("Problem decrypting JWT secret key: " + gse.getMessage(), gse);
			System.out.println("Problem decrypting JWT secret key: " + gse.getMessage());
		} catch(UnsupportedEncodingException uee) {
			// MCD TODO handle this better than just eating it
			//l.error("Problem decrypting JWT secret key: " + uee.getMessage(), uee);
			System.out.println("Problem decrypting JWT secret key: " + uee.getMessage());
		}
	} 
	
	public static AuthService instance() {
		return authService;
	}
	
	/**
	 * @param user
	 * @param password
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public void createUserPassword(User user, String password) 
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		user.setPasswordSalt(SecurityUtil.generateSalt());
		user.setEncryptedPassword(SecurityUtil.encryptPassword(password, user.getPasswordSalt()));
		
		user.save();
	}
	
	/**
	 * @param paramMap - the http request parameters
	 * @return String holding the new user token
	 * @throws ParamNotFoundException 
	 * @throws PasswordMismatchException
	 * @throws RecordNotFoundException 
	 * @throws AuthException 
	 */
	public String authenticateUser(String userEmail, String password) 
		throws PasswordMismatchException, UserNotFoundException, AuthException {
		String token = null;
		
		try {	
	    	
	    	User user = User.getByEmail(userEmail); 
	    	if (user == null) {
	    		throw new UserNotFoundException("Could not find user with email " + userEmail);
	    	}
			
			// Encrypt the clear-text password using the same salt that was used to
			// encrypt the original password
			byte[] encryptedAttemptedPassword = SecurityUtil.encryptPassword(password, user.getPasswordSalt());
	    	
			// Authentication succeeds if encrypted password that the user entered
			// is equal to the stored hash
			if (!Arrays.equals(user.getEncryptedPassword(), encryptedAttemptedPassword)) {
				throw new PasswordMismatchException("Password mismatch for user " + userEmail);
			}
			
			// MCD TODO implement refresh tokens so we don't need such a long timeout on auth token
			token = createJWT("wharu.com", userEmail, 1000 * 60 * 60);
			
		} catch (GeneralSecurityException gse) {
			throw new AuthException(gse, "Security error when authorizing " + userEmail + ": " + gse.getMessage());
		} catch (DaoException daoe) {
			throw new AuthException(daoe, "Database error when authorizing " + userEmail + ": " + daoe.getMessage());
		}
		
		return token;
	}

	/**
	 * @param issuer
	 * @param subject
	 * @param ttlMillis
	 * @return
	 */
	public String createJWT(String issuer, String subject, long ttlMillis) {
		 
	    //The JWT signature algorithm we will be using to sign the token
	    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
	 
	    long nowMillis = System.currentTimeMillis();
	    Date now = new Date(nowMillis);
	 
	    //We will sign our JWT with our ApiKey secret
	    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(jwtSecretKey);
	    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
	 
	    //Let's set the JWT Claims
	    JwtBuilder builder = Jwts.builder()
	    							.setIssuedAt(now)
	                                .setSubject(subject)
	                                .setIssuer(issuer)
	                                .signWith(signatureAlgorithm, signingKey);
	 
	    //if it has been specified, let's add the expiration
	    if (ttlMillis >= 0) {
	    long expMillis = nowMillis + ttlMillis;
	        Date exp = new Date(expMillis);
	        builder.setExpiration(exp);
	    }
	 
	    //Builds the JWT and serializes it to a compact, URL-safe string
	    return builder.compact();
	}

	// Sample method to validate and read the JWT
	/**
	 * @param jwt
	 */
	public void parseJWT(String jwt) {
		// This line will throw an exception if it is not a signed JWS (as
		// expected)
		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(jwtSecretKey.toString()))
				.parseClaimsJws(jwt).getBody();
		System.out.println("ID: " + claims.getId());
		System.out.println("Subject: " + claims.getSubject());
		System.out.println("Issuer: " + claims.getIssuer());
		System.out.println("Expiration: " + claims.getExpiration());
	}
}
