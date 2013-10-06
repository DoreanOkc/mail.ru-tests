package ru.mail.qa.pages.requirements;

import net.thucydides.core.annotations.Feature;

public class Application {

	@Feature
    public class Authorization {
        public class ParametrizedEnterInvalidLoginData {}
        public class EnterValidLoginData {}
    }

}
