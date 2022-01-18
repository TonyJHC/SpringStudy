package hello.core.member;


// 추상화에 의존 : MemberServiceImpl 은 MemberService interface에 의존함. -> DIP
public class MemberServiceImpl implements MemberService {  // 관례상 구현체가 하나일때는 Impl이라고 뒤에 붙이는 경우가 많다.

    // 구체화에도 의존 : 구현체에 의존하기 때문에 DIP 지키지 못함.
//    private final MemberRepository memberRepository = new MemoryMemberRepository();

    // MemberRepository라는 interface에 의존하기 때문에 DIP 지킬 수 있게 됨. ( MemberRepository인 추상화에만 의존 )
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Override
    public void join(Member member) {
        memberRepository.save(member); // 다형성에의해 MemoryMemberRepository의 save가 호출됨.
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }


}

