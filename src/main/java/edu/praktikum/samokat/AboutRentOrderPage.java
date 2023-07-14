package edu.praktikum.samokat;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

public class AboutRentOrderPage {
    // Поле ввода даты доставки
    private final By DELIVERY_DATE = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    // Поле ввода комментария
    private final By DESCRIPTION = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    // Поле выбора срока аренды
    private final By RENTAL_PERIOD = By.xpath(".//div[@class='Dropdown-root']");
    // Кнопка "Заказать"
    private final By NEXT_BTN = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");
    Random random = new Random();
    private final int SEVEN_DAYS_RANDOM = random.nextInt(7) + 1; // Рандом числа от 1 до 7
    private final By RENTAL_PERIOD_DAYS = By.xpath(".//div[@class='Dropdown-menu']/div['" + SEVEN_DAYS_RANDOM + "']");
    private final int COLORS_RANDOM = random.nextInt(2) + 1; // Рандом для выбора чекбокса цвета самоката
    private final By SCOOTER_COLOR = By.xpath(".//div[@class='Order_Checkboxes__3lWSI']/label['" + COLORS_RANDOM + "']/input");
    WebDriver webdriver;

    public AboutRentOrderPage(WebDriver webdriver) {
        this.webdriver = webdriver;
    }

    public OrderConfirmationModal aboutRentOrderPage(String deliveryDate, String informationForCourier) {
        webdriver.findElement(DELIVERY_DATE).sendKeys(deliveryDate, Keys.ENTER);
        webdriver.findElement(RENTAL_PERIOD).click();
        new WebDriverWait(webdriver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(RENTAL_PERIOD_DAYS));
        webdriver.findElement(RENTAL_PERIOD_DAYS).click();
        new WebDriverWait(webdriver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(SCOOTER_COLOR));
        webdriver.findElement(SCOOTER_COLOR).click();
        webdriver.findElement(DESCRIPTION).sendKeys(informationForCourier);
        webdriver.findElement(NEXT_BTN).click();

        return new OrderConfirmationModal(webdriver);
    }
}
