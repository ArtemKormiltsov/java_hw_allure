package guru.qa;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class WebSteps {

    @Step("Открыть страницу GitHub.com")
    public void openPage() {
        open("https://github.com");
    }

    @Step("В поисковую строку ввести {repo} и нажать Enter")
    public void searchByText(String repo) {
        $("[placeholder='Search GitHub']").setValue(repo).pressEnter();
    }

    @Step("Нажать на ссылку с текстом {repo}")
    public void clickRepositoryLink(String repo) {
        $(linkText(repo)).click();
    }

    @Step("Нажать на Issues")
    public void clickIssues() {
        $("#issues-tab").click();
    }

    @Step("Проверить наличие {issue} в списке")
    public void assertIssuesWithText(String issue) {
        $(withText(issue)).shouldBe(Condition.exist);
    }
}
