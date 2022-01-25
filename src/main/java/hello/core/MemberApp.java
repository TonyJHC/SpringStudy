package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {

//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService(); // appConfig에서 memberService 주입받기
//        MemberServiceImpl memberServiceImpl = new MemberServiceImpl()


        // Spring이 AppConfig에 있는 @Bean으로 등록해둔 객체 생성물들을 Spring 컨테이너에 집어넣어서 관리해준다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class); // Spring의 시작은 ApplicationContext로부터 시작된다. -> 이것이 Spring 컨테이너임
        // 이제는 위의 주석처리처럼 AppConfig에서 직접 MemberService 객체를 가져올 필요가 없다.
         MemberService memberService = applicationContext.getBean("memberService", MemberService.class);// memberService라는 객체를 찾아줘  , MemberService.class는 반환타입

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMeber = memberService.findMember(member.getId());
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMeber.getName());
    }
}
