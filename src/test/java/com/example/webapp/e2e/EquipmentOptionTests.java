package com.example.webapp.e2e;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public class EquipmentOptionTests {

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
    void givenEquipmentOptionData_whenCreateEquipmentOption_thenCreatesNewEquipmentOption() {
        final var equipmentOptionName = "Test name";
        final var equipmentOptionsButton = driver.findElement(By.xpath("//*[contains(text(),'Opcje Wyposażenia')]"));
        equipmentOptionsButton.click();
        final var createEquipmentOptionButton = driver.findElement(By.xpath("//*[contains(text(),'Dodaj nową opcję wyposażenia')]"));
        createEquipmentOptionButton.click();
        final var nameInput = driver.findElement(By.id("name"));
        nameInput.sendKeys(equipmentOptionName);
        final var saveButton = driver.findElement(By.xpath("//*[contains(text(),'Zapisz')]"));
        saveButton.click();
        final var equipmentOptionNameElement = driver.findElement(By.xpath("//*[contains(text(),'" + equipmentOptionName + "')]"));
        assertThat(equipmentOptionNameElement.isDisplayed()).isTrue();
    }

    @Test
    @Order(2)
    void givenSomeEquipmentOption_whenEditEquipmentOption_thenModifiesEquipmentOption() {
        final var equipmentOptionName = "Test name";
        final var newEquipmentOptionName = "New test name";
        final var equipmentOptionsButton = driver.findElement(By.xpath("//*[contains(text(),'Opcje Wyposażenia')]"));
        equipmentOptionsButton.click();
        final var editEquipmentOptionButton = driver.findElement(By.xpath("//td[text()='" + equipmentOptionName + "']/following-sibling::td/div/a[text()='Edytuj']"));
        editEquipmentOptionButton.click();
        final var nameInput = driver.findElement(By.id("name"));
        nameInput.clear();
        nameInput.sendKeys(newEquipmentOptionName);
        final var saveButton = driver.findElement(By.xpath("//*[contains(text(),'Zapisz')]"));
        saveButton.click();
        final var equipmentOptionElement = driver.findElement(By.xpath("//*[contains(text(),'" + newEquipmentOptionName + "')]"));
        assertThat(equipmentOptionElement.isDisplayed()).isTrue();
        final var oldEquipmentOptionElement = driver.findElements(By.xpath("//*[contains(text(),'" + equipmentOptionName + "')]"));
        assertThat(oldEquipmentOptionElement.isEmpty()).isTrue();
    }

    @Test
    @Order(3)
    void givenSomeEquipmentOption_whenDeleteEquipmentOption_thenEquipmentOptionIsRemoved() {
        final var equipmentOptionName = "New test name";
        final var equipmentOptionsButton = driver.findElement(By.xpath("//*[contains(text(),'Opcje Wyposażenia')]"));
        equipmentOptionsButton.click();
        final var deleteEquipmentOptionButton = driver.findElement(By.xpath("//td[text()='" + equipmentOptionName + "']/following-sibling::td/div/form/button[text()='Usuń']"));
        deleteEquipmentOptionButton.click();
        final var equipmentOptionElement = driver.findElements(By.xpath("//*[contains(text(),'" + equipmentOptionName + "')]"));
        assertThat(equipmentOptionElement.isEmpty()).isTrue();
    }

}
