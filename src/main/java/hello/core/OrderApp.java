package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.order.Order;
import hello.core.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {

    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService(); // appConfig가 MemberService의 구현체를 주입해줌.
//        OrderService orderService = appConfig.orderService(); // appConfig가 OrderService의 구현체를 주입해줌.


        // Spring이 AppConfig에 있는 @Bean으로 등록해둔 객체 생성물들을 Spring 컨테이너에 집어넣어서 관리함.
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        // Spring 컨테이너인 ApplicationContext로 만든 객체를 통해 @Bean으로 등록된 메서드로 생성한 객체를 주입받음.
        // 결과적으로 AppConfig에서 직접 Memberservice 객체를 가져올 필요가 없어짐.
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        OrderService orderService = ac.getBean("orderService", OrderService.class);


        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 20000);
        System.out.println("order = " + order);
        System.out.println("order.calculatePrice() = " + order.calculatePrice());
    }
}
