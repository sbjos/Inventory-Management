package com.inventorymanagement.test.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.inventorymanagement.dao.CategoryDao;
import com.inventorymanagement.table.Category;
import com.inventorymanagement.test.TestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

// TODO: Review for unnecessary or missing test
public class CategoryDaoTest {
    private final Category food = TestHelper.food();
    private final Category electronic = TestHelper.electronic();
    @InjectMocks
    private CategoryDao categoryDao;
    @Mock
    private DynamoDBMapper dynamoDBMapper;
    @Mock
    private PaginatedQueryList<Category> paginatedQueryList;

    @BeforeEach
    // FIXME: Check what is that openMock "auto-closable" thing is.
    //  All other test class with @Mock has it.
    public void Setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void find_ReturnsItem() {
        // GIVEN
        when(dynamoDBMapper.load(Category.class, food.getCategoryName())).thenReturn(food);

        // WHEN
        Category result = categoryDao.find(food.getCategoryName());

        // THEN
        assertEquals(food, result);
    }

//    @Test
//    void findAll_ReturnsItem() {
//        // GIVEN
//        List<Category> existingItem = new ArrayList<>();
//        existingItem.add(food);
//        existingItem.add(electronic);
//
//        when(paginatedQueryList.iterator()).thenReturn(existingItem.iterator());
//        when(paginatedQueryList.size()).thenReturn(existingItem.size());
//        when(paginatedQueryList.isEmpty()).thenReturn(false);
//        when(paginatedQueryList.stream()).thenReturn(existingItem.stream());
//
//        when(categoryDao.findAll()).thenReturn(paginatedQueryList);
//
//        // WHEN
//        PaginatedQueryList<Category> result = categoryDao.findAll();
//
//        // THEN
//        System.out.println(result);
//        assertEquals(existingItem, result);
//    }
}
