package ru.pigarev.framework.base_test_class;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.pigarev.framework.managers.DriverManager;
import ru.pigarev.framework.managers.InitManager;
import ru.pigarev.framework.managers.PageManager;
import ru.pigarev.framework.managers.TestPropManager;
import ru.pigarev.framework.utils.PropConst;
import ru.pigarev.framework.utils.listeners.MyAllureListener;
import ru.pigarev.framework.utils.models.Item;

@ExtendWith(MyAllureListener.class)
public class BaseTests {

    private final DriverManager driverManager = DriverManager.getInstance();
    private final TestPropManager propManager = TestPropManager.getInstance();
    protected PageManager pageManager = PageManager.getInstance();
    

    protected Item product = new Item("6.1\" Смартфон Apple iPhone 13 128 ГБ черный", 0, 12, 0, 0);
    protected Item detroit = new Item("Игра Detroit: Стать человеком (PS4)");

    @BeforeAll
    public static void beforeAll() {
        InitManager.initFramework();
    }

    @BeforeEach
    public void beforeEach() {
        driverManager.getDriver().get(propManager.getProperty(PropConst.BASE_URL));
    }

//    @AfterEach
//    public void afterEach() {
//
//    }

    @AfterAll
    public static void afterAll() {
        InitManager.quitFramework();
        System.out.println("@AfterClass -> afterAll()\n");
    }

}
