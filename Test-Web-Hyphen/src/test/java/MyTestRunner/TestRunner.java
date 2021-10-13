package MyTestRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        dryRun = false,
        monochrome = true,
//        tags ,
        features = "src/test/resources/Features",
        glue = {"classpath:StepDefinition"},
        plugin = {"me.jvt.cucumber.report.PrettyReports:target/Cucumber"}

)

public class TestRunner {
}
