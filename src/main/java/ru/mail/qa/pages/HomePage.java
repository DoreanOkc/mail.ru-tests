package ru.mail.qa.pages;

import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import net.thucydides.core.pages.WebElementFacade;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@DefaultUrl("http://www.mail.ru")
public class HomePage extends PageObject {

	@FindBy(id = "mailbox__login")
	private WebElementFacade loginInput;
	
	@FindBy(id = "mailbox__password")
	private WebElementFacade passwordInput;
	
	@FindBy(id = "mailbox__auth__button")
	private WebElement loginButton;

	public HomePage(WebDriver driver) {
		super(driver);
	}

	public void enter_login(String login) {
		loginInput.type(login);
	}
	
	public void enter_password(String password) {
		passwordInput.type(password);
	}

	public void select_domain(String domain) {
		if(domain.equals("") | domain == null){
			return;
		}
		WebElement domainDropDown = find(By.id("mailbox__login__domain"));
		selectFromDropdown(domainDropDown, domain);
	}
	
	public void click_login() {
		loginButton.click();
	}
}
