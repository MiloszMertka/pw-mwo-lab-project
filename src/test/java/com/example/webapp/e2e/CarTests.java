package com.example.webapp.e2e;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public class CarTests {

    private static final String APP_URL = "http://localhost:8080";
    private final WebDriver driver = new SeleniumConfig().getDriver();

    @BeforeEach
    void setUp() {
        driver.get(APP_URL);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    @Order(1)
    void givenCarData_whenCreateCar_thenCreatesNewCar() throws Exception {
        final var carName = "Test name";
        final var color = "red";
        final var carsButton = driver.findElement(By.xpath("//*[contains(text(),'Samochody')]"));
        carsButton.click();
        final var createCarButton = driver.findElement(By.xpath("//*[contains(text(),'Dodaj nowy samochód')]"));
        createCarButton.click();
        final var nameInput = driver.findElement(By.id("name"));
        nameInput.sendKeys(carName);
        final var colorInput = driver.findElement(By.id("color"));
        colorInput.sendKeys(color);
        final var engineInput = driver.findElement(By.id("engineId"));
        final var engineSelect = new Select(engineInput);
        engineSelect.selectByIndex(1);
        final var equipmentOptionsInput = driver.findElement(By.id("equipmentOptionsIds"));
        final var equipmentOptionsSelect = new Select(equipmentOptionsInput);
        equipmentOptionsSelect.selectByIndex(1);
        equipmentOptionsSelect.selectByIndex(2);
        final var js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector('button[type=\"submit\"]').click();");
        Thread.sleep(1000);
        final var carNameElement = driver.findElement(By.xpath("//*[contains(text(),'" + carName + "')]"));
        assertThat(carNameElement.isDisplayed()).isTrue();
    }

    @Test
    @Order(2)
    void givenSomeCar_whenEditCar_thenModifiesCar() throws Exception {
        final var carName = "Test name";
        final var newCarName = "New test name";
        final var carsButton = driver.findElement(By.xpath("//*[contains(text(),'Samochody')]"));
        carsButton.click();
        final var editCarButton = driver.findElement(By.xpath("//td[text()='" + carName + "']/following-sibling::td/div/a[text()='Edytuj']"));
        editCarButton.click();
        final var nameInput = driver.findElement(By.id("name"));
        nameInput.clear();
        nameInput.sendKeys(newCarName);
        final var js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector('button[type=\"submit\"]').click();");
        Thread.sleep(1000);
        final var carElement = driver.findElement(By.xpath("//*[contains(text(),'" + newCarName + "')]"));
        assertThat(carElement.isDisplayed()).isTrue();
        final var oldCarElement = driver.findElements(By.xpath("//*[contains(text(),'" + carName + "')]"));
        assertThat(oldCarElement.isEmpty()).isTrue();
    }

    @Test
    @Order(3)
    void givenSomeCar_whenDeleteCar_thenCarIsRemoved() {
        final var carName = "New test name";
        final var carsButton = driver.findElement(By.xpath("//*[contains(text(),'Samochody')]"));
        carsButton.click();
        final var deleteCarButton = driver.findElement(By.xpath("//td[text()='" + carName + "']/following-sibling::td/div/form/button[text()='Usuń']"));
        deleteCarButton.click();
        final var carElement = driver.findElements(By.xpath("//*[contains(text(),'" + carName + "')]"));
        assertThat(carElement.isEmpty()).isTrue();
    }

}
