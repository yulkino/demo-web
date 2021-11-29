package com.example.demoweb;

import com.example.demoweb.controller.PostsCreateController;
import com.example.demoweb.model.DateProvider;
import com.example.demoweb.model.Post;
import com.example.demoweb.repository.PostRepository;
import com.example.demoweb.service.LikesService;
import com.example.demoweb.service.PostService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTests {

    @MockBean
    private PostRepository postRepository;

    @MockBean
    private DateProvider dateProvider;

    @Test
    public void create_post_test(){
        PostService postService = new PostService(postRepository, dateProvider);
        String text = "This is a test text";
        Date date = new Date();
        Mockito.doReturn(date).when(dateProvider).getDate();
        Post post = new Post(null, text, date);
        postService.create(text);
        Mockito.verify(postRepository, Mockito.times(1))
                .save(Mockito.argThat(x ->
                        Objects.equals(x.getId(), post.getId())
                                && Objects.equals(x.getCreationDate(), post.getCreationDate())
                                && Objects.equals(x.getText(), post.getText())));
    }

    @Test
    public void add_like_test(){
        Post post = new Post(null, "text", new Date());
        Mockito.when(postRepository.findById(Mockito.any())).thenReturn(Optional.of(post));

        LikesService likesService = new LikesService(postRepository);

        long oldLikes = post.getLikes();
        likesService.like(Mockito.any());
        long newLikes = post.getLikes();

        Assert.assertEquals(oldLikes + 1, newLikes);
    }

}
