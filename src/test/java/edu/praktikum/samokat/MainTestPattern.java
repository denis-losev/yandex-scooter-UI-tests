package edu.praktikum.samokat;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

// Шаблон для тестов с Before и After
public abstract class MainTestPattern {
    WebDriver webdriver;
    private final String URL = "https://qa-scooter.praktikum-services.ru/";

    @Before
    public void startWebDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        webdriver = new ChromeDriver(options);
        webdriver.get(URL);
    }

    @After
    public void closeWebDriver() {
        webdriver.quit();
    }
}
