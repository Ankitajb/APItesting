package stepDefinations;

import io.cucumber.core.backend.StepDefinition;
import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {
    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {
       //write a code that will give you place_id
       //write a code  execute only when place_id is null
        stepDefination m = new stepDefination();
       if(stepDefination.place_id == null)
       {
        m.addPlacePayloadWith("Ankita","English", "50, wise street");
        m.userCallsWithHttpRequest("AddPlaceAPI","POST");
        m.verifyPlace_IdCreatedMapsToUsing("Ankita", "GetPlaceAPI");
       }

    }
}
