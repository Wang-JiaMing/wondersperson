package com.wondersgroup.service;

import com.wondersgroup.dao.NewsRepository;
import com.wondersgroup.model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @projectName:wondersperson
 * @packageName:com.wondersgroup.service
 * @authorName:wangjiaming
 * @createDate:2018-02-11
 * @editor:IntelliJ IDEA
 * @other:
 **/
@Service
public class NewsService {

    private final static Integer NEWSSIZE=10;

    @Autowired
    NewsRepository newsRepository;


    public List<News> getTop10(){
        List<News> newsList=new ArrayList<News>(10);
        String maxBatch=newsRepository.findByMaxBatchNo();
        System.out.println(maxBatch);
        List<News> tmpNewsList=newsRepository.findByBatchNo(maxBatch);
        if(tmpNewsList.size()>NEWSSIZE){

        }else{

        }

        return null;
    }
}
