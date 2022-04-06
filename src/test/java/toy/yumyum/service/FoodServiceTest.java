package toy.yumyum.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import toy.yumyum.domain.Food;
import toy.yumyum.domain.PetType;
import toy.yumyum.repository.FoodRepository;

@SpringBootTest
@Transactional
class FoodServiceTest {
	@Autowired
	FoodService foodService;
	@Autowired
	FoodRepository foodRepository;

	@Test
	@Rollback(false)
	public void 사료등록() throws Exception {
		//given
		Food food = Food.builder()
			.name("아카나 와일드 프레이리 캣")
			.ingredients("[{\"name\":\"닭고기\",\"concern\":\"1\"}, {\"name\":\"돼지고기\",\"concern\":\"1\"}]")
			.type(PetType.CAT)
			.build();

		// when
		Long foodId = foodService.saveFood(food);

		// then
		assertEquals(food, foodRepository.findOne(foodId));
	}

	@Test
	public void 사료중복체크() throws Exception {
		//given
		Food food1 = Food.builder()
			.name("아카나 와일드 프레이리 캣")
			.ingredients("[{\"name\":\"닭고기\",\"concern\":\"1\"}, {\"name\":\"돼지고기\",\"concern\":\"1\"}]")
			.type(PetType.CAT)
			.build();

		Food food2 = Food.builder()
			.name("아카나 와일드 프레이리 캣")
			.ingredients("[{\"name\":\"닭고기\",\"concern\":\"1\"}, {\"name\":\"돼지고기\",\"concern\":\"1\"}]")
			.type(PetType.CAT)
			.build();

		// when

		// then
		assertThatIllegalStateException().isThrownBy(() -> {
			foodService.saveFood(food1);
			foodService.saveFood(food2);
		});
	}

	@Test
	public void 사료명빈값체크() throws Exception {
		//given
		Food food = Food.builder()
			.ingredients("[{\"name\":\"닭고기\",\"concern\":\"1\"}, {\"name\":\"돼지고기\",\"concern\":\"1\"}]")
			.type(PetType.CAT)
			.build();

		// when

		// then
		assertThatIllegalStateException().isThrownBy(() -> {
			foodService.saveFood(food);
		});
	}

}