package hello.login.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    public Member save(Member member){
        member.setId(++sequence);
        log.info("save : member={}",member);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id){
        return store.get(id);
    }

    public Optional<Member> findByLoginId(String loginId){
//        List<Member> all = findAll();
//        for (Member m : all) {
//            if(m.getLoginId().equals(loginId)){
//                return Optional.of(m);
//            }
//        }
//        return Optional.empty();

        return findAll().stream()
                .filter(m -> m.getLoginId().equals(loginId))
                .findFirst();
        //findAll로 데이터를 다 찾아서 stream 루프(반복)돌려서 람다식에 조건이 맞는것만 filter링을 해서 findFirst 첫번째로 나온것을 바로 return 반환 한다.
    }

    public List<Member> findAll(){
       return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }

}
