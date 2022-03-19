package com.mycompany.app;

import com.codeborne.selenide.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BTA_test {
    @Before
    public void OpenPage() {
        Configuration.timeout = 8000;//due to my internet speed, it sometimes failed to load the page quickly enough to not fail the test.
        System.out.println("starting test");
        open("http://www.bta.lv/");
        $(byText("Atļaut")).click();
    }

    @Test
    public void testCase() throws InterruptedException {
        $(byText("Ceļojumi")).click();
        $(byId("regionalSelectorRegion-open")).click();
        $(byId("regionalSelectorCountry")).click();
        $(byCssSelector(".text.add-text")).click();
        $(byId("regionalSelectorCountry-typedValue")).setValue("Indija");
        $$(byClassName("dropdown")).findBy(text("Indija")).click();
        $(byId("regionalSelectorCountry-applyButton")).click();
        $(byId("regionalSelectorRegion-open")).shouldHave(text("Visa pasaule"));
        $(byId("travelActivities-open")).click();
        $(byText("Sports")).click();
        $(byId("travelActivities-open")).shouldHave(text("Sports"));
        $(by("data-type", "travelSubmit")).click();
        $(by("datatype", "selectPolicyPlanOPP")).click();
        $$(by("data-broadcast-one-wrapper", "showPolicyPlanCovered")).findBy(text("Apskati, kas ir apdrošināts")).scrollIntoView("{block: \"center\"}");
        Thread.sleep(500); //could not get it to scroll the element in any other way. The element was either not clickable due to the pinned footer on top of the form's button or it just did wait for scrolling animation to finish. Sorry.
        $$(by("data-broadcast-one-wrapper", "showPolicyPlanCovered")).findBy(text("Apskati, kas ir apdrošināts")).click();
        $(byClassName("covered-popup-travel")).shouldBe(visible);
        $(byClassName("popup-close")).click();
        $(by("data-type", "footerSubmit")).click();
        $(byId("travelerFirstName0-text")).shouldBe(visible);
        $(byId("travelerLastName0-text")).shouldBe(visible);
        $(byId("travelerIdentityNumber0-text")).shouldBe(visible);
    }

    @After
    public void tearDown() {
        System.out.println("test finished successfully");
    }
}
