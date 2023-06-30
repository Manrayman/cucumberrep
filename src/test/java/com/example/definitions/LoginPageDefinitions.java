package com.example.definitions;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.AssertJUnit;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.containsString;

public class LoginPageDefinitions {

	private static WebDriver driver;
	public final static int TIMEOUT = 10;
	public WebDriverWait wait;
	WebElement resetPasswordButton;
	public static WebElement cancelButton;
	@Before
	public void setUp() {
		

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
		driver.manage().window().maximize();
	}

	@Given("User is on HRMLogin page {string}")
	public void loginTest(String url) {

		driver.get(url);

	}

	@When("User enters username as {string} and password as {string}")
	public void goToHomePage(String userName, String passWord) {

		// login to application
		driver.findElement(By.name("username")).sendKeys(userName);
		driver.findElement(By.name("password")).sendKeys(passWord);
		driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();

		// go the next page

	}

	@When("User clicks on Forgot Password Link on homepage")
	public void user_clicks_on_forgot_password_link_on_homepage() {
		driver.findElement(By.cssSelector(".orangehrm-login-forgot-header")).click();
	}

	@Then("User navigates to password reset page")
	public void user_navigates_to_password_reset_page() {
		WebElement resetpasswordtxt = driver.findElement(By.cssSelector(".orangehrm-forgot-password-title"));
		String text = resetpasswordtxt.getText();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.textToBePresentInElement(resetpasswordtxt, text));

		String forgotpasswordurl = driver.getCurrentUrl();
	//instead of waiting complete url use contains
		Assert.assertEquals(forgotpasswordurl,
				"https://opensource-demo.orangehrmlive.com/web/index.php/auth/requestPasswordResetCode");
		resetpasswordtxt.isDisplayed();
		resetPasswordButton = driver.findElement(By.cssSelector(".orangehrm-forgot-password-button--reset"));
		resetPasswordButton.isDisplayed();
		cancelButton = driver.findElement(By.cssSelector(".orangehrm-forgot-password-button--cancel"));
		cancelButton.isDisplayed();

	}

	@When("User clicks on cancel button on forgot password page")
	public void user_clicks_on_cancel_button_on_forgot_password_page() {
		//WebElement cancelButton = driver.findElement(By.cssSelector(".orangehrm-forgot-password-button--cancel"));
		cancelButton.click();
	}

	@Then("User navigates back to home page")
	public void user_navigates_back_to_home_page() {
		String homepageurl = driver.getCurrentUrl();
		Assert.assertEquals(homepageurl, "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	}

	@Then("User should be able to login sucessfully and new page open")
	public void verifyLogin() {

		String homePageHeading = driver.findElement(By.xpath("//h6")).getText();

		// Verify new page - HomePage
		AssertJUnit.assertEquals(homePageHeading, "Dashboard");

	}

	@Then("User should be able to see error message {string}")
	public void verifyErrorMessage(String expectedErrorMessage) {

		String actualErrorMessage = driver.findElement(By.className("oxd-alert-content-text")).getText();

		// Verify Error Message
		AssertJUnit.assertEquals(actualErrorMessage, expectedErrorMessage);

	}

	@After
	public void teardown() {

		driver.quit();
	}

}