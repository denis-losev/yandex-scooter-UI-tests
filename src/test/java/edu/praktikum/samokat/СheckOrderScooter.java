package edu.praktikum.samokat;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class СheckOrderScooter extends MainTestPattern {
    private final String FIRST_NAME; //  Имя
    private final String LAST_NAME; // Фамилия
    private final String ADDRESS; // Адрес
    private final String PHONE_NUMBER; // Номер телефона
    private final String DELIVERY_DATE; // Дата доставки
    private final String DESCRIPTION; // Комментарий для курьера

    public СheckOrderScooter (String firstName, String lastName, String address, String phone, String dateToDelivery, String description) {
        this.FIRST_NAME = firstName;
        this.LAST_NAME = lastName;
        this.ADDRESS = address;
        this.PHONE_NUMBER = phone;
        this.DELIVERY_DATE = dateToDelivery;
        this.DESCRIPTION = description;
    }

    @Parameterized.Parameters()
    public static Object[][] orderParams() {
        return new Object[][]{
                {"Денис", "Лосев", "Ленина 1", "+79621510777", "22.07.2023", "Вход через арку"},
                {"Иван", "Иванов", "ул. Дыбенко, д. 5, кв. 10", "89621510777", "29.07.2023", "Позвонить за 2 часа"}
        };
    }

    @Test // Заказ через кнопку "Заказать" в шапке страницы
    public void orderByClickHeader() {
        MainPage mainPage = new MainPage(webdriver);
        mainPage.acceptCookies();

        UserInfoOrderPage userInfoOrderPage;
        userInfoOrderPage = mainPage.headerOrderBtnClick();

        AboutRentOrderPage aboutRentOrderPage;
        aboutRentOrderPage = userInfoOrderPage.userInfoOrderPage(FIRST_NAME, LAST_NAME, ADDRESS, PHONE_NUMBER);

        OrderConfirmationModal orderConfirmationModal;
        orderConfirmationModal = aboutRentOrderPage.aboutRentOrderPage(DELIVERY_DATE, DESCRIPTION);

        SuccessOrderModal successOrderModal;
        successOrderModal = orderConfirmationModal.confirmOrder();

        Assert.assertEquals("Ошибка", "Заказ оформлен", successOrderModal.getOrderHeader());
    }

    @Test // Заказ через кнопку "Заказать" в разделе "Как это работает"
    public void orderByClickBody() {
        MainPage mainPage = new MainPage(webdriver);
        mainPage.acceptCookies();

        UserInfoOrderPage userInfoOrderPage;
        userInfoOrderPage = mainPage.bottomOrderBtnClick();

        AboutRentOrderPage aboutRentOrderPage;
        aboutRentOrderPage = userInfoOrderPage.userInfoOrderPage(FIRST_NAME, LAST_NAME, ADDRESS, PHONE_NUMBER);

        OrderConfirmationModal orderConfirmationModal;
        orderConfirmationModal = aboutRentOrderPage.aboutRentOrderPage(DELIVERY_DATE, DESCRIPTION);

        SuccessOrderModal successOrderModal;
        successOrderModal = orderConfirmationModal.confirmOrder();

        Assert.assertEquals("Ошибка", "Заказ оформлен", successOrderModal.getOrderHeader());
    }
}
