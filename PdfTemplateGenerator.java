import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.io.FileInputStream;
import java.security.KeyStore;

public class RestTemplateConfig {

    public static RestTemplate createRestTemplate() throws Exception {
        String keystorePath = "/path/to/keystore.jks"; // 🔹 Replace with actual JKS path
        String keystorePassword = "your-keystore-password";

        String truststorePath = "/path/to/truststore.jks"; // 🔹 Replace with actual TrustStore path
        String truststorePassword = "your-truststore-password";

        // Load Keystore (Client Certificates)
        KeyStore keyStore = KeyStore.getInstance("JKS");
        try (FileInputStream keyStoreStream = new FileInputStream(keystorePath)) {
            keyStore.load(keyStoreStream, keystorePassword.toCharArray());
        }

        // Load Truststore (Server CA Certificates)
        KeyStore trustStore = KeyStore.getInstance("JKS");
        try (FileInputStream trustStoreStream = new FileInputStream(truststorePath)) {
            trustStore.load(trustStoreStream, truststorePassword.toCharArray());
        }

        // Build SSL Context with KeyStore and TrustStore
        SSLContext sslContext = SSLContextBuilder.create()
                .loadKeyMaterial(keyStore, keystorePassword.toCharArray()) // 🔹 For client authentication
                .loadTrustMaterial(trustStore, null) // 🔹 For trusting server certificates
                .build();

        // Use Connection Manager with SSL
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
        connManager.setDefaultSocketConfig(org.apache.hc.core5.util.Timeout.ofMinutes(1));

        // Build HttpClient with SSL
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(connManager)
                .setConnectionManagerShared(true)
                .setSSLContext(sslContext)
                .build();

        // ✅ Correct constructor for HttpClient 5.x
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
        return new RestTemplate(factory);
    }
}
