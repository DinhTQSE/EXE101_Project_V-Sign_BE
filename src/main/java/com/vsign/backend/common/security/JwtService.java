package com.vsign.backend.common.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.Instant;
import java.util.Base64;
import java.util.Optional;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    private static final String HMAC_ALGO = "HmacSHA256";
    private final byte[] secretBytes;
    private final long expirationSeconds;

    public JwtService(
            @Value("${app.jwt.secret:vsign-task3-dev-secret}") String secret,
            @Value("${app.jwt.expiration-seconds:86400}") long expirationSeconds
    ) {
        this.secretBytes = secret.getBytes(StandardCharsets.UTF_8);
        this.expirationSeconds = expirationSeconds;
    }

    public String generateToken(String email) {
        return generateToken(email, "USER");
    }

    public String generateToken(String email, String role) {
        long expiresAt = Instant.now().plusSeconds(expirationSeconds).getEpochSecond();
        String normalizedRole = role == null || role.isBlank() ? "USER" : role.trim().toUpperCase();
        String payload = email + ":" + normalizedRole + ":" + expiresAt;
        String signature = base64UrlEncode(hmac(payload));
        String encodedPrincipal = base64UrlEncode((email + "|" + normalizedRole).getBytes(StandardCharsets.UTF_8));
        return encodedPrincipal + "." + expiresAt + "." + signature;
    }

    public Optional<String> extractEmailIfValid(String token) {
        return extractPrincipalIfValid(token).map(Principal::email);
    }

    public Optional<Principal> extractPrincipalIfValid(String token) {
        try {
            String[] parts = token.split("\\.");
            if (parts.length != 3) {
                return Optional.empty();
            }

            String decodedPrincipal = new String(Base64.getUrlDecoder().decode(parts[0]), StandardCharsets.UTF_8);
            String[] principalParts = decodedPrincipal.split("\\|");
            String email = principalParts[0];
            String role = principalParts.length > 1 ? principalParts[1].trim().toUpperCase() : "USER";
            long expiresAt = Long.parseLong(parts[1]);
            if (Instant.now().getEpochSecond() > expiresAt) {
                return Optional.empty();
            }

            String payload = email + ":" + role + ":" + expiresAt;
            byte[] expected = hmac(payload);
            byte[] provided = Base64.getUrlDecoder().decode(parts[2]);
            if (!MessageDigest.isEqual(expected, provided)) {
                return Optional.empty();
            }

            return Optional.of(new Principal(email, role));
        } catch (RuntimeException ex) {
            return Optional.empty();
        }
    }

    private byte[] hmac(String value) {
        try {
            Mac mac = Mac.getInstance(HMAC_ALGO);
            mac.init(new SecretKeySpec(secretBytes, HMAC_ALGO));
            return mac.doFinal(value.getBytes(StandardCharsets.UTF_8));
        } catch (Exception ex) {
            throw new IllegalStateException("Failed to calculate token signature", ex);
        }
    }

    private static String base64UrlEncode(byte[] value) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(value);
    }

    public record Principal(String email, String role) {
    }
}
