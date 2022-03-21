package com.encore.AI_Posturecoaching;

import com.encore.AI_Posturecoaching.category.Category;
import com.encore.AI_Posturecoaching.category.repository.CategoryRepository;
import com.encore.AI_Posturecoaching.exception.CategoryNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static com.encore.AI_Posturecoaching.category.CategoryFactory.createCategory;
import static org.assertj.core.api.Assertions.assertThat;

public class CategoryRepositoryTest {
    @Autowired
    CategoryRepository categoryRepository;
    @PersistenceContext
    EntityManager em;

    @Test
    void createAndReadTest() {
        // given
        Category category = createCategory();

        System.out.println(category);
        // when
        Category savedCategory = categoryRepository.save(category);
        System.out.println("bb");
        em.flush();
        em.clear();

        // then
        Category foundCategory = categoryRepository.findById(savedCategory.getId()).orElseThrow(CategoryNotFoundException::new);
        assertThat(foundCategory.getId()).isEqualTo(savedCategory.getId());
    }

}
