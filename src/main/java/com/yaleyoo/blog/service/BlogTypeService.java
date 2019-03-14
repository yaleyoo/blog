package com.yaleyoo.blog.service;

import com.yaleyoo.blog.domain.Blog;
import com.yaleyoo.blog.domain.BlogDTO;
import com.yaleyoo.blog.domain.BlogType;
import com.yaleyoo.blog.exception.BlogTypeNotFoundException;
import com.yaleyoo.blog.persistence.BlogTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

/**
 * Created by steve on 13/3/19.
 */
@Service
public class BlogTypeService {
    @Autowired
    private BlogTypeRepository blogTypeRepository;

    public List<BlogType> findAllBlogType(){
        return blogTypeRepository.findAll();
    }

    public BlogType findBlogTypeByType(String type) throws BlogTypeNotFoundException{
        return blogTypeRepository.findByTypeName(type).orElseThrow(BlogTypeNotFoundException::new);
    }

    public BlogType insertBlogType(BlogType blogType){
        return blogTypeRepository.save(blogType);
    }

    public long deleteBlogType(String typeName){
        return blogTypeRepository.deleteBlogTypeByTypeName(typeName);
    }

    @Transactional
    public BlogType updateBlogTypeWhileInsert(Blog affectedBlog){
        BlogType blogType = blogTypeRepository.findByTypeName(affectedBlog.getType()).get();
        blogType.getBlogList().add(new BlogDTO(affectedBlog));
        return blogTypeRepository.save(blogType);
    }

    @Transactional
    public BlogType updateBlogTypeWhileDelete(Blog affectedBlog){
        BlogType blogType = blogTypeRepository.findByTypeName(affectedBlog.getType()).get();
        blogType.getBlogList().remove(new BlogDTO(affectedBlog));
        return blogTypeRepository.save(blogType);
    }

    @Transactional
    public BlogType renameBlogType(String typeName, String newTypeName){
        BlogType blogType = blogTypeRepository.findByTypeName(typeName).get();
        blogType.setTypeName(newTypeName);
        return blogTypeRepository.save(blogType);
    }
}
