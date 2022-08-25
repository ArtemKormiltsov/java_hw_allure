package guru.qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class IssuesSearchTest {

    private static final String REPOSITORY = "Allure";
    private static final String LINK_NAME = "allure-framework/allure2";
    private static final String ISSUE_TEXT = "Cant install allure";
    private static final String URL = "https://github.com";

    @BeforeAll
    static void listenerConnect() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @Test
    void allureIssuesTest() {
        open(URL);
        $("[placeholder='Search GitHub']").setValue(REPOSITORY).pressEnter();
        $(linkText(LINK_NAME)).click();
        $("#issues-tab").click();
        $(withText(ISSUE_TEXT)).shouldBe(Condition.exist);
    }

    @Test
    void issuesTestWithLambda() {
        step("Открыть страницу GitHub.com", () -> {
            open("https://github.com");
        });
        step("В поисковую строку ввести" + REPOSITORY + "и нажать Enter", () -> {
            $("[placeholder='Search GitHub']").setValue(REPOSITORY).pressEnter();
        });
        step("Нажать на ссылку с текстом" + LINK_NAME, () -> {
            $(linkText(LINK_NAME)).click();
        });
        step("Нажать на Issues", () -> {
            $("#issues-tab").click();
        });
        step("Проверить наличие" + ISSUE_TEXT + "в списке", () -> {
            $(withText(ISSUE_TEXT)).shouldBe(Condition.exist);
        });
    }

    @Test
    public void testWebSteps() {
        WebSteps steps = new WebSteps();
        steps.openPage();
        steps.searchByText(REPOSITORY);
        steps.clickRepositoryLink(LINK_NAME);
        steps.clickIssues();
        steps.assertIssuesWithText(ISSUE_TEXT);
    }
}

