package com.hayman42.springboot.web

import com.hayman42.springboot.Application
import com.hayman42.springboot.domain.posts.Posts
import com.hayman42.springboot.domain.posts.PostsRepository
import com.hayman42.springboot.web.dto.PostsSaveRequestDto
import com.hayman42.springboot.web.dto.PostsUpdateRequestDto
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDateTime
import kotlin.contracts.contract

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [Application::class])
class PostsApiControllerTest {
    @LocalServerPort
    var port: Int = 0

    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    @Autowired
    private lateinit var postsRepository: PostsRepository

    @AfterEach
    fun tearDown() = postsRepository.deleteAll()

    @Test
    fun `save post`() {
        var title = "title"
        var content = "content"
        var requestDto = PostsSaveRequestDto(title, content, "hayman42")
        var url = "http://localhost:${port}/api/v1/posts"
        println(url)

        var responseEntity = restTemplate.postForEntity(url, requestDto, Any::class.java)
        assertEquals(HttpStatus.OK, responseEntity.statusCode)

        var all = postsRepository.findAll()
        assertEquals(title, all[0].title)
        assertEquals(content, all[0].content)
    }

    @Test
    fun `update post`() {
        var savedPost = postsRepository.save(Posts("title", "content", "hayman42"))
        var updateId = savedPost.id
        var expectedTitle = "title2"
        var expectedContent = "content2"

        var requestDto = PostsUpdateRequestDto(expectedTitle, expectedContent)
        var url = "http://localhost:${port}/api/v1/posts/${updateId}"
        println(url)

        var requestEntity = HttpEntity<PostsUpdateRequestDto>(requestDto)
        var responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Any::class.java)

        assertEquals(HttpStatus.OK, responseEntity.statusCode)
        assertNotEquals(0L, responseEntity.body)

        var all = postsRepository.findAll()
        assertEquals(expectedTitle, all[0].title)
        assertEquals(expectedContent, all[0].content)

    }

    @Test
    fun `BaseTimeEntity test`() {
        var now = LocalDateTime.now()
        postsRepository.save(Posts("title", "content", "hayman42"))

        var postsList = postsRepository.findAll()
        var post = postsList[0]
        println("createDate=${post.createdDate} modifiedDate=${post.modifiedDate} now=${now}")

        assert(post.createdDate ?: LocalDateTime.MIN > now)
        assert(post.modifiedDate ?: LocalDateTime.MIN > now)
    }
}