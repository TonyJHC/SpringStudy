package hello.core.member;


import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {
    MemberService memberService;

    @BeforeEach // Test가 실행되기전에 무조건 실행되는 것
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }


    @Test
    @DisplayName("회원가입에 성공하였습니다.")
    void join() {
        // given : ~이러한 환경이 주어졌을 때
        Member member = new Member(1L, "memberA", Grade.VIP);

        // when : 이렇게하면
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        // then : 이렇게 된다. (검증)
        Assertions.assertThat(member).isEqualTo(findMember);

    }
}
