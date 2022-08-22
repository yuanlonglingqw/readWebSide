package org.example.service.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.dao.ChapterContentDao;
import org.example.domain.Chaptercontent;
import org.example.service.ChapterService;
import org.springframework.stereotype.Service;

@Service
public class ChapterContentServiceImp extends ServiceImpl<ChapterContentDao, Chaptercontent> implements ChapterService {
}
