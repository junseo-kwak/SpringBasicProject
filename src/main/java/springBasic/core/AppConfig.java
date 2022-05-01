package springBasic.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springBasic.core.discount.FixDiscountPolicy;
import springBasic.core.member.MemberService;
import springBasic.core.member.MemberServiceImpl;
import springBasic.core.member.MemoryMemberRepository;
import springBasic.core.order.OrderService;
import springBasic.core.order.OrderServiceImpl;


@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService(){
          System.out.println("call AppConfig.memberService");
          return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public MemoryMemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }
    @Bean
    public OrderService orderService() {
         System.out.println("call AppConfig.orderService");
          return new OrderServiceImpl(memberRepository(), disCountPolicy());
    }

    @Bean
    public FixDiscountPolicy disCountPolicy() {
        return new FixDiscountPolicy();
    }



}
