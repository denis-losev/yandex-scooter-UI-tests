package edu.praktikum.samokat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SuccessOrderModal {
    WebDriver webdriver;
    //  Текстовый блок "Заказ оформлен"
    private final By orderSuccessResult = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ']");

    public SuccessOrderModal(WebDriver webdriver) {
        this.webdriver = webdriver;
    }

    public String getOrderHeader() {
        new WebDriverWait(webdriver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(orderSuccessResult));
        String[] header = webdriver.findElement(orderSuccessResult).getText().split("\n");
        return header[0];
    }
}
