package com.leyou.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsPageServiceTest {

    @Autowired
    FileService fileService;

    @Test
    public void loadItem() throws Exception {
        fileService.createHtml(141L);
    }
}
