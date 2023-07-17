package edu.praktikum.samokat;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class СheckOrderScooter extends BaseTest {
    private final String firstName; //  Имя
    private final String lastName; // Фамилия
    private final String address; // Адрес
    private final String phoneNumber; // Номер телефона
    private final String deliveryDate; // Дата доставки
    private final String description; // Комментарий для курьера

    public СheckOrderScooter (String firstName, String lastName, String address, String phone, String dateToDelivery, String description) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phone;
        this.deliveryDate = dateToDelivery;
        this.description = description;
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
        aboutRentOrderPage = userInfoOrderPage.userInfoOrderPage(firstName, lastName, address, phoneNumber);

        OrderConfirmationModal orderConfirmationModal;
        orderConfirmationModal = aboutRentOrderPage.aboutRentOrderPage(deliveryDate, description);

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
        aboutRentOrderPage = userInfoOrderPage.userInfoOrderPage(firstName, lastName, address, phoneNumber);

        OrderConfirmationModal orderConfirmationModal;
        orderConfirmationModal = aboutRentOrderPage.aboutRentOrderPage(deliveryDate, description);

        SuccessOrderModal successOrderModal;
        successOrderModal = orderConfirmationModal.confirmOrder();

        Assert.assertEquals("Ошибка", "Заказ оформлен", successOrderModal.getOrderHeader());
    }
}
