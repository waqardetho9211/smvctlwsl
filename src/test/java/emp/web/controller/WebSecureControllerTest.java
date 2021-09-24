package emp.web.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Under construction
 */
@RunWith(SpringRunner.class)
@Ignore
public class WebSecureControllerTest {

    @Autowired
    MockMvc mockMvc;

    /**
     * Access should be granted from the Server
     */
    @Test
    public void givenUser_whenPerformingGet_thenReturnsIndex() throws Exception {
        mockMvc.perform(get("/").with(user("any_user").password("any_password"))).andExpect(status().isForbidden()).andExpect(view().name("index"));
    }

}