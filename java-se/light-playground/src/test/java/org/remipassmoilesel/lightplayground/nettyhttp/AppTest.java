
package org.remipassmoilesel.lightplayground.nettyhttp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class AppTest {

    @Test
    public void
    lotto_resource_returns_200_with_expected_id_and_winners() {

        when().
                get("/hello", 0).
                then().
                statusCode(200).
                body("lotto.lottoId", equalTo(5),
                        "lotto.winners.winnerId", hasItems(23, 54));

    }

}
