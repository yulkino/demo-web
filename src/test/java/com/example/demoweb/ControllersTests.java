package com.example.demoweb;

import com.example.demoweb.model.DateProvider;
import com.example.demoweb.model.Post;
import com.example.demoweb.repository.PostRepository;
import com.example.demoweb.service.PostService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create_before_post_table.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/drop_after_post_table.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ControllersTests {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private DateProvider dateProvider;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void post_create_controller() throws Exception {
        this.mockMvc.perform(post("/new").param("text", "new"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void post_controller_service_database() throws Exception {
        PostService postService = new PostService(postRepository, dateProvider);
        String expectedTest = "new";
        this.mockMvc.perform(post("/new").param("text", expectedTest));
        List<Post> countAllPosts = StreamSupport
                .stream(postService.listAllPosts().spliterator(), false)
                .collect(Collectors.toList());

        Post actualPost = countAllPosts.get(countAllPosts.size() - 1);

        Assert.assertEquals((Long)1L, actualPost.getId());
        Assert.assertEquals(expectedTest, actualPost.getText());

    }
}
