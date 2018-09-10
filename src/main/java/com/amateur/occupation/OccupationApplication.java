package com.amateur.occupation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.filter.HttpPutFormContentFilter;


@SpringBootApplication
@EnableTransactionManagement
@Slf4j
public class OccupationApplication{

    public static void main(String[] args) {
        SpringApplication.run(OccupationApplication.class, args);
    }

//    /**
//     *
//     *
//     * @param registry
//     */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/webapp/**").addResourceLocations("classpath:/webapp/");
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new SessionInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/error")
//                .excludePathPatterns("/admin/login")
//                .excludePathPatterns("/webapp/static/ajax/**")
//                .excludePathPatterns("/webapp/static/css/**")
//                .excludePathPatterns("/webapp/static/fonts/**")
//                .excludePathPatterns("/webapp/static/img/**")
//                .excludePathPatterns("/webapp/static/js/**")
//                .excludePathPatterns("/webapp/login.html");
//    }
//
//    @Bean
//    public SessionInterceptor sessionInterceptor() {
//        return new SessionInterceptor();
//    }
//
    /**
     *
     *
     * @return
     */
    @Bean
    public HttpPutFormContentFilter httpPutFormContentFilter() {
        return new HttpPutFormContentFilter();
    }
//


}
