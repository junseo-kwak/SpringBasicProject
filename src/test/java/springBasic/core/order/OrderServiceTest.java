package springBasic.core.order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import springBasic.core.AppConfig;
import springBasic.core.member.Grade;
import springBasic.core.member.Member;
import springBasic.core.member.MemberService;
import springBasic.core.member.MemberServiceImpl;
import static org.assertj.core.api.Assertions.assertThat;

class OrderServiceTest {
    private  MemberService memberService;
    private  OrderService orderService;

    @BeforeEach
    void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void  createOrder(){
        Member member = new Member(1L, "test", Grade.VIP);
        memberService.join(member);
        Order order = orderService.createOrder(member.getId(), "itemA", 10000);

        assertThat(order.getDiscountAmount()).isEqualTo(1000);
    }
}