package toy.yumyum.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import toy.yumyum.domain.Kind;

@Repository
@RequiredArgsConstructor
public class KindRepository {
	private final EntityManager em;

	public Kind save(Kind kind) {
		em.persist(kind);
		return kind;
	}

	public List<Kind> findAll() {
		return em.createQuery("select f From Kind f", Kind.class)
			.getResultList();
	}

	public Kind findOne(Long id) {
		return em.find(Kind.class, id);
	}

	public List<Kind> findByName(String name) {
		return em.createQuery("select k from Kind k where k.name like :name")
			.setParameter("name", name)
			.getResultList();
	}

	public void delete(Long id) {
		em.createQuery("delete from Kind k where k.id = :kindId")
			.setParameter("kindId", id)
			.executeUpdate();
	}
}
