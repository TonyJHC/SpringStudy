package hello.core;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;


// 객체의 생성과 연결은 AppConfig가 담당함.
public class AppConfig {

    // 생성자 주입
    public MemberService memberService() {
        return new MemberServiceImpl(
                new MemoryMemberRepository()); // 생성한 객체 인스턴스의 참조(레퍼런스)를 생성자를 통해서 주입(연결) 해줌.
    }

    // 생성자 주입
    public OrderService orderService() {
        return new OrderServiceImpl(
                new MemoryMemberRepository(),
                new FixDiscountPolicy());
    }
}
