package ru.mail.qa.pages.steps;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.steps.ScenarioSteps;
import ru.mail.qa.pages.HomePage;
import ru.mail.qa.pages.LoginExternalPage;
import ru.mail.qa.pages.PersonalAccountPage;

@SuppressWarnings("serial")
public class EndUserSteps extends ScenarioSteps{

	public EndUserSteps(Pages pages) {
		super(pages);
	}
	
	private HomePage homePage() {
		return getPages().currentPageAt(HomePage.class);
	}
	
	private LoginExternalPage loginExternalPage() {
		return getPages().currentPageAt(LoginExternalPage.class);
	}
	
	private PersonalAccountPage personalAccountPage() {
		return getPages().currentPageAt(PersonalAccountPage.class);
	}

	@Step
	public void open_home_page() {
		homePage().open();
	}
	
    @Step
    public void enter_credentials(String login, String password) {
    	homePage().enter_login(login);
    	homePage().enter_password(password);
    }
    
    @Step
    public void choose_domain(String domain) {
    	homePage().select_domain(domain);
    }
    
    @Step
    public void login() {
    	homePage().click_login();
    }

    @Step
	public void should_see_external_login_page_with_error_message(String errorMessage) {
    	assertThat(loginExternalPage().getError(), is(errorMessage));
	}
    
    @Step
    public void enter_credentials_external_page(String login, String password) {
    	loginExternalPage().enter_login(login);
    	loginExternalPage().enter_password(password);
    }
    
    @Step
    public void choose_domain_external_page(String domain) {
    	loginExternalPage().select_domain(domain);
    }
    
    @Step
    public void login_external_page() {
    	loginExternalPage().click_login();
    }

	public void login_should_contains_entered_text(String text) {
		assertThat(loginExternalPage().getLoginText(), is(text));
	}

	@Step
	public void should_see_personal_account_page(String title) {
    	assertThat(personalAccountPage().getTitle(), is(title));
	}
}
