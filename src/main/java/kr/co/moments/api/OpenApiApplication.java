package kr.co.moments.api;

import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import lombok.Data;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

@Data
@PropertySource("classpath:api.properties")
@Component
public final class OpenApiApplication {
    private final static String REQUEST_METHOD = "POST";
    private final static String DOMAIN = "https://api-gateway.coupang.com";
    private final static String URL = "/v2/providers/affiliate_open_api/apis/openapi/v1/deeplink";
    
	//api접속정보
	@Value("${api.ACCESS_KEY}")
	private String ACCESS_KEY;
	@Value("${api.SECRET_KEY}")
	private String SECRET_KEY;

    

    private final static String REQUEST_JSON = "{\"coupangUrls\": [\"https://www.coupang.com/np/search?component=&q=good&channel=user\",\"https://www.coupang.com/np/coupangglobal\"]}";
   
    public String sendApiRequest() throws IOException {
        // Generate HMAC string
        String authorization = HmacGenerator.generate(REQUEST_METHOD, URL, SECRET_KEY, ACCESS_KEY);

        // Send request
        StringEntity entity = new StringEntity(REQUEST_JSON, "UTF-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");

        org.apache.http.HttpHost host = org.apache.http.HttpHost.create(DOMAIN);
        org.apache.http.HttpRequest request = org.apache.http.client.methods.RequestBuilder
                .post(URL).setEntity(entity)
                .addHeader("Authorization", authorization)
                .build();

        org.apache.http.HttpResponse httpResponse = HttpClientBuilder.create().build().execute(host, request);

        // Return response
        return EntityUtils.toString(httpResponse.getEntity());
    }
    

}