package andreistefanwork.itests;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.util.EnvironmentVariables;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

import static net.thucydides.core.ThucydidesSystemProperty.WEBDRIVER_BASE_URL;

public abstract class AbstractIntegrationTest {
    @Managed
    protected WebDriver browser;
    protected Actor tester;

    private EnvironmentVariables environmentVariables;

    @BeforeEach
    void setUp() {
        tester = Actor.named("Tester");
        tester.can(BrowseTheWeb.with(browser));

        setBaseUrl();
    }

    private void setBaseUrl() {
        environmentVariables.setProperty(WEBDRIVER_BASE_URL.getPropertyName(),
                "http://localhost:4200");
    }
}
