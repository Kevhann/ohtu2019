package ohtu;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Stepdefs {
    // WebDriver driver = new ChromeDriver();
    WebDriver driver = new HtmlUnitDriver();
    String baseUrl = "http://localhost:4567";

    @Given("login is selected")
    public void loginIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();
    }
    @Given("command new user is selected")
    public void newUserIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
    }

    @Given("user with username {string} and password {string} is tried to be created")
    public void attemptUserCreationWithBadCredentials(String username, String password) {
        newUserIsSelected();
        createNewWith(username, password, password);

    }

    @Given("user with username {string} with password {string} is successfully created")
    public void alidNewUserIsCreated(String username, String password) {
        newUserIsSelected();
        createNewWith(username, password, password);
    }

    @When("correct username {string} and password {string} are given")
    public void correctUsernameAndPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }

    @When("a valid username {string} and password {string} and matching password confirmation are entered")
    public void correctUsernameAndPasswordsAreGiven(String username, String password) {
        createNewWith(username, password, password);
    }

    @When("an invalid username {string} and valid password {string} and matching password are entered")
    public void incorrectUsernameAndValidMatchingPasswords(String username, String password) {
        createNewWith(username, password, password);
    }

    @Then("user is not created and error {string} is reported")
    public void creatingNewUserWithInvalidCredentials(String message) {
        pageHasContent(message);
    }


    @Then("a new user is created")
    public void newUserWasCreated() {
        pageHasContent("Welcome to Ohtu Application");
    }

    @Then("user is logged in")
    public void userIsLoggedIn() {
        pageHasContent("Ohtu Application main page");
    }

    @When("correct username {string} and incorrect password {string} are given")
    public void correctUsernameAndIncorrectPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }

    @When("incorrect username {string} and password {string} are given") 
        public void incorrectUsernameAndIncorrectPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }
    

    @When("nonexistent username {string} and incorrect password {string} are given")
    public void nonexistentUsernameAndIncorrectPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }

    @When("a valid username {string} and an invalid password {string} and matching password are given")
    public void createNewUserWithValidUsernameAndTooShortPassword(String username, String password) {
        createNewWith(username, password, password);
    }

    @When("a valid username {string} and a valid password {string} and confirmation {string} are given")
    public void createNewUserWithValidUsernameAndDifferingValidPasswords(String username, String password, String confirmation) {
        createNewWith(username, password, confirmation);
    }

    @Then("user is not logged in and error message is given")
    public void userIsNotLoggedInAndErrorMessageIsGiven() {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }

 

    @After
    public void tearDown() {
        driver.quit();
    }

    /* helper methods */

    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }

    private void createNewWith(String username, String password, String confirmation) {
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(confirmation);
        element = driver.findElement(By.name("signup"));
        element.submit();
    }

    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();
    }
}
