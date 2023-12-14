package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.Iservices.IPost;
import com.demo.dtos.PostDto;
import com.demo.entities.Post;

@RestController
@RequestMapping("/post")
public class PostController {

	@Autowired
	IPost post;

	@PostMapping("/user/{userid}/categoey/{categoryid}/post")
	ResponseEntity<Post> cetarepost(@PathVariable Long userid, @PathVariable Long categoryid,
			@RequestBody PostDto dto) {
		System.out.println(userid);
		System.out.println(categoryid);
		Post postt = this.post.createPost(dto, userid, categoryid);
		return new ResponseEntity<Post>(postt, HttpStatus.OK);

	}

	@GetMapping("/getpost/{id}")
	ResponseEntity<Post> getpostById(@PathVariable Long id) {

		Post result = post.getpostById(id);
		System.out.println(result);

		return new ResponseEntity<Post>(result, HttpStatus.OK);
	}

	@GetMapping("/getbycat/{catid}")
	ResponseEntity<List<Post>> getpostByCategory(@PathVariable Long catid) {
		List<Post> result = post.getpostByCategory(catid);
		System.out.println(catid);
		System.out.println("yes");
		System.out.println(result);
		return new ResponseEntity<List<Post>>(result, HttpStatus.OK);
	}

	@GetMapping("/getbyuserid/{userid}")
	ResponseEntity<List<Post>> getPostByuser(@PathVariable Long userid) {
		System.out.println(userid);
		List<Post> resultpostlist = post.getpostByUserid(userid);
		System.out.println(resultpostlist);
		return new ResponseEntity<List<Post>>(resultpostlist, HttpStatus.OK);
	}

	@GetMapping()
	ResponseEntity<List<Post>> getallpost() {
		System.out.println("inside the al post");
		List<Post> postlist = post.getAllpost();
		System.out.println(postlist);
		return new ResponseEntity<List<Post>>(postlist, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	ResponseEntity<String> deletePost(@PathVariable Long id) {

		post.getpostById(id);
		post.deletepost(id);

		return new ResponseEntity<String>("post deleted " + id, HttpStatus.OK);

	}

	@PutMapping("/update/{id}")
	ResponseEntity<String> updatePost(@RequestBody PostDto postt, @PathVariable Long id) {

		post.getpostById(id);
		post.updatepost(postt, id);
		return new ResponseEntity<String>("post is is updated successfully "+id, HttpStatus.OK);

	}
}
