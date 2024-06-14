package autotests.clients;

import autotests.BaseTest;
import autotests.EndpointConfig;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.message.builder.ObjectMappingPayloadBuilder;
import com.consol.citrus.testng.spring.TestNGCitrusSpringSupport;
import com.consol.citrus.http.client.HttpClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.test.context.ContextConfiguration;

import static com.consol.citrus.actions.ExecuteSQLAction.Builder.sql;
import static com.consol.citrus.actions.ExecuteSQLQueryAction.Builder.query;
import static com.consol.citrus.dsl.MessageSupport.MessageBodySupport.fromBody;
import static com.consol.citrus.http.actions.HttpActionBuilder.http;
import static org.eclipse.jetty.util.LazyList.contains;

@ContextConfiguration(classes = {EndpointConfig.class})
public class DuckActionsClient extends BaseTest {

    @Autowired
    protected HttpClient yellowDuckService;

    @Autowired
    protected SingleConnectionDataSource db;

    @Step("Обновление БД")
    public void databaseUpdate(TestCaseRunner runner, String sql) {
        runner.$(sql(db)
                .statement(sql));
    }

    @Step("Эндпоинт, заставляющий утку плыть")
    public void duckSwim(TestCaseRunner runner, String id) {
        sendGetRequest(runner, "/api/duck/actions/swim?id=${duckId}", yellowDuckService);
    }

    @Step("Проверка полученного ответа")
    public void validateResponse(TestCaseRunner runner, String response) {
        validateFullResponse(runner, response, yellowDuckService, "$.id", "duckId");
    }

    @Step("Валидация параметров уточки в БД.")
    public void validateDuckInDatabase(TestCaseRunner runner, String id, String color, String height, String material,
                                       String sound, String wingsState) {
        runner.$(query(db)
                .statement("SELECT * FROM DUCK WHERE ID =" + id)
                .validate("COLOR", color)
                .validate("HEIGHT", height)
                .validate("MATERIAL", material)
                .validate("SOUND", sound)
                .validate("WINGS_STATE", wingsState)
        );
    }

    @Step("Проверка полученного ответа")
    public void validateResponse(TestCaseRunner runner, Object expectedPayload) {
        validateFullResponseFromTestData(runner, expectedPayload, yellowDuckService);
    }

    @Step("Создание утки")
    public void createDuck(TestCaseRunner runner, String body) {
        sendPostRequest(runner, "/api/duck/create", yellowDuckService, body);
    }

    protected void createDuck(TestCaseRunner runner, Object userData) {
        sendPostRequest(runner, "/api/duck/create", yellowDuckService, userData);
    }
}
