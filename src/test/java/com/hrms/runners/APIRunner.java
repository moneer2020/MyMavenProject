package com.hrms.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        dryRun = false,
        glue = "com/hrms/API/workflow/steps",
        tags = "@APIWorkflow",
        plugin = {"pretty",
                "html:target/cucumber-default-reports",
                "rerun:target/FailedTests.txt",
                "json:target/cucumber.json"})
public class APIRunner {
}
