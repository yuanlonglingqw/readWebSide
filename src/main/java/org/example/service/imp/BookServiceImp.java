package org.example.service.imp;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.dao.BookDao;
import org.example.domain.Book;
import org.example.service.BookService;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImp extends ServiceImpl<BookDao,Book> implements BookService {

}
