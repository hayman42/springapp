package com.hayman42.springboot.web.dto

import org.junit.jupiter.api.Test

class HelloResponseDtoTest {
    @Test
    fun `data class test`() {
        var name = "test"
        var amount = 100

        var dto = HelloResponseDto(name, amount)
        assert(dto.name == name)
        assert(dto.amount == amount)
        
    }
}