package edu.praktikum.samokat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;
import java.util.Random;

//Класс страницы с полями ввода информации о пользователе
public class UserInfoOrderPage {
    WebDriver webdriver;
    private final By firstName = By.xpath(".//input[@placeholder='* Имя']");
    private final By lastName = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By address = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metro = By.xpath(".//input[@placeholder='* Станция метро']");
    Random random = new Random();
    private final int randomNumberMetroStation = random.nextInt(237) + 1;
    private final By metroStation = By.xpath(".//button[@value='" + randomNumberMetroStation + "']");
    private final By phoneNumber = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextBtn = By.xpath(".//button[contains(text(),'Далее')]");

    public AboutRentOrderPage userInfoOrderPage(String nameInput, String lastNameInput, String addressInput, String phoneInput) {
        webdriver.findElement(firstName).sendKeys(nameInput);
        webdriver.findElement(lastName).sendKeys(lastNameInput);
        webdriver.findElement(address).sendKeys(addressInput);
        webdriver.findElement(phoneNumber).sendKeys(phoneInput);
        webdriver.findElement(metro).click();
        new WebDriverWait(webdriver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(metroStation));
        ((JavascriptExecutor) webdriver).executeScript("arguments[0].scrollIntoView();", webdriver.findElement(metroStation));
        webdriver.findElement(metroStation).click();
        webdriver.findElement(nextBtn).click();

        return new AboutRentOrderPage(webdriver);
    }

    public UserInfoOrderPage(WebDriver webdriver){
        this.webdriver = webdriver;
    }
}
