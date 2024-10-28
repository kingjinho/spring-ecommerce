package com.kingjinho.ecommerce.repository;

import com.kingjinho.ecommerce.domain.Member;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // 스프링 빈으로 등록. JPA 예외를 스프링 기반 예외로 예외 변환
@RequiredArgsConstructor
public class MemberRepository {

    //RequiredArgsConstructor를 사용했기에 @PersistentContext가 없음
    //스프링 부트(Spring Data JPA)가 자동으로 주입이 되게 지원하기에 가능한거지
    //아니라면 @PersistentContext를 사용해야함
    private final EntityManager em; //엔티티 매니저 주입

    public void save(Member member) {
        //member의 아이디는 항상 보장되어있음
        //db마다 전략이 다르지만, 스프링은 기본적으로 persist한다고 insert문이 날라가지는 않음
        //로그를 보면 persist한다고 insert 쿼리가 찍혀있지 않음
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    //sql은 테이블 대상으로 쿼리
    //jpql은 엔티티 대상으로 쿼리
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class) //jpql query
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

}
