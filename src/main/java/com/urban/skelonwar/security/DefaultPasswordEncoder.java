package com.urban.skelonwar.security;

import com.urban.skelonwar.error.HashPasswordError;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by hernan.urban on 6/13/2017.
 */
public class DefaultPasswordEncoder implements PasswordEncoder {

    private static final int MASK = 0xFF;

    private String secret;

    public DefaultPasswordEncoder(String secret) {
        this.secret = secret;
    }

    @Override
    public String encode(CharSequence charSequence) {
        try {
            return hash(charSequence.toString());
        } catch (Exception e) {
            throw new HashPasswordError("Error creating hash");
        }
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        try {
            return s.equals(hash(charSequence.toString()));
        } catch (Exception e) {
            return false;
        }
    }

    private String hash(final String userPassword) throws Exception {
        String passwordHash = null;
        try {
            final MessageDigest sha = MessageDigest.getInstance("sha-256");
            sha.reset();
            byte[] list;
            try {
                final String saltedPassword = userPassword + ":" + secret;
                list = saltedPassword.getBytes("utf8");
                sha.update(list);
                final byte[] messageDigest = sha.digest();

                final StringBuffer hexString = new StringBuffer();
                for (int i = 0; i < messageDigest.length; i++) {
                    final String token = Integer.toHexString(MASK & messageDigest[i]);
                    // Make sure each is exactly 2 chars long
                    if (token.length() < 2) {
                        hexString.append("0");
                    }
                    hexString.append(token);
                }
                passwordHash = hexString.toString();
            } catch (final UnsupportedEncodingException e) {
                throw new HashPasswordError("Unsopported Encoding", e);
            }
        } catch (final NoSuchAlgorithmException e) {
            throw new HashPasswordError("No such algorithm", e);
        }

        return passwordHash;
    }
}
