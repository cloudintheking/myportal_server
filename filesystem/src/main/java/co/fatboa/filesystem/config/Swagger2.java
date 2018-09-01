package co.fatboa.filesystem.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: hl
 * @Description: swagger配置类
 * @Date: 12:18 2018/8/26
 * @Modified By:
 * @Version 1.0
 */
@Configuration
@ConditionalOnProperty(prefix = "swagger", value = "enable", havingValue = "true") //havingvalue为期望值,期望值等于实际值时配置才生效
@EnableSwagger2
public class Swagger2 {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("文件系统API")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))    // 这里采用包含注解的方式来确定要显示的接口
                .apis(RequestHandlerSelectors.basePackage("co.fatboa.filesystem.controller"))        // 这里采用包扫描的方式来确定要显示的接口
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("hl", "https://cloudintheking.github.io", "huangkk@gmail.com");
        return new ApiInfoBuilder()
                .title("个人项目 - 文件系统API文档")
                .contact(contact)
                .version("1.0")
                .build();
    }
}
