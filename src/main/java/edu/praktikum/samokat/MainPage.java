package edu.praktikum.samokat;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

import java.time.Duration;

//Класс для главной страницей
public class MainPage {
    WebDriver webdriver;

    // Конструктор для главной страницы
    public MainPage(WebDriver webdriver) {
        this.webdriver = webdriver;
    }

    // Кнопка "Заказать" в шапке страницы
    private final By headerOrderBtn = By.xpath(".//div[@class='Header_Nav__AGCXC']/button[1]");

    // Кнопка "Заказать" в разделе "Как это работает"
    private final By bodyOrderBtn = By.xpath("//div[@class='Home_FinishButton__1_cWm']/button");

    // Блок FAQ в разделе "Вопросы о важном"
    private final By accordion = By.xpath("//div[@class='accordion']");

    // Блок с ответом в разделе "Вопросы о важном"
    private final By accordionAnswer = By.xpath(".//div[(@data-accordion-component='AccordionItemPanel' and not(@hidden))]");

    // Кнопка принятия куки
    private final By cookiesBtn = By.xpath(".//button[@class='App_CookieButton__3cvqF']");

    // Принять куки
    public void acceptCookies() {
        new WebDriverWait(webdriver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(cookiesBtn));
        webdriver.findElement(cookiesBtn).click();
    }

    // Нажатие на блок вопроса в разделе "Вопросы о важном"
    public void questionClick(String text) {
        ((JavascriptExecutor) webdriver).executeScript("arguments[0].scrollIntoView();",
                webdriver.findElement(accordion)); // Скроллим до блока
        try {
            webdriver.findElement(By.xpath(".//div[@data-accordion-component='AccordionItemButton' and contains(text(),'" + text + "')]")).click();
        } catch (Exception e) {
            System.out.println("На странице не найден элемент с текстом: " + text);
        }
    }

    // Проверка содержания текста ответа в разделе "Вопросы о важном"
    public void checkAnswersText(String answerParam) {
        new WebDriverWait(webdriver,
                Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(accordionAnswer));
        String equalResult = webdriver.findElement(accordionAnswer).getText();
        assertEquals(equalResult, answerParam);
    }

    // Нажатие кнопки "Заказать" в шапке страницы
    public UserInfoOrderPage headerOrderBtnClick() {
        webdriver.findElement(headerOrderBtn).click(); // Клик по кнопке "Заказать"
        return new UserInfoOrderPage(webdriver); // Возвращаем объект следующей страницы
    }

    // Нажатие кнопки "Заказать" в разделе "Как это работает"
    public UserInfoOrderPage bottomOrderBtnClick() {
        ((JavascriptExecutor) webdriver).executeScript("arguments[0].scrollIntoView();",
                webdriver.findElement(bodyOrderBtn)); // Скроллим до места расположения кнопки "Заказать"
        webdriver.findElement(bodyOrderBtn).click(); // Клик по кнопке "Заказать"
        return new UserInfoOrderPage(webdriver); // Возвращаем объект следующей страницы
    }
}
