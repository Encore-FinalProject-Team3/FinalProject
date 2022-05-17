package com.encore.AI_Posturecoaching.category;

import com.encore.AI_Posturecoaching.category.repository.CategoryRepository;
import com.encore.AI_Posturecoaching.exception.CategoryNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static com.encore.AI_Posturecoaching.category.CategoryFactory.createCategory;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
public class CategoryRepositoryTest {
    @Autowired CategoryRepository categoryRepository;
    @PersistenceContext EntityManager pm;

    @Test
    void createAndReadTest(){
        // given
        Category category = createCategory();
        // when

        Category savedCategory = categoryRepository.save(category);
        pm.flush();
        pm.clear();

        // then
        Category foundCategory = categoryRepository.findById(savedCategory.getId()).orElseThrow(CategoryNotFoundException::new);
        assertThat(foundCategory.getId()).isEqualTo(savedCategory.getId());
    }

    @Test
    void deleteTest(){
        // given
        Category category = categoryRepository.save(createCategory());
        pm.flush();
        pm.clear();

        // when
        categoryRepository.delete(category);

        // then
        assertThatThrownBy(() -> categoryRepository.findById(category.getId()).orElseThrow(CategoryNotFoundException::new))
                .isInstanceOf(CategoryNotFoundException.class);
    }


}
