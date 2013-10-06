package ru.mail.qa.pages;

import java.util.Arrays;
import java.util.Collection;

import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.pages.Pages;
import net.thucydides.junit.annotations.Concurrent;
import net.thucydides.junit.annotations.TestData;
import net.thucydides.junit.runners.ThucydidesParameterizedRunner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import ru.mail.qa.pages.requirements.Application;
import ru.mail.qa.pages.steps.EndUserSteps;

@Story(Application.Authorization.ParametrizedEnterInvalidLoginData.class)
@RunWith(ThucydidesParameterizedRunner.class)
@Concurrent
public class ParametrizedEnterInvalidLoginDataStoryTest {

	@Managed
	public WebDriver webdriver;

	@ManagedPages
	public Pages pages;

	@Steps
	public EndUserSteps endUser;

	@TestData
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
        		{"shubin.ks", "pass01word", "@list.ru"},
        		{"shubin.ks", "wrongPassword", "@inbox.ru"},
        		{"wrongEmail", "pass01word", "@inbox.ru"},
                {"", "pass01word", "@inbox.ru"},
                {"shubin.ks", "", "@inbox.ru"},
                {"", "", "@bk.ru"},
                {"", "", ""},
        });
    }
    
    private String login;
    private String password;
    private String domain;
    private String errorMessage = "Неверное имя пользователя или пароль. Проверьте правильность введенных данных.";
 
    public ParametrizedEnterInvalidLoginDataStoryTest(String login, String password, String domain) {
        this.login = login;
        this.password = password;
        this.domain = domain;
    }
	
	@Test
	public void enter_invalid_credentials() {
		endUser.open_home_page();
		endUser.enter_credentials(login, password);
		endUser.choose_domain(domain);
		endUser.login();
		endUser.should_see_external_login_page_with_error_message(errorMessage);
	}

}
