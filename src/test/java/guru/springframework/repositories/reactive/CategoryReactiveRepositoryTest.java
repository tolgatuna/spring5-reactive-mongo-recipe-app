package guru.springframework.repositories.reactive;

import guru.springframework.domain.Category;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CategoryReactiveRepositoryTest {

    @Autowired
    CategoryReactiveRepository categoryReactiveRepository;

    @Before
    public void setUp() throws Exception {
        categoryReactiveRepository.deleteAll().block();
    }

    @Test
    public void testCategorySave() {
        Category category = new Category();
        category.setDescription("BLA BLA");
        categoryReactiveRepository.save(category).block();
        Long count = categoryReactiveRepository.count().block();
        assertEquals(Long.valueOf(1L), count);
    }

    @Test
    public void testFindByDescription() {
        Category category = new Category();
        category.setDescription("FOO");
        categoryReactiveRepository.save(category).block();
        Category saved = categoryReactiveRepository.findByDescription("FOO").block();
        assertNotNull(saved.getId());
        assertEquals(saved.getDescription(), category.getDescription());
    }
}
