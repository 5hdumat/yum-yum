package toy.yumyum.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import toy.yumyum.domain.Pet;

@Repository
@RequiredArgsConstructor
public class PetRepository {

	private final EntityManager em;

	public Pet save(Pet pet) {
		em.persist(pet);
		return pet;
	}

	public Pet findOne(Long id) {
		return em.find(Pet.class, id);
	}

	public List<Pet> findByMemberId(Long id) {
		return em.createQuery("select p from Pet p where p.member.id = :memberId", Pet.class)
			.setParameter("memberId", id)
			.getResultList();
	}

	public void delete(Long id) {
		em.createQuery("delete from Pet p where p.id = :petId")
			.setParameter("petId", id)
			.executeUpdate();
	}
}
