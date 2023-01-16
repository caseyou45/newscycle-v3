package com.java.newscycle;

import com.java.newscycle.repository.ArticleRepository;
import com.java.newscycle.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NewscycleApplicationTests {

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    CommentRepository commentRepository;

    @Test
    void test() {


    }
}
