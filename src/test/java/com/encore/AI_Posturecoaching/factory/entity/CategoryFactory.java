package com.encore.AI_Posturecoaching.factory.entity;

import com.encore.AI_Posturecoaching.category.Category;
import com.encore.AI_Posturecoaching.member.Member;
import org.springframework.test.util.ReflectionTestUtils;

public class CategoryFactory {
    public static Category createCategoryWithId(Long id) {
        Category category = new Category("email", null);
        ReflectionTestUtils.setField(category, "id", 3L);
        return category;
    }
}
