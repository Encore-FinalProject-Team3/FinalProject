package com.encore.AI_Posturecoaching.category.controller;

import com.encore.AI_Posturecoaching.category.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {
    @InjectMocks CategoryController categoryController;
    @Mock
    CategoryService categoryService;


    @Test
    void readAll() {
    }

    @Test
    void create() {
    }

    @Test
    void delete() {
    }
}