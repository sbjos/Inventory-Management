package test.service.item;

import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.inventorymanagement.controller.Controller;
import com.inventorymanagement.dao.CategoryDao;
import com.inventorymanagement.dao.ItemDao;
import com.inventorymanagement.dao.LocationDao;
import com.inventorymanagement.exception.CategoryNotFoundException;
import com.inventorymanagement.exception.InvalidAttributeException;
import com.inventorymanagement.exception.ItemAlreadyExistException;
import com.inventorymanagement.exception.LocationNotFoundException;
import com.inventorymanagement.result.ItemResult;
import com.inventorymanagement.service.item.CreateItemService;
import com.inventorymanagement.table.Category;
import com.inventorymanagement.table.Item;
import com.inventorymanagement.table.Location;
import com.inventorymanagement.test.TestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class CreateItemServiceTest {
    private final Item ramen = TestHelper.ramenNoodle();
    private final Item lettuce = TestHelper.lettuce();
    private final Category food = TestHelper.food();
    private final int availQuantity = 10;
    private final Location locationDF1 = TestHelper.locationDF1();
    @InjectMocks
    CreateItemService createItemService;
    @Mock
    private ItemDao itemDao;
    @Mock
    private CategoryDao categoryDao;
    @Mock
    private LocationDao locationDao;
    @Mock
    private PaginatedQueryList<Item> paginatedQueryList;
    private Item item;
    private Controller controller;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        item = new Item();
        item.setItemName(ramen.getItemName());
        item.setItemCategory(food.getCategoryName());
        item.setAvailability("Available");
        item.setItemQuantity(availQuantity);
        item.setItemLocation(locationDF1.getLocationName());
    }

    @Test
    void handleRequest_withValidName_returnResultAndAssignsItemId() {
        // GIVEN
        controller = Controller.builder()
                .withName(ramen.getItemName())
                .withCategory(food.getCategoryName())
                .withQuantity(availQuantity)
                .withLocation(locationDF1.getLocationName())
                .build();

        when(categoryDao.find(ramen.getItemCategory())).thenReturn(food);
        when(locationDao.find(ramen.getItemLocation())).thenReturn(locationDF1);

        // WHEN
        ItemResult result = createItemService.handleRequest(controller, null);

        // THEN
        assertEquals(item.getItemName(), result.getItem().getItemName());
        assertEquals(item.getItemCategory(), result.getItem().getItemCategory());
        assertEquals(item.getAvailability(), result.getItem().getAvailability());
        assertEquals(item.getItemQuantity(), result.getItem().getItemQuantity());
        assertEquals(item.getItemLocation(), result.getItem().getItemLocation());
        assertNotNull(result.getItem().getItemId());
    }

    @Test
    void handleRequest_withValidName_invalidLocation_returnLocationNotFoundException() {
        // GIVEN
        String invalid = "invalid";

        controller = Controller.builder()
                .withName(ramen.getItemName())
                .withCategory(food.getCategoryName())
                .withQuantity(availQuantity)
                .withLocation(invalid)
                .build();

        when(categoryDao.find(ramen.getItemCategory())).thenReturn(food);
        when(locationDao.find(ramen.getItemLocation())).thenReturn(locationDF1);

        // WHEN - THEN
        assertThrows(LocationNotFoundException.class, ()->
                createItemService.handleRequest(controller, null),
                (String.format("%s is not a valid location", invalid)));
    }

    @Test
    void handleRequest_withValidName_invalidCategory_returnCategoryNotFoundException() {
        // GIVEN
        String invalid = "invalid";

        controller = Controller.builder()
                .withName(ramen.getItemName())
                .withCategory(invalid)
                .withQuantity(availQuantity)
                .withLocation(ramen.getItemLocation())
                .build();

        when(categoryDao.find(ramen.getItemCategory())).thenReturn(food);
        when(locationDao.find(ramen.getItemLocation())).thenReturn(locationDF1);

        // WHEN - THEN
        assertThrows(CategoryNotFoundException.class, ()->
                        createItemService.handleRequest(controller, null),
                (String.format("%s is not a valid location", invalid)));
    }

    @Test
    void handleRequest_withInvalidName_returnInvalidAttributeException() {
        // GIVEN
        String empty = " ";
        controller = Controller.builder()
                .withName(empty)
                .withCategory(food.getCategoryName())
                .withQuantity(availQuantity)
                .withLocation(locationDF1.getLocationName())
                .build();

        // WHEN - // THEN
        assertThrows(InvalidAttributeException.class, () ->
                        createItemService.handleRequest(controller, null),
                "Please enter a valid item name.");
    }

    @Test
    void handleRequest_withQuantityLessThanOne_setsAvailabilityToUnavailable() {
        // GIVEN
        controller = Controller.builder()
                .withName(ramen.getItemName())
                .withCategory(food.getCategoryName())
                .withQuantity(0)
                .withLocation(locationDF1.getLocationName())
                .build();

        when(categoryDao.find(ramen.getItemCategory())).thenReturn(food);
        when(locationDao.find(ramen.getItemLocation())).thenReturn(locationDF1);
        when(categoryDao.find(food.getCategoryName())).thenReturn(food);

        // WHEN
        ItemResult result = createItemService.handleRequest(controller, null);

        // THEN
        assertEquals("Unavailable", result.getItem().getAvailability());
    }

    @Test
    void handleRequest_withExistingItemName_throwsItemAlreadyExistException() {
        // GIVEN
        controller = Controller.builder()
                .withName(ramen.getItemName())
                .withCategory(food.getCategoryName())
                .withQuantity(availQuantity)
                .withLocation(locationDF1.getLocationName())
                .build();

        when(categoryDao.find(ramen.getItemCategory())).thenReturn(food);
        when(locationDao.find(ramen.getItemLocation())).thenReturn(locationDF1);
        when(itemDao.find(ramen.getItemName())).thenReturn(ramen);

        // WHEN - THEN
        assertThrows(ItemAlreadyExistException.class, () ->
                createItemService.handleRequest(controller, null),
                (String.format("%s already exist. Please choose a different name.", controller.getName())));
    }

    @Test
    void handleRequest_looksForExistingItemId_ifMatchIsFound_assignsNewItemId() {
        // GIVEN
        List<Item> existingItem = new ArrayList<>();
        existingItem.add(ramen);
        existingItem.add(lettuce);

        controller = Controller
                .builder()
                .withName(ramen.getItemName())
                .withCategory(food.getCategoryName())
                .withQuantity(availQuantity)
                .withLocation(locationDF1.getLocationName())
                .build();

        when(paginatedQueryList.size()).thenReturn(existingItem.size());
        when(paginatedQueryList.isEmpty()).thenReturn(false);
        when(paginatedQueryList.iterator()).thenReturn(existingItem.iterator());
        when(paginatedQueryList.stream()).thenReturn(existingItem.stream());

        when(categoryDao.find(ramen.getItemCategory())).thenReturn(food);
        when(locationDao.find(ramen.getItemLocation())).thenReturn(locationDF1);
        when(itemDao.findById(ramen.getItemId())).thenReturn(paginatedQueryList);

        // WHEN
        ItemResult result = createItemService.handleRequest(controller, null);

        // THEN
        assertNotEquals(ramen.getItemId(), result.getItem().getItemId());
        assertNotEquals(lettuce.getItemId(), result.getItem().getItemId());
    }
}
