package com.web.pkl;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.web.pkl.model.Post;
import com.web.pkl.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Controller
public class PostController {

  @Autowired
  private PostRepository postRepository;

  @RequestMapping(value="/posts", method = RequestMethod.GET)
  @ResponseBody
  public String posts(){
    JSONObject obj = new JSONObject();

    List<Post> posts = postRepository.findAll();
    JSONArray GroupList = new JSONArray();
    posts.forEach(
        post -> GroupList.add(Arrays.asList(new String[]{post.getTitle(), post.getDescription()}))
    );
    obj.put("data", GroupList);
    return obj.toString();
  }

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
