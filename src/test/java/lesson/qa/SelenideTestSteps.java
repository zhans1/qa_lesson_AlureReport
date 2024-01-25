package lesson.qa;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class SelenideTestSteps {
    private static final int ISSUE = 84;
    private static final String REPOSITORY = "eroshenkoam/allure-example";

    @Test
    public void testLambdaStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываетм страницу", () -> {
            open("https://github.com");
        });
        step("Проходим по поисковику", () -> {
            $("span.flex-1").click();
            $("#query-builder-test").setValue(REPOSITORY).pressEnter();
        });
        step("Находим нужную репозиторию", () -> {
            $(linkText(REPOSITORY)).click();
        });
        step("Находим/Открываем таб Issue", () -> {
            $("#issues-tab").click();
        });
        step("Проверяем наличие Issue с номером " + ISSUE, () -> {
            $("#issue_" + ISSUE + "_link").shouldHave(text("Issue_created_to_test_allure_reports"));
        });
    }

    @Test
    public void testAnnotatedSteps() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        SelenideTestAnnotate steps = new SelenideTestAnnotate();

        steps.openMainPage();
        steps.searchRepository(REPOSITORY);
        steps.clickRepository(REPOSITORY);
        steps.openIssueTab();
        steps.findIssue84(ISSUE);
    }

    @Test
    public void testIssueSearch() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com");

        $("span.flex-1").click();
        $("#query-builder-test").setValue(REPOSITORY).pressEnter();

        $(linkText(REPOSITORY)).click();
        $("#issues-tab").click();
        $("#issue_" + ISSUE + "_link").shouldHave(text("Issue_created_to_test_allure_reports"));
    }
}