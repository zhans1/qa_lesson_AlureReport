package lesson.qa;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class SelenideTestAnnotate {

    @Step("Открываетм страницу")
    public void openMainPage(){
        open("https://github.com");
    }
    @Step("Проходим по поисковику")
    public void searchRepository(String repo){
        $("span.flex-1").click();
        $("#query-builder-test").setValue(repo).pressEnter();
    }
    @Step("Находим нужную репозиторию")
    public void clickRepository(String repo){
        $(linkText(repo)).click();
    }
    @Step("Находим/Открываем таб Issue")
    public void openIssueTab(){
        $("#issues-tab").click();
    }
    @Step("Проверяем наличие Issue с номером {issue}")
    public void findIssue84(int issue){
        $("#issue_" + issue + "_link").shouldHave(text("Issue_created_to_test_allure_reports"));
    }
}
