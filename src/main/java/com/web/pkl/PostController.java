package com.web.pkl;

import com.web.pkl.model.Post;
import com.web.pkl.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PostController {

  @Autowired
  private PostRepository postRepository;

  @RequestMapping(value="/post", method = RequestMethod.POST)
  public String postNew(
      @RequestParam(value = "title")String title,
      @RequestParam(value = "description")String description
  ) {
    Post post = new Post(title = title, description = description);
    postRepository.save(post);
    return "redirect:/";
  }
}
