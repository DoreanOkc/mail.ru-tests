package ru.mail.qa.pages;

import net.thucydides.core.annotations.At;
import net.thucydides.core.pages.PageObject;
import net.thucydides.core.pages.WebElementFacade;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@At("https://e.mail.ru/login")
public class LoginExternalPage extends PageObject {

	@FindBy(xpath = "//div[contains(@class,'external_error login-page__external__text')]")
	private WebElement errorMessage;
	
	@FindBy(xpath = "//input[contains(@class,'external_input_login')]")
	private WebElementFacade loginInput;
	
	@FindBy(xpath = "//input[contains(@class,'external_input__password')]")
	private WebElementFacade passwordInput;
	
	@FindBy(xpath = "//input[contains(@class,'external_submit')]")
	private WebElement loginButton; 
	
	public String getError(){
		return errorMessage.getText();
	}
	
	public void enter_login(String login) {
		loginInput.type(login);
	}
	
	public String getLoginText(){
		return loginInput.getValue();
	}
	
	public void enter_password(String password) {
		passwordInput.type(password);
	}

	public void select_domain(String domain) {
		if(domain.equals("") | domain == null){
			return;
		}
		WebElement domainDropDown = find(By.tagName("select"));
		selectFromDropdown(domainDropDown, domain);
	}
	
	public void click_login() {
		loginButton.click();
	}
	
}
