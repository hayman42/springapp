package com.hayman42.springboot.web

import com.hayman42.springboot.service.PostsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class IndexController {
    @Autowired
    private lateinit var postService: PostsService

    @GetMapping("/")
    fun index(model: Model): String {
        model.addAttribute("posts", postService.findAllDesc())
        return "index"
    }

    @GetMapping("/posts/save")
    fun postsSave(): String {
        return "posts-save"
    }

    @GetMapping("/posts/update/{id}")
    fun postsUpdate(@PathVariable id: Long, model: Model): String {
        var dto = postService.findById(id)
        model.addAttribute("post", dto)

        return "posts-update"
    }

}