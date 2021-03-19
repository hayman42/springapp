package com.hayman42.springboot.web.dto

import com.hayman42.springboot.domain.posts.Posts

data class PostsSaveRequestDto(
    var title: String,
    var content: String,
    var author: String
) {
    fun toEntity(): Posts {
        return Posts(title, content, author)
    }
}
