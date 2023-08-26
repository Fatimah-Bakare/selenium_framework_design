package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.ProductCatalog;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups= {"ErrorHandling"},retryAnalyzer=rahulshettyacademy.TestComponents.Retry.class)
	public void loginErrorValidation() throws IOException, InterruptedException {

		String productName = "ZARA COAT 3";
		// Product Catalog Page
		ProductCatalog productCatalog = landingPage.loginApplication("johndoe@gmail.com", "jonedoe@123");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}
	
	@Test
	public void productErrorValidation() throws IOException, InterruptedException {

		String productName = "ZARA COAT 3";

		// Product Catalog Page
		ProductCatalog productCatalog = landingPage.loginApplication("teemakareem96@gmail.com", "Fatimah@123");
		List<WebElement> products = productCatalog.getProductList();
		productCatalog.addProductToCart(productName);

		// Cart Page -- Click on cart icon
		CartPage cartPage = productCatalog.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);

	}

}
