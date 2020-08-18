package test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import dataGenertor.RegistrationDataGenerator;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class EntryFormTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void setUp() {
        open("https://planoplan.com/");
    }

    @Test
    @DisplayName("Негативный кейс. Попытка входа незарегистрированного пользователя")
    void shouldNotSubmitInvalidCredentials() {
        $(withText("Войти")).click();
        val user = RegistrationDataGenerator.generateUnregisteredUser();
        $("input[name=username]").setValue(user.getEmail());
        $("#form-entry > div > form > fieldset:nth-child(2) > label > div.input__Wrapper-sc-185agi7-2.eILXoJ > input").setValue(user.getPassword());
        $("button[data-test=login_submit]").click();
        $("[data-test=error-message]").shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Негативный кейс. Не введена почта")
    void shouldNotSubmitWithoutEmail() {
        $(withText("Войти")).click();
        val user = RegistrationDataGenerator.generateUnregisteredUser();
        $("input[name=username]").click();
        $("#form-entry > div > form > fieldset:nth-child(2) > label > div.input__Wrapper-sc-185agi7-2.eILXoJ > input").setValue(user.getPassword());
        $(withText("Поле не может быть пустым")).shouldBe(Condition.visible);

    }

    @Test
    @DisplayName("Негативный кейс. Не введен пароль")
    void shouldNotSubmitWithoutPassword() {
        $(withText("Войти")).click();
        val user = RegistrationDataGenerator.generateUnregisteredUser();
        $("#form-entry > div > form > fieldset:nth-child(2) > label > div.input__Wrapper-sc-185agi7-2.eILXoJ > input").click();
        $("input[name=username]").setValue(user.getEmail());
        $(withText("Поле не может быть пустым")).shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Негативный кейс. Кириллица в поле почты")
    void shouldNotSubmitCyrillicInEmailField() {
        $(withText("Войти")).click();
        val user = RegistrationDataGenerator.generateUserWithCyrillicInEmail();
        $("input[name=username]").setValue(user.getEmail());
        $("#form-entry > div > form > fieldset:nth-child(2) > label > div.input__Wrapper-sc-185agi7-2.eILXoJ > input").setValue(user.getPassword());
        $(withText("Email введено неверно")).shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Негативный кейс. Цифры в поле почты")
    void shouldNotSubmitNumbersInEmailField() {
        $(withText("Войти")).click();
        val user = RegistrationDataGenerator.generateUserWithNumbersInEmail();
        $("input[name=username]").setValue(user.getEmail());
        $("#form-entry > div > form > fieldset:nth-child(2) > label > div.input__Wrapper-sc-185agi7-2.eILXoJ > input").setValue(user.getPassword());
        $(withText("Email введено неверно")).shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Негативный кейс. Некорректный формат почты")
    void shouldNotSubmitNumbersIncorrectEmail() {
        $(withText("Войти")).click();
        val user = RegistrationDataGenerator.generateUserWithIncorrectEmail();
        $("input[name=username]").setValue(user.getEmail());
        $("#form-entry > div > form > fieldset:nth-child(2) > label > div.input__Wrapper-sc-185agi7-2.eILXoJ > input").setValue(user.getPassword());
        $(withText("Email введено неверно")).shouldBe(Condition.visible);
    }
}
