package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;


// 객체의 생성과 연결은 AppConfig가 담당함.
// 역할(메소드 명)과 구현(return하는 객체)을 한눈에 볼 수 있다.
public class AppConfig {

    // 생성자 주입
    public MemberService memberService() {
        return new MemberServiceImpl(
                memberRepository()); // 역할이 들어나게끔 . 생성한 객체 인스턴스의 참조(레퍼런스)를 생성자를 통해서 주입(연결) 해줌.
    }

    private MemberRepository memberRepository() { // MemberRepository라는 역할에대한
        return new MemoryMemberRepository(); // 구현은 Memory로 할거임.
    }

    // 생성자 주입
    public OrderService orderService() { // OrderService라는 역할에 대한
        return new OrderServiceImpl( // 구현은 OrderServiceImpl로 할거임.
                memberRepository(),
                discountPolicy()); // 역할이 들어나게끔 . + 중복제외
    }

    public DiscountPolicy discountPolicy() { // DiscountPolicy라는 역할에대한
//        return new FixDiscountPolicy(); // 구현은 FixDiscount로 할거임.
        return new RateDiscountPolicy();
    }
}
