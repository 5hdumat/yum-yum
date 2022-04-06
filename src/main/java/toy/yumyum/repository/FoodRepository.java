package toy.yumyum.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import toy.yumyum.domain.Food;

@Repository
@RequiredArgsConstructor
public class FoodRepository {

	private final EntityManager em;

	public Food save(Food food) {
		em.persist(food);
		return food;
	}

	public List<Food> findAll() {
		return em.createQuery("select f From Food f", Food.class)
			.getResultList();
	}

	public Food findOne(Long id) {
		return em.find(Food.class, id);
	}

	public List<Food> findIds(List<Long> ids) {
		return em.createQuery("select f from Food f where f.id in :ids")
			.setParameter("ids", ids)
			.getResultList();
	}

	public List<Food> findByName(String name) {
		return em.createQuery("select f from Food f where f.name like :name")
			.setParameter("name", name)
			.getResultList();
	}

	public void delete(Long id) {
		em.createQuery("delete from Food f where f.id = :foodId")
			.setParameter("foodId", id)
			.executeUpdate();
	}
}
