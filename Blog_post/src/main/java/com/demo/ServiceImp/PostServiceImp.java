package com.demo.ServiceImp;

import java.lang.StackWalker.Option;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.Iservices.IPost;
import com.demo.Repo.CategoryRepo;
import com.demo.Repo.PostRepo;
import com.demo.Repo.UserRepo;
import com.demo.dtos.PostDto;
import com.demo.entities.Category;
import com.demo.entities.Post;
import com.demo.entities.User;
import com.demo.gloabalExceptions.ResourseNotFound;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PostServiceImp implements IPost {
	@Autowired
	PostRepo postrepo;

	@Autowired
	ModelMapper mapper;

	@Autowired
	UserRepo userrepo;

	@Autowired
	CategoryRepo catrepo;

	@Override
	public Post createPost(PostDto postdto, Long userid, Long catid) {
		Optional<User> user = userrepo.findById(userid);
		System.out.println("iserid " + userid);
		System.out.println("catid " + catid);

		User persistuser = user.orElseThrow(() -> new ResourseNotFound("user not found"));

		Optional<Category> category = catrepo.findById(catid);

		Category persistcategory = category.orElseThrow(() -> new ResourseNotFound("category not found"));

		Post post = this.mapper.map(postdto, Post.class);
		System.out.println(post);
		post.setImage("default.png");
		post.setAdddate(new Date());
		post.setUser(persistuser);
		post.setCategory(persistcategory);
		Post newpost = this.postrepo.save(post);
		return newpost;
	}

	@Override
	public Post updatepost(PostDto postdtom, Long id) {
		
		
		Post persistpost = getpostById(id);

		persistpost.setAdddate(postdtom.getAdddate());
		persistpost.setCategory(postdtom.getCategory());
		persistpost.setContent(postdtom.getContent());
		persistpost.setImage(postdtom.getImage());
		persistpost.setPostid(id);
		persistpost.setTitle(postdtom.getTitle());
		persistpost.setUser(postdtom.getUser());

		return persistpost;
	}

	@Override
	public void deletepost(Long id) {
		Post post = getpostById(id);
		postrepo.delete(post);

	}

	@Override
	public List<Post> getAllpost() {
		List<Post> list = postrepo.findAll();
		System.out.println(list);

		return list;
	}

	@Override
	public Post getpostById(Long id) {
		Optional<Post> post = postrepo.findById(id);

		Post resultpost = post.orElseThrow(() -> new ResourseNotFound("Post not found"));
		return resultpost;
	}

	@Override
	public List<Post> getpostByCategory(Long categoryid) {

		Optional<Category> category = this.catrepo.findById(categoryid);
		System.out.println("inside service " + categoryid);
		Category result = category.orElseThrow(() -> new ResourseNotFound("Reosurce Not found"));
		List<Post> listpost = postrepo.findByCategory(result);
		return listpost;
	}

	@Override
	public List<Post> getpostByUserid(Long userid) {
		Optional<User> user = userrepo.findById(userid);
		User persistuser = user.orElseThrow(() -> new ResourseNotFound("user not found"));
		System.out.println(userid);
		List<Post> listpost = postrepo.findByUser(persistuser);
		System.out.println(listpost);
		return listpost;
	}

	@Override
	public List<Post> searchpost(String keyword) {

		return null;
	}

}
