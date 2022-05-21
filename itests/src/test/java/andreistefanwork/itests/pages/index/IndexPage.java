package andreistefanwork.itests.pages.index;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;

import static net.serenitybdd.screenplay.targets.Target.the;

@DefaultUrl("/")
public class IndexPage extends PageObject {
    public static final Target NAME_INPUT =
            the("name input").located(By.id("nameInput"));
    public static final Target PERSON_NAME =
            the("name header text").located(By.id("personName"));
    public static final Target PERSON_AGE =
            the("age header text").located(By.id("personAge"));
}

