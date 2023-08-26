package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalog;

public class SubmitOrderTest extends BaseTest {

	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		// Product Catalog Page
		ProductCatalog productCatalog = landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalog.getProductList();
		productCatalog.addProductToCart(input.get("productName"));

		// Cart Page -- Click on cart icon
		CartPage cartPage = productCatalog.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);

		// Checkout Page
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");

		// Confirmation Page
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void orderHistoryTest() {
		ProductCatalog productCatalog = landingPage.loginApplication("rachealchu@gmail.com", "Racheal@123");
		OrderPage orderPage = productCatalog.goToOrdersPage();
		Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");

		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

//	@DataProvider
//	public Object[][] getData() {
//  Method 1
//	HashMap<String, String> map = new HashMap<String, String>();
//	map.put("email", "teemakareem96@gmail.com");
//	map.put("password", "Fatimah@123");
//	map.put("productName", "ZARA COAT 3");
//
//	HashMap<String, String> map1 = new HashMap<String, String>();
//	map1.put("email", "rachealchu@gmail.com");
//	map1.put("password", "Racheal@123");
//	map1.put("productName", "ADIDAS ORIGINAL");

//	return new Object[][] { { map }, { map1 } }; // number of curly brackets created
//	// represents number of data set to be
//	// provided.

//  Method 2
//		return new Object[][] { {"teemakareem96@gmail.com", "Fatimah@123", "ZARA COAT 3"}, 
//	{"rachealchu@gmail.com", "Racheal@123", "ADIDAS ORIGINAL"} }; // number of curly brackets created
//														// represents number of data set to be
//														// provided.

}
