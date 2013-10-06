package ru.mail.qa.pages;

import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.pages.Pages;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import ru.mail.qa.pages.requirements.Application;
import ru.mail.qa.pages.steps.EndUserSteps;

@Story(Application.Authorization.EnterValidLoginData.class)
@RunWith(ThucydidesRunner.class)
public class EnterValidLoginDataStoryTest {

	@Managed
	public WebDriver webdriver;

	@ManagedPages
	public Pages pages;

	@Steps
	public EndUserSteps endUser;
	
	private String login = "shubin.ks";
	private String password = "pass01word";
	private String domain = "@inbox.ru";
	
	@Test
	public void enter_valid_credentials() {
		endUser.open_home_page();
		endUser.enter_credentials(login, password);
		endUser.choose_domain(domain);
		endUser.login();
		endUser.should_see_personal_account_page("Входящие - " + login + domain +" - Почта Mail.Ru");
	}
	
	@Test
	public void enter_invalid_then_valid_credentials() {
		endUser.open_home_page();
		endUser.enter_credentials("wrongEmail", "wrongPassword");
		endUser.choose_domain("");
		endUser.login();
		endUser.should_see_external_login_page_with_error_message("Неверное имя пользователя или пароль. Проверьте правильность введенных данных.");
		
		endUser.login_should_contains_entered_text("wrongEmail".toLowerCase());
		endUser.enter_credentials_external_page(login, password);
		endUser.choose_domain_external_page(domain);
		endUser.login_external_page();
		endUser.should_see_personal_account_page("Входящие - " + login + domain +" - Почта Mail.Ru");
	}
	
}
