package autotests.tests;

import autotests.clients.DuckActionsClient;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import static com.consol.citrus.container.FinallySequence.Builder.doFinally;

@Epic("Тесты на duck-action-controller")
@Feature("Эндпоинт /api/duck/action/fly")
public class DuckActionsTest extends DuckActionsClient {

    @Test(description = "Проверка того, что уточка поплыла")
    @CitrusTest
    public void successfulSwim(@Optional @CitrusResource TestCaseRunner runner) {
        runner.variable("duckId", "123");
        runner.$(doFinally().actions(context ->
                databaseUpdate(runner, "DELETE FROM DUCK WHERE ID=${duckId}")));

        databaseUpdate(runner,"insert into DUCK (id, color, height, material, sound, wings_state)\n" +
                "values (${duckId}, 'orange', 3.0, 'cheese', 'hrum','ACTIVE');");

        duckSwim(runner, "${duckId}");
        validateResponse(runner, "duckActionTest/successfulSwim.json");
    }
}