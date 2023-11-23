package com.example.webapp.e2e;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public class EngineTests {

    private static final String APP_URL = "http://localhost:8080";
    private WebDriver driver;

    @BeforeEach
    void setUp() {
        driver = new SeleniumConfig().getDriver();
        driver.get(APP_URL);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    @Order(1)
    void givenEngineData_whenCreateEngine_thenCreatesNewEngine() {
        final var engineName = "Test name";
        final var horsePower = "100";
        final var enginesButton = driver.findElement(By.xpath("//*[contains(text(),'Silniki')]"));
        enginesButton.click();
        final var createEngineButton = driver.findElement(By.xpath("//*[contains(text(),'Dodaj nowy silnik')]"));
        createEngineButton.click();
        final var nameInput = driver.findElement(By.id("name"));
        nameInput.sendKeys(engineName);
        final var horsePowerInput = driver.findElement(By.id("horsePower"));
        horsePowerInput.sendKeys(horsePower);
        final var saveButton = driver.findElement(By.xpath("//*[contains(text(),'Zapisz')]"));
        saveButton.click();
        final var engineNameElement = driver.findElement(By.xpath("//*[contains(text(),'" + engineName + "')]"));
        assertThat(engineNameElement.isDisplayed()).isTrue();
        final var engineHorsePowerElement = driver.findElement(By.xpath("//*[contains(text(),'" + horsePower + "')]"));
        assertThat(engineHorsePowerElement.isDisplayed()).isTrue();
    }

    @Test
    @Order(2)
    void givenSomeEngine_whenEditEngine_thenModifiesEngine() {
        final var engineName = "Test name";
        final var newEngineName = "New test name";
        final var enginesButton = driver.findElement(By.xpath("//*[contains(text(),'Silniki')]"));
        enginesButton.click();
        final var editEngineButton = driver.findElement(By.xpath("//td[text()='" + engineName + "']/following-sibling::td/div/a[text()='Edytuj']"));
        editEngineButton.click();
        final var nameInput = driver.findElement(By.id("name"));
        nameInput.clear();
        nameInput.sendKeys(newEngineName);
        final var saveButton = driver.findElement(By.xpath("//*[contains(text(),'Zapisz')]"));
        saveButton.click();
        final var engineElement = driver.findElement(By.xpath("//*[contains(text(),'" + newEngineName + "')]"));
        assertThat(engineElement.isDisplayed()).isTrue();
        final var oldEngineElement = driver.findElements(By.xpath("//*[contains(text(),'" + engineName + "')]"));
        assertThat(oldEngineElement.isEmpty()).isTrue();
    }

    @Test
    @Order(3)
    void givenSomeEngine_whenDeleteEngine_thenEngineIsRemoved() {
        final var engineName = "New test name";
        final var enginesButton = driver.findElement(By.xpath("//*[contains(text(),'Silniki')]"));
        enginesButton.click();
        final var deleteEngineButton = driver.findElement(By.xpath("//td[text()='" + engineName + "']/following-sibling::td/div/form/button[text()='Usuń']"));
        deleteEngineButton.click();
        final var engineElement = driver.findElements(By.xpath("//*[contains(text(),'" + engineName + "')]"));
        assertThat(engineElement.isEmpty()).isTrue();
    }

}
