package springBasic.core.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import springBasic.core.discount.DisCountPolicy;
import springBasic.core.member.Member;
import springBasic.core.member.MemberRepository;


@Component
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private final DisCountPolicy disCountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DisCountPolicy disCountPolicy){
        this.memberRepository = memberRepository;
        this.disCountPolicy = disCountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member findMember = memberRepository.findById(memberId);
        int discountPrice = disCountPolicy.disCount(findMember, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
