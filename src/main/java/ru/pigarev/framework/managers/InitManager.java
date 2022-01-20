package ru.pigarev.framework.managers;

import ru.pigarev.framework.utils.PropConst;

import java.util.concurrent.TimeUnit;

public class InitManager {

    private static final DriverManager driverManager = DriverManager.getInstance();
    private static final TestPropManager props = TestPropManager.getInstance();

    public static void initFramework() {
        driverManager.getDriver().manage().window().maximize();
        driverManager.getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(props.getProperty(PropConst.IMPLICITLY_WAIT)), TimeUnit.SECONDS);
        driverManager.getDriver().manage().timeouts().pageLoadTimeout(Integer.parseInt(props.getProperty(PropConst.PAGE_LOAD_TIMEOUT)), TimeUnit.SECONDS);
    }

    public static void quitFramework() {
        driverManager.quitDriver();
    }
}
