package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver;

	public LandingPage(WebDriver driver) {

		super(driver); // To be used in Parent Class AbstractComponent
		// initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// WebElement userEmail = driver.findElement(By.id("userEmail"));

	// PageFactory

	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement PasswordEle; // driver.findElement(By.id("userPassword"))

	@FindBy(id = "login")
	WebElement submit; // driver.findElement(By.name("login"))
	
	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMessage; 

	public ProductCatalog loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		PasswordEle.sendKeys(password);
		submit.click();
		ProductCatalog productCatalog = new ProductCatalog(driver);
		return productCatalog;
	}
	
	public String getErrorMessage() 
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}

}
