package kr.co.moments.api;

import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import kr.co.moments.domain.DailyPriceVO;
import kr.co.moments.domain.GoodsVO;
import kr.co.moments.goods.GoodsService;
import lombok.Data;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Data
@PropertySource("classpath:api.properties")
@Component
public final class OpenApiApplication {
	@Autowired
	GoodsService service;
	
    private final static String REQUEST_METHOD = "GET";
    private final static String DOMAIN = "https://api-gateway.coupang.com";
    private final static String URL = "/v2/providers/affiliate_open_api/apis/openapi/products/bestcategories/";
    private static final List<Integer> CATEGORY_IDS = Arrays.asList(
            1001,1002,1010,1011,1012,1013,1014,1015,1016,
            1017,1018,1019,1020,1021,1024,1025,1026,1029,1030);
    private final static String LIMIT = "?limit=100";
    
	//api접속정보
	@Value("${api.ACCESS_KEY}")
	private String ACCESS_KEY;
	@Value("${api.SECRET_KEY}")
	private String SECRET_KEY;

    public void sendApiRequest() throws IOException {

        for(int i=0; i<CATEGORY_IDS.size(); i++) {
	        // Generate HMAC string
	        String authorization = HmacGenerator.generate(REQUEST_METHOD, URL+CATEGORY_IDS.get(i)+LIMIT, SECRET_KEY, ACCESS_KEY);
	
	
	        org.apache.http.HttpHost host = org.apache.http.HttpHost.create(DOMAIN);
	        org.apache.http.HttpRequest request = org.apache.http.client.methods.RequestBuilder
	        		.get(URL+CATEGORY_IDS.get(i)+LIMIT)
	                .addHeader("Authorization", authorization)
	                .build();
	
	        org.apache.http.HttpResponse httpResponse = HttpClientBuilder.create().build().execute(host, request);
	        System.out.println(URL+CATEGORY_IDS.get(i)+LIMIT);
	        JSONObject jsonObject = new JSONObject(EntityUtils.toString(httpResponse.getEntity()));
	
	        // "data" 배열을 JSONArray로 변환
	        JSONArray dataArray = jsonObject.getJSONArray("data");
	        
	        // 변환된 JSONArray 출력
	        for (int j= 0; j < dataArray.length(); j++) {
	            JSONObject product = dataArray.getJSONObject(j);
	            String productId = String.valueOf(product.get("productId"));
	            
	            GoodsVO goodsVO = new GoodsVO();
	            DailyPriceVO dailyPriceVO = new DailyPriceVO();
	            goodsVO.setCategory_no(CATEGORY_IDS.get(i));
	            goodsVO.setGoods_name(product.getString("productName"));
	            goodsVO.setGoods_image(product.getString("productImage"));
	            goodsVO.setGoods_id(productId);
	            dailyPriceVO.setGoods_id(productId);
	            dailyPriceVO.setPrice(product.getInt("productPrice"));
	            
	            int resultGoods = service.insertGoods(goodsVO);
	            int resultPrice = service.insertPrice(dailyPriceVO);
	            
	            System.out.println("결과: " + resultGoods+" "+ resultPrice);
	        }
        }
    }

}