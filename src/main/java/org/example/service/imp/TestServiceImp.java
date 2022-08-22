package org.example.service.imp;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.dao.TestDao;
import org.example.domain.Test;
import org.example.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TestServiceImp extends ServiceImpl<TestDao,Test> implements TestService{
    @Autowired
    private TestDao testDao;
    @Override
    public List<Test> selectAll() {
        return testDao.selectAll();
    }
}
