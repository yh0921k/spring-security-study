package com.security.account;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void index_anonymous() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/").with(anonymous())).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void index_user() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/").with(user("userA").roles("USER")))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void admin_user() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/admin").with(user("userA").roles("USER")))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    public void admin_admin() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/admin").with(user("adminA").roles("ADMIN")))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    public void index_anonymous_annotation() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "userA", roles = "USER")
    public void index_user_annotation() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithCustomUser
    public void admin_user_annotation() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/admin"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "adminA", roles = "ADMIN")
    public void admin_admin_annotation() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/admin"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}