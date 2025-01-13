package UI;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class UITests {

    @BeforeAll
    static void setup() {
        Configuration.baseUrl = "https://www.demoblaze.com/";
        Configuration.startMaximized = true;
    }

    @Test
    void registerAndLoginTest() {
        open("/index.html");

        // Регистрация
        $("#signin2").click();
        $("#sign-username").setValue("testUser");
        $("#sign-password").setValue("password123");
        $("button[onclick='register()']").click();

        // Логин
        $("#login2").click();
        new LoginPage().login("testUser", "password123");
    }
    @Test
    void addProductsToCartTest() {
        // Пример добавления продукта
        $$("a[href^='prod.html']").first().click();  // Открыть первый продукт
        $("a[onclick='addToCart(1)']").click();      // Добавить в корзину
    }
}

