package guru.springframework.repositories.reactive;

import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UnitOfMeasureReactiveRepositoryTest {
    @Autowired
    UnitOfMeasureReactiveRepository measureReactiveRepository;

    @Before
    public void setUp() throws Exception {
        measureReactiveRepository.deleteAll().block();
    }

    @Test
    public void testUnitOfMeasureSave() {
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setDescription("UOM");
        measureReactiveRepository.save(unitOfMeasure).block();
        Long count = measureReactiveRepository.count().block();
        assertEquals(Long.valueOf(1L), count);
    }
}
