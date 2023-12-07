package com.hellospring.study.repository;

import com.hellospring.study.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {



    private static Map<Long, Member> store = new HashMap<>();
    // 위와같은 경우가 실무일 경우에는 동시성문제때문에 Concurrent라는 변수를 사용해야한다
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {

        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Member> findByname(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    public void clearStore() {
        store.clear();
    }
}


