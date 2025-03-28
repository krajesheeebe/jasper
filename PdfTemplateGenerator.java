import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.security.KeyStore;

public class RestTemplateConfig {
    
    public static RestTemplate createRestTemplate() throws Exception {
        String keystorePath = "/path/to/keystore.jks";  // Replace with actual JKS path
        String keystorePassword = "your-keystore-password";

        String truststorePath = "/path/to/truststore.jks"; // Replace with actual TrustStore path
        String truststorePassword = "your-truststore-password";

        // Load KeyStore (Client Certs)
        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(new java.io.FileInputStream(new File(keystorePath)), keystorePassword.toCharArray());

        // Load TrustStore (CA Certs)
        KeyStore trustStore = KeyStore.getInstance("JKS");
        trustStore.load(new java.io.FileInputStream(new File(truststorePath)), truststorePassword.toCharArray());

        // Create SSL Context using both KeyStore and TrustStore
        SSLContext sslContext = SSLContextBuilder.create()
                .loadKeyMaterial(keyStore, keystorePassword.toCharArray()) // Client certificates
                .loadTrustMaterial(trustStore, null) // Truststore for verifying server certs
                .build();

        // Create HTTP Client with SSL
        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(new SSLConnectionSocketFactory(sslContext))
                .build();

        // Create RestTemplate with custom SSL
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
        return new RestTemplate(factory);
    }
}
