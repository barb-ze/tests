package UI;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    public SelenideElement usernameField = $("#loginusername");
    public SelenideElement passwordField = $("#loginpassword");
    public SelenideElement loginButton = $("button[onclick='logIn()']");

    public void login(String username, String password) {
        usernameField.setValue(username);
        passwordField.setValue(password);
        loginButton.click();
    }
}