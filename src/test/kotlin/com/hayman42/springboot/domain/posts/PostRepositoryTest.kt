package com.hayman42.springboot.domain.posts

import com.hayman42.springboot.Application
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [Application::class])
class PostRepositoryTest {
    @Autowired
    private lateinit var postRepository: PostsRepository

    @AfterEach
    fun cleanup() {
        postRepository.deleteAll()
    }

    @Test
    fun `load saved post`() {
        var title = "게시글"
        var content = "본문"

        postRepository.save(
            Posts(
                title = title,
                author = "hayman42",
                content = content
            )
        )

        var postsList = postRepository.findAll()
        var posts = postsList[0]

        Assertions.assertEquals(posts.title, title)
        Assertions.assertNotNull(posts.id)
        Assertions.assertEquals(posts.content, content)
    }
}