package hello.core;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class),
        basePackages =  "hello.core.member" // 지정 패키지 하위만 탐색
)
// @ComponentScan 이란 @Component annotation 이 붙은 클래스를 모두 찾아 bean 등록 해줌

public class AutoAppConfig {


}
