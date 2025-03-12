package kr.co.moments.config;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan(basePackages = {"kr.co.moments"})
@EnableWebMvc
@MapperScan(basePackages = {"kr.co.moments"}, annotationClass = Mapper.class) // 인터페이스 스캔
@EnableTransactionManagement
public class MvcConfig implements WebMvcConfigurer{
	// DB접속정보
	@Value("${db.driver}")
	private String driver;
	@Value("${db.url}")
	private String url;
	@Value("${db.username}")
	private String username;
	@Value("${db.userpassword}")
	private String userpassword;
	
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/views/", ".jsp");
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/event/index");
	}
	
	// HikariCP
	@Bean(destroyMethod = "close")
	public HikariDataSource dataSource() {
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setDriverClassName(driver);
		dataSource.setJdbcUrl(url);
		
		dataSource.setUsername(username);
		dataSource.setPassword(userpassword);
		return dataSource;
	}

//	// Mybatis
//	@Bean
//	public SqlSessionFactory sqlSessionFactory() throws Exception {
//		SqlSessionFactoryBean ssf = new SqlSessionFactoryBean();
//		ssf.setDataSource(dataSource()); 
//		return ssf.getObject();
//	}
	// mybatis
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean ssf = new SqlSessionFactoryBean();
        ssf.setDataSource(dataSource());
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        ssf.setMapperLocations(resolver.getResources("classpath:/*/**.xml"));
        return ssf.getObject();
    }
	
	// 트랜잭션 설정
	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager dtm = new DataSourceTransactionManager();
		dtm.setDataSource(dataSource());
		return dtm;
	}

	// 프로퍼티 설정
	@Bean
	public static PropertySourcesPlaceholderConfigurer property() {
		PropertySourcesPlaceholderConfigurer property = new PropertySourcesPlaceholderConfigurer();
		property.setLocations(new ClassPathResource("db.properties"));
		return property;
	}
}

