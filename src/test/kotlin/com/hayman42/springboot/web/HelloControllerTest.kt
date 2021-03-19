package com.hayman42.springboot.web

import org.hamcrest.core.Is
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath

@ExtendWith(SpringExtension::class)
@WebMvcTest(controllers = [HelloController::class])
class HelloControllerTest(@Autowired val mockMvc: MockMvc) {
    @Test
    fun `hello return`() {
        var hello = "hello"
        mockMvc.perform(get("/hello"))
            .andExpect(status().isOk)
            .andExpect(content().string(hello))
    }

    @Test
    fun `helloDto return`() {
        var name = "hello"
        var amount = 100
        mockMvc.perform(
            get("/hello/dto")
                .param("name", name).param("amount", amount.toString())
        ).andExpect(status().isOk)
            .andExpect(jsonPath("$.name", Is.`is`(name)))
            .andExpect(jsonPath("$.amount", Is.`is`(amount)))
    }
}