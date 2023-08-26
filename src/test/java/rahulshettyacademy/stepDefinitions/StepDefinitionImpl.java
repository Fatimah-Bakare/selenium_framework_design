package rahulshettyacademy.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalog;

public class StepDefinitionImpl extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalog productCatalog;
	public CartPage cartPage;
	public CheckoutPage checkoutPage;
	public ConfirmationPage confirmationPage;

	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		landingPage = launchApplication();
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username, String password) {
		productCatalog = landingPage.loginApplication(username, password);
	}

	@When("^I add product (.+) to Cart$")
	public void I_add_product_to_cart(String productName) throws InterruptedException {
		List<WebElement> products = productCatalog.getProductList();
		productCatalog.addProductToCart(productName);
	}

	@When("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_the_order(String productName) {
		// Cart Page -- Click on cart icon
		cartPage = productCatalog.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);

		// Checkout Page
		checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		confirmationPage = checkoutPage.submitOrder();
	}
	
	@Then ("{string} is displayed on ConfirmationPage")
	public void message_displayed_on_ConfirmationPage(String string) 
	{
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then ("{string} message is displayed on ConfirmationPage")
	public void message_is_displayed_on_ConfirmationPage(String strArg) 
	{
		Assert.assertEquals(strArg, landingPage.getErrorMessage());
		driver.close();
	}

}
