package csd230.lecture_2_1;

import com.github.javafaker.Commerce;
import com.github.javafaker.Faker;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional(propagation = Propagation.NOT_SUPPORTED)

public class ProductRepositoryTests {
//	@Autowired
//	private TestEntityManager entityManager;
    @Autowired
    private ProductRepository productRepository;

	@Test
	void testSave() {
		Faker faker = new Faker();
		Commerce cm = faker.commerce();
		com.github.javafaker.Number number = faker.number();
		com.github.javafaker.Book fakeBook = faker.book();
		String name = cm.productName();
		String description = cm.material();
		Product newProduct = new Product(name, description, number.randomDouble(2, 10, 100));

		//testEM.persistAndFlush(b1);
		productRepository.save(newProduct);

		Long savedProductID = newProduct.getId();

		Product product= productRepository.findById(savedProductID).orElseThrow();
		//Product book = testEM.find(Product.class, savedProductID);

		assertEquals(savedProductID, product.getId());
	}

}
