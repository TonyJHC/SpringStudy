package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames(); // 스프링에 등록된 모든 빈 이름을 조회
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName); // Bean 이름으로 Bean 객체(인스턴스)를 조회한다.
            System.out.println("name=" + beanDefinitionName+" object="+ bean);

        }

    }
    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            // BeanDefinition : Bean 하나하나 정보(메타데이터)
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            // Bean의 메타데이터를 가지고 있는 BeanDefinition을 기반으로 애플리케이션 빈만 뽑아줌 ( 내가 등록한 빈만 출력해보기 )
            // Role ROLE_APPLICATION : 사용자가 직접 등록한 애플리케이션 빈
            // Role ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){ // 사용자가 직접 등록한 애플리케이션 빈
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name=" + beanDefinitionName+" object="+ bean);

            }
        }

    }
}

