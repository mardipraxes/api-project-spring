package mindswap.academy.app.utils;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 *
 * This class is meant to make a hash for journalist tokens
 * Current implementation is not secure, but it is enough for the current purpose
 *
 */

@Component
public class MHasher {

    private final int M_BYTE = "M".getBytes()[0];

    public String getToken(String username) {
        StringBuilder sb = new StringBuilder(username);
        sb.reverse();
        sb.append("ja_token");
        return sb.toString();
    }
//    public String hashString(String str) {
//        long currentTimeMillis = System.currentTimeMillis();
//        long monthMilliseconds = 2629800000L;
//        String expirationTime = String.valueOf(currentTimeMillis + monthMilliseconds);
//
//        long codedExpirationTime = Long.parseLong(expirationTime) / M_BYTE;
//
//        int random = Integer.parseInt(expirationTime.substring(expirationTime.length() - 3));
//
//        byte[] expTimeBytes = String.valueOf(codedExpirationTime).getBytes();
//
//        StringBuilder date = new StringBuilder();
//
//        for (int i = 0; i < expTimeBytes.length; i++) {
//            int iterations = 0;
//            long overflow = (long) ((int) expTimeBytes[i]) + ((long) M_BYTE ^ M_BYTE);
//
//            while(overflow > 154) {
//                overflow -= M_BYTE;
//                if(overflow <= 154) {
//                    System.out.println(overflow);
//                    expTimeBytes[i] = (byte) (overflow - M_BYTE);
//                    iterations++;
//                    break;
//                }
//                iterations++;
//            }
//            date.append(iterations);
//            date.append(".");
//            date.append(new String(expTimeBytes));
//        }
//
//        System.out.println("Date: " + date.toString());
//
//        StringBuilder sb = new StringBuilder();
//        sb.append(new String(expTimeBytes));
//        sb.append(":");
//
//        byte[] strBytes = str.getBytes();
//
//        for (int i = 0; i < strBytes.length; i++) {
//            if(strBytes[i] == M_BYTE) {
//                strBytes[i] = 0;
//            }
//            strBytes[i] = (byte) (strBytes[i] ^ M_BYTE - (7 ^ M_BYTE) + 1);
//            if(strBytes[i] == ' ') {
//                strBytes[i] = (byte)  M_BYTE;
//            }
//        }
//
//        sb.append(new String(strBytes));
//        return sb.toString();
//    }
//
//
//    public static void main(String[] args) {
//        MHasher hasher = new MHasher();
//        System.out.println(hasher.hashString("abcdefghijklmnoprstuzwxyz1234567890"));
//    }
}
