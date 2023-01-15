package ua.ilyadreamix.amino.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HexFormat;
import java.util.Random;

public final class HashUtility {
    // Utilities
    private static final Random random = new Random();
    private static final HexFormat hexFormat = HexFormat.of();

    // String keys
    private static final String SIG_KEY = "DFA5ED192DDA6E88A12FE12130DC6206B1251E44";
    private static final String DEVICE_KEY = "E7309ECC0953C6FA60005B2765F99DBBC965C8E9";
    private static final String PREFIX = "19";

    // Bytes keys
    private static final byte[] SIG_KEY_BYTES = hexFormat.parseHex(SIG_KEY);
    private static final byte[] DEVICE_KEY_BYTES = hexFormat.parseHex(DEVICE_KEY);
    private static final byte[] PREFIX_BYTES = hexFormat.parseHex(PREFIX);

    /**
     * Generate device ID from given or random bytes
     *
     * @param data Bytes to generate device ID from
     * @return Device ID
     */
    public static String generateDeviceId(@Nullable byte[] data) {
        byte[] identifier;

        if (data == null) {
            byte[] tempIdentifier = new byte[20];
            random.nextBytes(tempIdentifier);
            identifier = ArrayUtils.addAll(PREFIX_BYTES, tempIdentifier);
        } else identifier = ArrayUtils.addAll(PREFIX_BYTES, data);

        String hmac = deviceHmac(identifier);

        return (Hex.encodeHexString(identifier) + hmac).toUpperCase();
    }

    /**
     * Generate signature from given bytes
     *
     * @param data Bytes to generate signature from
     * @return Signature
     */
    public static String generateSignature(@NotNull byte[] data) {
        byte[] hmac = signatureHmac(data);
        byte[] b64Payload = ArrayUtils.addAll(PREFIX_BYTES, hmac);

        return Base64.encodeBase64String(b64Payload);
    }

    /**
     * Generate HMAC SHA1
     *
     * @param data Data to generate HMAC from
     * @return HMAC SHA1 hex string representation
     */
    private static String deviceHmac(byte[] data) {
        HmacUtils hmacUtils = new HmacUtils(HmacAlgorithms.HMAC_SHA_1, HashUtility.DEVICE_KEY_BYTES);
        byte[] hmacDigest = hmacUtils.hmac(data);
        return Hex.encodeHexString(hmacDigest);
    }

    /**
     * Generate HMAC SHA1
     *
     * @param data Data to generate HMAC from
     * @return HMAC SHA1 byte array representation
     */
    private static byte[] signatureHmac(byte[] data) {
        HmacUtils hmac = new HmacUtils(HmacAlgorithms.HMAC_SHA_1, HashUtility.SIG_KEY_BYTES);
        return hmac.hmac(data);
    }
}
