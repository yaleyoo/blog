package com.yaleyoo.blog.service;

import com.yaleyoo.blog.domain.BlogType;
import com.yaleyoo.blog.persistence.BlogTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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

    public BlogType findBlogTypeByType(String type){
        return blogTypeRepository.findByTypeName(type);
    }

    public BlogType insertBlogType(BlogType blogType){
        return blogTypeRepository.save(blogType);
    }

    public long deleteBlogType(String typeName){
        return blogTypeRepository.deleteBlogTypeByTypeName(typeName);
    }
}
