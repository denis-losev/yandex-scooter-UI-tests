package edu.praktikum.samokat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderConfirmationModal {
    WebDriver webdriver;

    //  Кнопка "Да"
    private final By YES_BTN = By.xpath(".//button[text()='Да']");

    public OrderConfirmationModal(WebDriver webdriver) {
        this.webdriver = webdriver;
    }

    // Подтвердить заказ
    public SuccessOrderModal confirmOrder() {
        new WebDriverWait(webdriver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(YES_BTN));
        webdriver.findElement(YES_BTN).click();
        return new SuccessOrderModal(webdriver);
    }
}
