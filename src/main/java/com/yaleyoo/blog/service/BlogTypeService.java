package com.yaleyoo.blog.service;

import com.yaleyoo.blog.persistence.BlogTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by steve on 13/3/19.
 */
@Service
public class BlogTypeService {
    @Autowired
    private BlogTypeRepository blogTypeRepository;


}
