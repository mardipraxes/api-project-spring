package mindswap.academy.app.utils;

import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class AlgorithmUtil {


    private static final String secretCode = "christyronie420blazeit";



    public static Algorithm getAlgorithm() {
        return Algorithm.HMAC256(secretCode.getBytes());
    }


}
