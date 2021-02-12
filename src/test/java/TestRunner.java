// NB: We may need to place this in a folder under: test/java/RunnerFile.txt
//     May need to create a Package like src/test/java/com.company/stepdefinitions and
//                                       src/test/java/com.company/another_folder/RunnerFile.txt

//import cucumber.api.SnippetType;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:target/junit-reports/out.html"},
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        dryRun = false,
        //glue = {"src/test/java/nicebank/step_definitions"},
        features = {"src/test/resource/features"}
)

public class TestRunner {
}
