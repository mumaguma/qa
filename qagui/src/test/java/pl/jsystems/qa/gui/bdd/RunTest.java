package pl.jsystems.qa.gui.bdd;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features="src/test/resources",
        glue="pl.jsystems.qa.gui.bdd",
        plugin = {"pretty", "summary", "html:target/cucumber/report.html", "json: target/cucumber.json", "junit:target/cucumber.xml", "rerun:target/rerun.txt"},
//        tags="not @ignore"
        tags="@login and @wordpress"
)
public class RunTest {
}
