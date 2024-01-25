package lesson.qa;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.*;

public class SelenideTest {
    @Test
    public void testIssueSearch() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com");

        $("span.flex-1").click();
        $("#query-builder-test").setValue("eroshenkoam/allure").pressEnter();

        $(linkText("eroshenkoam/allure-example")).click();
        $("#issues-tab").click();
        $("#issue_84_link").shouldHave(text("Issue_created_to_test_allure_reports"));
    }
}
