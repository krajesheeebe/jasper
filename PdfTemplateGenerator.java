import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.apache.hc.core5.ssl.TrustStrategy;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.cert.X509Certificate;

public class RestTemplateConfig {
    public static RestTemplate createRestTemplate() throws Exception {
        // Create an SSLContext that ignores all certificates
        SSLContext sslContext = SSLContextBuilder.create()
                .loadTrustMaterial((TrustStrategy) (X509Certificate[] chain, String authType) -> true)
                .build();

        // Create an HttpClient with the custom SSL context
        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLContext(sslContext)
                .build();

        // Create RestTemplate with the custom HttpClient
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
        return new RestTemplate(factory);
    }
}
