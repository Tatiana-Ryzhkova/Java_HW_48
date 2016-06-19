package core;
/**One instance of that class represents SignUp page **/

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SignUp_Page {
	
	WebDriver driver;
	
	@FindBy(id="id_fname")
	@CacheLookup
	static WebElement fname_field;
	
	@FindBy(id="id_lname")
	@CacheLookup
	static WebElement lname_field;
	
	@FindBy(id="id_email")
	@CacheLookup
	static WebElement email_field;
	
	@FindBy(id="id_phone")
	@CacheLookup
	static WebElement phone_field;
	
	@FindBy(id="id_submit_button")
	@CacheLookup
	static WebElement submit_button;
	
	@FindBy(id="ErrorLine")
	@CacheLookup
	static WebElement error_line;
	
	@FindBy(id="id_g_radio_01")
	@CacheLookup
	static WebElement radio_button_01;
	
	@FindBy(id="id_g_radio_02")
	@CacheLookup
	static WebElement radio_button_02;
	
	@FindBy(id="id_checkbox")
	@CacheLookup
	static WebElement checkbox;
	
	@FindBy(id="id_state")
	@CacheLookup
	static WebElement state_field;
	
	@FindBy(id="id_quotes")
	@CacheLookup
	static WebElement quotes;
	
	@FindBy(id="id_quotes")
	@CacheLookup
	static WebElement quotes1;
	
	@FindBy(id="id_temperature")
	@CacheLookup
	static WebElement temperature;
	
	@FindBy(id="id_current_location")
	@CacheLookup
	static WebElement location;
	
	@FindBy(id="timestamp")
	@CacheLookup
	static WebElement time;
	
	@FindBy(id="os_browser")
	@CacheLookup
	static WebElement os_browser;
	
	
    /*Constructor*/
    public SignUp_Page(WebDriver wd) {
        driver = wd;
    }
    
    /*This method verifies title of the page*/
	public void verifyTitle(String expectedTitle, String baseUrl){
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		String titleActual = driver.getTitle();
		
		assertEquals(expectedTitle, titleActual);
	}
	
	/*This method verifies that link redirected to the expected page*/
	public void  verifyLink(String titleExpected, String baseUrl, String idWebElement){
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		driver.findElement(By.id(idWebElement)).click();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		ArrayList<String> allTabs = new ArrayList<String>(
				driver.getWindowHandles());
		driver.switchTo().window(allTabs.get(1));
		String titleActual = driver.getTitle();
		driver.close();
		driver.switchTo().window(allTabs.get(0));
		
		assertEquals(titleExpected, titleActual);

	}
	
	/*This method verifies that errors are handled correctly when user doesn't enter or entered
	 *  wrong credentials into the field*/
	public void verify_errorHandling(String fName, String lName, String email,
			String phone, String errorExpected, String baseUrl){
		driver.get(baseUrl); 
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		fname_field.clear();
		fname_field.sendKeys(fName);
		lname_field.clear();
		lname_field.sendKeys(lName);
		email_field.clear();
		email_field.sendKeys(email);
		phone_field.clear();
		phone_field.sendKeys(phone);
		submit_button.click();
						
		if(fName.matches("/^[a-zA-Z,.'-]{3,30}$/")){
			if(lName.matches("/^[a-zA-Z,.'-]{3,30}$/")){
				if(email.matches("/[a-zA-Z0-9]{2,}@([0-9a-zA-Z][-\\w]*\\.)+[a-zA-Z]{2,6}/")){
					if(phone.matches("/^\\(?[\\d]{3}\\)?[\\s-]?[\\d]{3}[\\s-]?[\\d]{4}$/")){
					}else{
						String errorActual = error_line.getText();
						assertEquals(errorExpected,errorActual);
					}
				}else{
					String errorActual = error_line.getText();
					assertEquals(errorExpected,errorActual);
				}
			}else{
				String errorActual = error_line.getText();
				assertEquals(errorExpected,errorActual);
			}
	    }else{
			String errorActual = error_line.getText();
     		assertEquals(errorExpected,errorActual);
		}

	}
	
	/*This method fills up the form with valid credentials and submits it */
	public void submitForm(String fName, String lName,String email, String phone,
			String gender, String state, Boolean terms, String baseUrl, String titleConformPage){
		driver.get(baseUrl); 
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		fname_field.clear();
		fname_field.sendKeys(fName);
		lname_field.clear();
		lname_field.sendKeys(lName);
		email_field.clear();
		email_field.sendKeys(email);
		phone_field.clear();
		phone_field.sendKeys(phone);
		
		if (gender.equalsIgnoreCase("male")){
			radio_button_01.click();
		}else if(gender.equalsIgnoreCase("female")){
			radio_button_02.click();
		}
		if (terms == true){
			checkbox.click();
		}
		if (state.isEmpty()){
			
		}else{
			
			new Select(state_field).selectByVisibleText(state);
		}
		submit_button.click();
		assertEquals(driver.getTitle(), titleConformPage);
		
	}
	
	
	public String verifyContent(WebElement webElementId,String baseUrl){
		
		
		driver.get(baseUrl); 
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		String actualContent = webElementId.getText();
		return actualContent;
		
	}

}
