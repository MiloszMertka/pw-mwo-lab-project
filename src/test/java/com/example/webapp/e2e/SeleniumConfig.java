package com.example.webapp.e2e;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@Getter
public class SeleniumConfig {

    private final WebDriver driver;

    public SeleniumConfig() {
        this.driver = new ChromeDriver();
    }

}
