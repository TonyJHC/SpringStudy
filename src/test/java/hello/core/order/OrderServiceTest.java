package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig =new AppConfig();
        memberService = appConfig.memberService(); // 구현체 외부에서 주입 (의존관계 주입 )
        orderService = appConfig.orderService();
    }

    @Test
    @DisplayName("주문생성 완료.")
    void createOrder(){
        Long memberId = 1L;
        Member meber = new Member(memberId,"memberA", Grade.VIP);
        memberService.join(meber);

        Order order = orderService.createOrder(memberId,"itemA",10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
