package at.htl;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class ExampleResourceTest {

    @Test
    public void testskiTeacher() {
        var smt =given()
                .when().get("/skiteacherService/skiTeacher")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();
        assertThat(smt).isEqualTo("[Person mit der id 0', namens Hans Müller, mit dem alter 55, leitet den Kurs Anfänger05-01-2021\n" +
                ", Person mit der id 1', namens Peter Hofer, mit dem alter 50, leitet den Kurs Anfänger05-01-2021\n" +
                ", Person mit der id 2', namens Lisa Müller, mit dem alter 25, leitet den Kurs Anfänger05-01-2021\n" +
                "]");
    }


//    @Test
//    public void testHelloEndpoint() {
//        given()
//          .when().get("/resteasy/hello")
//          .then()
//             .statusCode(200)
//             .body(is("hello"));
//    }

}
