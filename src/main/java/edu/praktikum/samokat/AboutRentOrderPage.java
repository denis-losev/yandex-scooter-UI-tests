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
    private final By deliveryDate = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    // Поле ввода комментария
    private final By description = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    // Поле выбора срока аренды
    private final By rentalPeriod = By.xpath(".//div[@class='Dropdown-root']");
    // Кнопка "Заказать"
    private final By nextBtn = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");
    Random random = new Random();
    private final int sevenDaysRandom = random.nextInt(7) + 1; // Рандом числа от 1 до 7
    private final By rentalPeriodDays = By.xpath(".//div[@class='Dropdown-menu']/div['" + sevenDaysRandom + "']");
    private final int colorsRandom = random.nextInt(2) + 1; // Рандом для выбора чекбокса цвета самоката
    private final By scooterColor = By.xpath(".//div[@class='Order_Checkboxes__3lWSI']/label['" + colorsRandom + "']/input");
    WebDriver webdriver;

    public AboutRentOrderPage(WebDriver webdriver) {
        this.webdriver = webdriver;
    }

    public OrderConfirmationModal aboutRentOrderPage(String deliveryDate, String informationForCourier) {
        webdriver.findElement(this.deliveryDate).sendKeys(deliveryDate, Keys.ENTER);
        webdriver.findElement(rentalPeriod).click();
        new WebDriverWait(webdriver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(rentalPeriodDays));
        webdriver.findElement(rentalPeriodDays).click();
        new WebDriverWait(webdriver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(scooterColor));
        webdriver.findElement(scooterColor).click();
        webdriver.findElement(description).sendKeys(informationForCourier);
        webdriver.findElement(nextBtn).click();

        return new OrderConfirmationModal(webdriver);
    }
}
