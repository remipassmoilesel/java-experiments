package org.remipassmoilesel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.remipassmoilesel.autowiring.EmployeeService;
import org.remipassmoilesel.notes.NotesController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.ReflectionUtils;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by remipassmoilesel on 22/02/17.
 */


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("PROFILE_NAME_VALUE") // add value to profiles
public class SpringExamplesTest {

    private MockMvc mockMvc;

    @Autowired
    private NotesController realNotesController;

    /**
     * TODO: clarify difference between all annotations:     @InjectMocks / Mock / ...
     */
    @InjectMocks
    private NotesController notesController;

    @Mock
    private NotesController employeeController;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(notesController).build();
    }

    @Test
    public void test() throws Exception {

        // reflection utils can be used to inspect classes and call private methods, ...
        ReflectionUtils.findField(SpringExamplesTest.class, "mockMvc");

        System.out.println(mockMvc);
        System.out.println(notesController);    // service dependencies may be null
        System.out.println(realNotesController);

        // test example
        // Warning: controller dependencies can be not injected
        // we have to prepare it before, see
        this.mockMvc.perform(get("/customers/json/all")
                .param("email", "mvcemail@test.com")
                .param("firstName", "mvcfirst")
                .param("lastName", "mvclastname"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("url/to/forward"))
                .andExpect(model().attributeExists("page_error"));


    }

    /**
     * Form example
     *
     * @throws Exception
     */
    @Test
    public void updateEmptyTodo() throws Exception {

        /*
        mockMvc.perform(post("/todo/update")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "1")
                .sessionAttr("todo", new TodoDTO())
        )
                .andExpect(status().isOk())
                .andExpect(view().name("todo/update"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/todo/update.jsp"))
                .andExpect(model().attributeHasFieldErrors("todo", "title"))
                .andExpect(model().attribute("todo", hasProperty("id", is(1L))))
                .andExpect(model().attribute("todo", hasProperty("description", isEmptyOrNullString())))
                .andExpect(model().attribute("todo", hasProperty("title", isEmptyOrNullString())));

        */
    }

    /**
     * JSOn test
     *
     * @throws Exception
     */
    @Test
    public void findAll_TodosFound_ShouldReturnFoundTodoEntries() throws Exception {

        /*
        Todo first = new TodoBuilder()
                .id(1L)
                .description("Lorem ipsum")
                .title("Foo")
                .build();
        Todo second = new TodoBuilder()
                .id(2L)
                .description("Lorem ipsum")
                .title("Bar")
                .build();

        when(todoServiceMock.findAll()).thenReturn(Arrays.asList(first, second));

        mockMvc.perform(get("/api/todo"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].description", is("Lorem ipsum")))
                .andExpect(jsonPath("$[0].title", is("Foo")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].description", is("Lorem ipsum")))
                .andExpect(jsonPath("$[1].title", is("Bar")));

        verify(todoServiceMock, times(1)).findAll();
        verifyNoMoreInteractions(todoServiceMock);
        */
    }

}
