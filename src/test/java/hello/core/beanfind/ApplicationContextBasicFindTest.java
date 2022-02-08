package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class); // 인터페이스로 조회
//        System.out.println("memberService = " + memberService);
//        System.out.println("memberService.class = " + memberService.getClass());

        // 검증 : memberService가 MemberServiceImpl인지 알아보기
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("이름 없이 타입으로만 조회")
    void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class);
        // 검증 : memberService가 MemberServiceImpl인지 알아보기
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구현체 타입으로 조회")
    void findBeanByName2() {
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class); // 구현체 타입으로 조회
        // 검증 : memberService가 MemberServiceImpl인지 알아보기
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회x")
    void findBeanByNameX() {
//        MemberService xxxx = ac.getBean("xxxx", MemberService.class);
        // Junit에서 지원해주는 Assertions 사용
//        org.junit.jupiter.api.Assertions.assertThrows(NoSuchBeanDefinitionException.class,() ) -> 너무 길어서 import static 으로 올려버림

        assertThrows(NoSuchBeanDefinitionException.class, // 2. NoSuch... 와 같은 결과가 나와야한다.
                () -> ac.getBean("xxxxx", MemberService.class)); // 1. 해당 로직을 수행하면

    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 중복 오류가 발생한다.")
    void findBeanByTypeDuplicate() {

        assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(MemberRepository.class)); // 현재는 MemberRespository 타입이 하나뿐이라서 테스트가 실패됨.
    }




}

