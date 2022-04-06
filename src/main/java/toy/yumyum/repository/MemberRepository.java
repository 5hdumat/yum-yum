package toy.yumyum.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import toy.yumyum.domain.Member;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

	private final EntityManager em;

	public Member save(Member member) {
		em.persist(member);
		return member;
	}

	public List<Member> findByEmail(String email) {
		return em.createQuery("select m from Member m where m.email = :email")
			.setParameter("email", email)
			.getResultList();
	}

	public Member findOne(Long id) {
		return em.find(Member.class, id);
	}

}
