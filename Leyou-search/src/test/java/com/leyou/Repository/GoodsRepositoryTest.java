package com.leyou.Repository;


import com.leyou.client.GoodsClient;
import com.leyou.common.pojo.PageResult;
import com.leyou.item.bo.SpuBo;
import com.leyou.pojo.Goods;
import com.leyou.service.IndexService;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;


@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsRepositoryTest {


    @Autowired
    private  GoodsRepository goodsRepository;

    @Autowired
    private  ElasticsearchTemplate elasticsearchTemplate;


    @Autowired
    private GoodsClient goodsClient;

    @Autowired
    private IndexService indexService;


    /**
     * 创建索引库
     * GET /goods 得到GOODS中对应的索引字段 便是成功
     */
    @Test
    public void testCreateIndex(){
        elasticsearchTemplate.createIndex(Goods.class);
        elasticsearchTemplate.putMapping(Goods.class);

    }

    /**
     * 把所有商品信息导入索引库
     * GET /goods/_search
     * {
     *   "query": {"match_all": {}}
     * }
     */
    @Test
    public void loadData() {
        //从第一页到最后一页,每次查询100条记录并导入
        int page = 1;
        int rows = 100;
        do {
            //查出所有商品
            PageResult<SpuBo> spuBos = goodsClient.queryGoodsByPage(null, true, page, rows);
            if (CollectionUtils.isEmpty(spuBos.getItems())){
                break;
            }
            //取出每一个spu转换为Goods索引对象
            List<Goods> goods = spuBos.getItems().stream().map(indexService::buildGoods).collect(Collectors.toList());
            //导入索引库
            goodsRepository.saveAll(goods);
            //翻页
            page++;
        }while (rows==100);
    }



}
