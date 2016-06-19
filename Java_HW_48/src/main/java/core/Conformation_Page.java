package core;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Conformation_Page {
	WebDriver driver;
    
    /*Constructor*/
    public Conformation_Page(WebDriver wd) {
        driver = wd;
    }
    
    public void verifyBackbutton(WebElement buttonId, String titleSubmitPage){
    	buttonId.click();
    	assertEquals(driver.getTitle(), titleSubmitPage);
    	
    }
    
    public String verifyContent(WebElement webElementId,String baseUrl){
    		   		
    	String actualContent = webElementId.getText();
    	return actualContent;
    		
    }

}