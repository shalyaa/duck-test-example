package autotests.tests;

import autotests.clients.DuckActionsClient;
import autotests.payloads.Duck;
import autotests.payloads.WingState;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.testng.CitrusParameters;
import io.qameta.allure.Description;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class DuckCreateTest extends DuckActionsClient {

    Duck duck1 = new Duck().color("yellow").height(0.1).material("rubber").sound("quack").wingsState(WingState.ACTIVE);
    Duck duck2 = new Duck().color("yellow").height(5).material("rubber").sound("quack").wingsState(WingState.ACTIVE);
    Duck duck3 = new Duck().color("yellow").height(10).material("rubber").sound("quack").wingsState(WingState.ACTIVE);
    Duck duck4 = new Duck().color("yellow").height(0.1).material("rubber").sound("QUACK").wingsState(WingState.ACTIVE);
    Duck duck5 = new Duck().color("yellow").height(0.1).material("rubber").sound("quack").wingsState(WingState.FIXED);


    @Test(dataProvider = "firstCreateTest", description = "")
    @Parameters("runner")
    @CitrusTest
    @CitrusParameters({"body", "response", "runner"})
    @Description("Первый")
    public void firstCreateTest(Object duck, String response, @Optional @CitrusResource TestCaseRunner runner) {
        createDuck(runner, duck);
        validateResponse(runner, response);
    }

    @Test(dataProvider = "secondCreateTest", description = "")
    @Parameters("runner")
    @CitrusTest
    @CitrusParameters({"body", "response", "runner"})
    @Description("Первый")
    public void secondCreateTest(String duck, Duck response, @Optional @CitrusResource TestCaseRunner runner) {
        createDuck(runner, duck);
        validateResponse(runner, response);
    }


    @DataProvider(name = "firstCreateTest")
    public Object[][] firstCreateTest() {
        return new Object[][]{
                {duck1, "DuckCreateTest/createFirstTest/first.json", null},
                {duck2, "DuckCreateTest/createFirstTest/second.json", null},
                {duck3, "DuckCreateTest/createFirstTest/third.json", null},
                {duck4, "DuckCreateTest/createFirstTest/fourth.json", null},
                {duck5, "DuckCreateTest/createFirstTest/fifth.json", null}};
    }

    @DataProvider(name = "secondCreateTest")
    public Object[][] secondCreateTest() {
        return new Object[][]{
                {"DuckCreateTest/createSecondTest/first.json", new Duck().id("@isNumber()@").color("yellow").height(0.1).material("rubber").sound("quack").wingsState(WingState.ACTIVE), null},
                {"DuckCreateTest/createSecondTest/second.json", new Duck().id("@isNumber()@").color("yellow").height(5).material("rubber").sound("quack").wingsState(WingState.ACTIVE), null},
                {"DuckCreateTest/createSecondTest/third.json", new Duck().id("@isNumber()@").color("yellow").height(10).material("rubber").sound("quack").wingsState(WingState.ACTIVE), null},
                {"DuckCreateTest/createSecondTest/fourth.json", new Duck().id("@isNumber()@").color("yellow").height(0.1).material("rubber").sound("QUACK").wingsState(WingState.ACTIVE), null},
                {"DuckCreateTest/createSecondTest/fifth.json", new Duck().id("@isNumber()@").color("yellow").height(0.1).material("rubber").sound("quack").wingsState(WingState.FIXED), null}
        };
    }
}
