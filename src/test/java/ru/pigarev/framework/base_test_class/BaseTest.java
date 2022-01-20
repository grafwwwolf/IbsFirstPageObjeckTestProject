package ru.pigarev.framework.base_test_class;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import ru.pigarev.framework.managers.DriverManager;
import ru.pigarev.framework.managers.InitManager;
import ru.pigarev.framework.managers.PageManager;
import ru.pigarev.framework.managers.TestPropManager;
import ru.pigarev.framework.utils.PropConst;
import ru.pigarev.framework.utils.models.Item;

public class BaseTest {

    private final DriverManager driverManager = DriverManager.getInstance();
    private TestPropManager propManager = TestPropManager.getInstance();
    protected PageManager pageManager = PageManager.getInstance();

    protected Item product = new Item("6.1\" Смартфон Apple iPhone 13 128 ГБ черный", 0, 12, 0, 0);
    protected Item detroit = new Item("Игра Detroit: Стать человеком (PS4)");

    @BeforeAll
    public static void beforeAll() {
        InitManager.initFramework();
    }

    @BeforeEach
    public void beforeEach() {
//        String baseUrl = "https://dns-shop.ru";
//        driverManager.getDriver().get(baseUrl);
        driverManager.getDriver().get(propManager.getProperty(PropConst.BASE_URL));
    }

    @AfterEach
    public void afterEach() {
        InitManager.quitFramework();
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("@AfterClass -> afterAll()\n");
    }

}
