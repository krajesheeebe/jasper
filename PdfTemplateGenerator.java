import org.apache.hc.client5.http.config.ConnectionConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.apache.hc.core5.util.TimeValue;
import org.apache.hc.core5.util.Timeout;
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

        // ✅ 1. Load Keystore (Client Certificates)
        KeyStore keyStore = KeyStore.getInstance("JKS");
        try (FileInputStream keyStoreStream = new FileInputStream(keystorePath)) {
            keyStore.load(keyStoreStream, keystorePassword.toCharArray());
        }

        // ✅ 2. Load Truststore (Server CA Certificates)
        KeyStore trustStore = KeyStore.getInstance("JKS");
        try (FileInputStream trustStoreStream = new FileInputStream(truststorePath)) {
            trustStore.load(trustStoreStream, truststorePassword.toCharArray());
        }

        // ✅ 3. Build SSL Context with Keystore & Truststore
        SSLContext sslContext = SSLContextBuilder.create()
                .loadKeyMaterial(keyStore, keystorePassword.toCharArray()) // 🔹 For client authentication
                .loadTrustMaterial(trustStore, null) // 🔹 For trusting server certificates
                .build();

        // ✅ 4. Configure Connection Manager
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
        connManager.setDefaultConnectionConfig(
                ConnectionConfig.custom()
                        .setSocketTimeout(Timeout.ofSeconds(30)) // Socket read timeout
                        .setTimeToLive(TimeValue.ofMinutes(5)) // Connection TTL
