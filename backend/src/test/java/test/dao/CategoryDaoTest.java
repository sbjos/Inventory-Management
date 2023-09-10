package test.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.inventorymanagement.configuration.awsglobalsecondaryindex.AwsGsiCategory;
import com.inventorymanagement.dao.CategoryDao;
import com.inventorymanagement.table.Category;
import com.inventorymanagement.test.TestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class CategoryDaoTest {
    private final Category food = TestHelper.food();
    private final Category electronic = TestHelper.electronic();
    @InjectMocks
    private CategoryDao categoryDao;
    @Mock
    private DynamoDBMapper dynamoDBMapper;
    @Mock
    private AwsGsiCategory awsGsiCategory;
    @Mock
    private PaginatedScanList<Category> paginatedScanList;

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

    @Test
    void findAll_ReturnsItem() {
        // GIVEN
        List<Category> existingItem = new ArrayList<>();
        existingItem.add(food);
        existingItem.add(electronic);

        when(paginatedScanList.iterator()).thenReturn(existingItem.iterator());
        when(paginatedScanList.size()).thenReturn(existingItem.size());
        when(paginatedScanList.isEmpty()).thenReturn(false);
        when(paginatedScanList.stream()).thenReturn(existingItem.stream());

        when(categoryDao.findAll()).thenReturn(paginatedScanList);

        // WHEN
        PaginatedScanList<Category> result = categoryDao.findAll();

        // THEN
        assertNotNull(result);
    }
}
