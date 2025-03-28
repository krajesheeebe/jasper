import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.apache.hc.core5.ssl.TrustStrategy;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;

public class RestTemplateConfig {
    
    public static RestTemplate createRestTemplate() throws Exception {
        String keystorePath = "/path/to/keystore.jks";  // Replace with actual JKS path
        String keystorePassword = "your-keystore-password";

        String truststorePath = "/path/to/truststore.jks"; // Replace with actual TrustStore path
        String truststorePassword = "your-truststore-password";

        // Load KeyStore (Client Certificates)
        KeyStore keyStore = KeyStore.getInstance("JKS");
        try (FileInputStream keyStoreInput = new FileInputStream(new File(keystorePath))) {
            keyStore.load(keyStoreInput, keystorePassword.toCharArray());
        }

        // Load TrustStore (CA Certificates)
        KeyStore trustStore = KeyStore.getInstance("JKS");
        try (FileInputStream trustStoreInput = new FileInputStream(new File(truststorePath))) {
            trustStore.load(trustStoreInput, truststorePassword.toCharArray());
        }

        // Create SSL Context
        SSLContext sslContext = SSLContextBuilder.create()
                .loadKeyMaterial(keyStore, keystorePassword.toCharArray()) // Client authentication
                .loadTrustMaterial(trustStore, (TrustStrategy) (chain, authType) -> true) // Truststore for server certs
                .build();

        // Create Connection Manager with SSL
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
        connManager.setDefaultSocketConfig(org.apache.hc.core5.util.Timeout.ofMinutes(1));

        // Create HttpClient
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(connManager)
                .setConnectionManagerShared(true)
                .setSSLContext(sslContext)
                .build();

        // Create RestTemplate
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
        return new RestTemplate(factory);
    }
}
