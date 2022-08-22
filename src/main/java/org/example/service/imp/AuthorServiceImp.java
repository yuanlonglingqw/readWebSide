package org.example.service.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.dao.AuthorDao;
import org.example.domain.Author;
import org.example.service.AuthorService;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImp extends ServiceImpl<AuthorDao, Author> implements AuthorService {
}
