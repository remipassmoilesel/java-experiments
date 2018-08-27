package org.remipassmoilesel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.remipassmoilesel.notes.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.CustomAutowireConfigurer;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@JsonTest
public class MyJsonTests {


    @Autowired
    private JacksonTester<Note> json;

    /*

    jsonPath("$", hasSize(4)): count array
    object: jsonPath("$.*", hasSize(4)): count members of an object

     */

    @Test
    public void testSerialize() throws Exception {
        /*
        VehicleDetails details = new VehicleDetails("Honda", "Civic");
        // Assert against a `.json` file in the same package as the test
        assertThat(this.json.write(details)).isEqualToJson("expected.json");
        // Or use JSON path based assertions
        assertThat(this.json.write(details)).hasJsonPathStringValue("@.make");
        assertThat(this.json.write(details)).extractingJsonPathStringValue("@.make")
                .isEqualTo("Honda");
                */
    }

    @Test
    public void testDeserialize() throws Exception {
        /*
        String content = "{\"make\":\"Ford\",\"model\":\"Focus\"}";
        assertThat(this.json.parse(content))
                .isEqualTo(new VehicleDetails("Ford", "Focus"));
        assertThat(this.json.parseObject(content).getMake()).isEqualTo("Ford");
        */
    }

}