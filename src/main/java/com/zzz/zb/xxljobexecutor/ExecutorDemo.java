package com.zzz.zb.xxljobexecutor;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import com.xxl.job.core.util.ShardingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangjiaw
 * @date 2021-06-03 13:52
 * 号段模式需要借助数据库sql
 * CREATE DATABASE leaf
 * CREATE TABLE `leaf_alloc` (
 *   `biz_tag` varchar(128)  NOT NULL DEFAULT '',
 *   `max_id` bigint(20) NOT NULL DEFAULT '1',
 *   `step` int(11) NOT NULL,
 *   `description` varchar(256)  DEFAULT NULL,
 *   `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
 *   PRIMARY KEY (`biz_tag`)
 * ) ENGINE=InnoDB;
 *
 * insert into leaf_alloc(biz_tag, max_id, step, description) values('leaf-segment-test', 1, 2000, 'Test leaf Segment Mode Get Id')
 * 雪花算法需要借助zookeeper
 */
@Component
public class ExecutorDemo {

    private static Logger logger = LoggerFactory.getLogger(ExecutorDemo.class);

    /**
     * 1、简单任务示例（Bean模式）
     */
    @XxlJob("demoJobHandler")
    public ReturnT<String> demoJobHandler(String param) throws Exception {
        logger.info("XXL-JOB, Hello World.");
        XxlJobLogger.log("XXL-JOB, Hello World.");

        for (int i = 0; i < 5; i++) {
            logger.info("beat at:" + i);
            XxlJobLogger.log("beat at:" + i);
            TimeUnit.SECONDS.sleep(2);
        }
        return ReturnT.SUCCESS;
    }

    /**
     * 2、分片广播任务
     */
    @XxlJob("shardingJobHandler")
    public ReturnT<String> shardingJobHandler(String param) throws Exception {

        // 分片参数
        ShardingUtil.ShardingVO shardingVO = ShardingUtil.getShardingVo();
        logger.info("分片参数：当前分片序号 = {}, 总分片数 = {}", shardingVO.getIndex(), shardingVO.getTotal());
        XxlJobLogger.log("分片参数：当前分片序号 = {}, 总分片数 = {}", shardingVO.getIndex(), shardingVO.getTotal());

        // 业务逻辑
        List<Object> list = new ArrayList<>();
        list.add(1);list.add(2);list.add(3);list.add(4);list.add(5);list.add(6);list.add(7);list.add(8);
        for (int i = 0; i < list.size(); i++) {
            if (i % shardingVO.getTotal() == shardingVO.getIndex()) {
                logger.info("第 {} 片, 命中分片开始处理", i);
                XxlJobLogger.log("第 {} 片, 命中分片开始处理", i);
            } else {
                logger.info("第 {} 片, 忽略", i);
                XxlJobLogger.log("第 {} 片, 忽略", i);
            }
        }

        return ReturnT.SUCCESS;
    }
}
