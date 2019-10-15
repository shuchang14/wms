package com.shu.wms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @EnableEurekaClient  将当前服务注册到eureka上
 */
@EnableFeignClients
@EnableEurekaClient
@EnableCaching
@SpringBootApplication
public class BaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class, args);
    }

    /**
     * 跨域请求配置  liangsc
     * @return
     */
  /*  private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
      //  corsConfiguration.addAllowedOrigin("http://localhost:8280");//允许请求的域
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod(HttpMethod.GET);//请求方法
        corsConfiguration.addAllowedMethod(HttpMethod.POST);
        //corsConfiguration.setAllowCredentials(true);//是否发送cookie
        //corsConfiguration.addExposedHeader("");
        return corsConfiguration;
    }*/

    /**
     * 跨域过滤器  liangsc
     *
     * @return
     */
  /*  @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig()); // 4
        return new CorsFilter(source);
    }*/
}
