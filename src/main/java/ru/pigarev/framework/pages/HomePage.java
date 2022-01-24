package ru.pigarev.framework.pages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(HomePage.class);


    @FindBy(xpath = "//div[@class=\"presearch__suggests presearch__suggests_hidden\"]/../ input[@class=\"ui-input-search__input ui-input-search__input_presearch\"]")
    private WebElement searchLine;


@Step("Вводим в поисковое поле название искомого товара - '{item}")
    public SelectionItemPage findItemsOnSite(String item) {
        fillInputField(searchLine, item);
        searchLine.submit();
        Assertions.assertTrue(driverManager.getDriver().getTitle().contains(item), "Страница не загрузилась");
        logger.info("Выбран товар инфо ");
        logger.debug("Выбран товар дебаг ");
        logger.error("Выбран товар еррор ");
        return pageManager.getSelectionItemPage();
    }
}
