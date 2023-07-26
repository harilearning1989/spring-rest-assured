import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MyStepdefs {
    @When("the client calls \\/calc\\/add with values {int} and {int}")
    public void theClientCallsCalcAddWithValuesAnd(int arg0, int arg1) {
    }

    @Then("the client receives answer as {int}")
    public void theClientReceivesAnswerAs(int arg0) {
        
    }

    @When("the client calls \\/calc\\/sub with values {int} and {int}")
    public void theClientCallsCalcSubWithValuesAnd(int arg0, int arg1) {
    }
}
