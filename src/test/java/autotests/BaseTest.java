package autotests;

import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.message.MessageType;
import com.consol.citrus.message.builder.ObjectMappingPayloadBuilder;
import com.consol.citrus.testng.spring.TestNGCitrusSpringSupport;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static com.consol.citrus.http.actions.HttpActionBuilder.http;

public class BaseTest extends TestNGCitrusSpringSupport {

    protected void sendGetRequest(TestCaseRunner runner, String path, HttpClient URL) {
        runner.$(http().client(URL)
                .send()
                .get(path));
    }

    protected void sendPutRequest(TestCaseRunner runner, String path, HttpClient URL) {
        runner.$(http().client(URL)
                .send()
                .put(path));
    }

    protected void sendDeleteRequest(TestCaseRunner runner, String path, HttpClient URL) {
        runner.$(http().client(URL)
                .send()
                .delete(path));
    }

    protected void sendPostRequest(TestCaseRunner runner, String path, HttpClient URL, Object userData) {
        runner.$(http().client(URL)
                .send()
                .post(path)
                .message()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(new ObjectMappingPayloadBuilder(userData, new ObjectMapper())));
    }

    //Функция для отправки запросов с телом json из файла ресурсов по указанному пути
    protected void sendPostRequest(TestCaseRunner runner, String path, HttpClient URL, String body) {
        runner.$(http().client(URL)
                .send()
                .post(path)
                .message()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(new ClassPathResource(body)));
    }

    //Функция валидации всего json ответа с имеющимся в файле по указанному пути
    public void validateFullResponse(TestCaseRunner runner, String expectedPayload, HttpClient URL) {
        runner.$(http().client(URL)
                .receive()
                .response()
                .message()
                .type(MessageType.JSON)
                .body(new ClassPathResource(expectedPayload)));
    }

    public void validateFullResponseFromTestData(TestCaseRunner runner, Object expectedPayload, HttpClient URL) {
        runner.$(http().client(URL)
                .receive()
                .response()
                .message()
                .type(MessageType.JSON)
                .body(new ObjectMappingPayloadBuilder(expectedPayload, new ObjectMapper())));
    }
}
