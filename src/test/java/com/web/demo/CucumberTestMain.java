package com.web.demo;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/cal",
        plugin = {"pretty", "html:target/cucumber/calc"},
        extraGlue = "com.web.demo")
public class CucumberTestMain {
}
