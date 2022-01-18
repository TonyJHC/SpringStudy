package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemoryMemberRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
/**
 * import static 이란
 * 일반적인 import와는 다르게 메소드나 변수를 패키지, 클래스명 없이 접근가능하게 해줌.*/

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다.")
    void vip_o(){
        //given
        Member member = new Member(1L, "memberViP", Grade.VIP);

        //when
        int discount = discountPolicy.discount(member, 10000);

        //then
        assertThat(discount).isEqualTo(1000); // discount 의 결과값은 1000이여야 한다.

    }

    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다.")
    void vip_x(){
        //given
        Member member = new Member(2L, "memberBASIC", Grade.BASIC);

        // when
        int discount = discountPolicy.discount(member, 10000);

        // then
        assertThat(discount).isEqualTo(0);

    }

}