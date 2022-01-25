package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

/**
 * 주문을 만들어 주는 역할만 해야됨.
 *
 * @return Order 객체
 */

/**
 * OCP 위반 : 확장에는 열려있고 변경에는 닫혀있어야함. ( 클라이언트 코드 변경 x)
 * 아래와 같이 할인 정책을 변경하려면
 * 클라이언트인 OrderServiceImpl의 코드를 수정해야된다.
 * -> OCP ( Open Closed Priciple ) 지키지 못한 것
 * OCP 원칙은 확장시 클라이언트에서 변경을 하면 안됨. -> 이는 Spring에서 해결할 수 있음.
 */

/**
 * DIP 위반 ( 구현클래스에 의존 x, 인터페이스에 의존해야됨 )
 * 현재 DiscountPolicy 라는 인터페이스뿐만 아니라
 * RateDiscountPolicy라는 구현체에도 의존 중
 */

public class OrderServiceImpl implements OrderService {

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); // 고정할인정책
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); // 새로운 할인 정책

    // interface에만 의존하고 있기때문에 DIP를 만족함.
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    /**다른 외부에서 interface인 MemberRepository와 DiscountPolicy에 구현체를 주입해준다.
     * 따라서 OrderServiceImpl 입장에서는 생성자를 통해 어떤 객체가 들어올지(주입될지)는 알 수 없다.
     * */
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository; // 어떤 객체가 주입되었는지는 모름
        this.discountPolicy = discountPolicy; //  어떤 객체가 주입되었는지는 모름
    }

    // OrderServiceImpl은 아래의 로직만 신경쓰면 된다.
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice); // OderServiceImpl은 그저 결과만 받는다.

        return new Order(memberId, itemName, itemPrice, discountPrice); // 주문

    }
}
