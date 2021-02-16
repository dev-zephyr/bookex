package com.zephyr.bookex.web.domain.posts;

import com.zephyr.bookex.domain.posts.Posts;
import com.zephyr.bookex.domain.posts.PostsRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    private PostsRepository postsRepository;

    /*
    테스트 종료 후 실행하는 메소드(AOP)
    여러 테스트가 동시에 수행되면 H2에 데이터가 그대로 남아 있어
    다음 테스트 실행 시 테스트 실패할 수 있음
    */
    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void read() {

        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("zephyr@gmail.com")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts post = postsList.get(0);

        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getContent()).isEqualTo(content);


    }



}
