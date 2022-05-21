package andreistefanwork.itests.pages.index;

import andreistefanwork.itests.AbstractIntegrationTest;
import net.serenitybdd.core.pages.WebElementState;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.function.Predicate;

import static andreistefanwork.itests.pages.index.IndexPage.*;
import static net.serenitybdd.screenplay.EventualConsequence.eventually;
import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.containsText;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static net.serenitybdd.screenplay.questions.WebElementQuestion.the;

@ExtendWith(SerenityJUnit5Extension.class)
public class IndexPageTest extends AbstractIntegrationTest {
    private static final String TEST_NAME = "Andrei";

    private IndexPage indexPage;

    @Test
    public void givenIndexPage_whenUserInputsName_thenAgePredictionIsDisplayedOnScreen() {
        givenThat(tester).wasAbleTo(Open.browserOn(indexPage));

        when(tester).attemptsTo(Enter.theValue(TEST_NAME).into(NAME_INPUT));

        then(tester).should(
                eventually(seeThat(the(PERSON_NAME), isVisible())),
                eventually(seeThat(the(PERSON_NAME), containsText(TEST_NAME))),
                eventually(seeThat(the(PERSON_AGE), isVisible())),
                eventually(seeThat(the(PERSON_AGE), isANumber()))
        );
    }

    private static Predicate<WebElementState> isANumber() {
        return (htmlElement) -> htmlElement.getText().matches("\\d*");
    }
}
