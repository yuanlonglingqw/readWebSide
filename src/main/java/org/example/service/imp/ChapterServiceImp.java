package org.example.service.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.dao.ChapterDao;
import org.example.domain.Chapter;
import org.example.service.ChapterService;
import org.springframework.stereotype.Service;

@Service
public class ChapterServiceImp extends ServiceImpl<ChapterDao, Chapter> implements ChapterService {

}
