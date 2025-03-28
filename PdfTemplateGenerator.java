import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.io.FileInputStream;
import java.security.KeyStore;

public class RestTemplateConfig {

    public static RestTemplate createRestTemplate() throws Exception {
        String keystorePath = "/path/to/keystore.jks"; // 🔹 Replace with your JKS path
        String keystorePassword = "your-keystore-password";

        String truststorePath = "/path/to/truststore.jks"; // 🔹 Replace with your TrustStore path
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

        // Create HttpClient with SSL
        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLContext(sslContext)
                .build();

        // Create RestTemplate with custom HttpClient
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
        return new RestTemplate(factory);
    }
}
