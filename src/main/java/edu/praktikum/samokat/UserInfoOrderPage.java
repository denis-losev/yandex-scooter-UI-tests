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
    private final By FIRST_NAME = By.xpath(".//input[@placeholder='* Имя']");
    private final By LAST_NAME = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By ADDRESS = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By METRO = By.xpath(".//input[@placeholder='* Станция метро']");
    Random random = new Random();
    private final int RANDOM_NUMBER_METRO_STATION = random.nextInt(237) + 1;
    private final By METRO_STATION = By.xpath(".//button[@value='" + RANDOM_NUMBER_METRO_STATION + "']");
    private final By PHONE_NUMBER = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By NEXT_BTN = By.xpath(".//button[contains(text(),'Далее')]");

    public AboutRentOrderPage userInfoOrderPage(String nameInput, String lastNameInput, String addressInput, String phoneInput) {
        webdriver.findElement(FIRST_NAME).sendKeys(nameInput);
        webdriver.findElement(LAST_NAME).sendKeys(lastNameInput);
        webdriver.findElement(ADDRESS).sendKeys(addressInput);
        webdriver.findElement(PHONE_NUMBER).sendKeys(phoneInput);
        webdriver.findElement(METRO).click();
        new WebDriverWait(webdriver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(METRO_STATION));
        ((JavascriptExecutor) webdriver).executeScript("arguments[0].scrollIntoView();", webdriver.findElement(METRO_STATION));
        webdriver.findElement(METRO_STATION).click();
        webdriver.findElement(NEXT_BTN).click();

        return new AboutRentOrderPage(webdriver);
    }

    public UserInfoOrderPage(WebDriver webdriver){
        this.webdriver = webdriver;
    }
}
