package co.fatboa.filesystem.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Auther: hl
 * @Date: 2018/9/5 08:59
 * @Description: 定时任务处理类
 * @Modified By:
 * @Version 1.0
 */
@Component
public class ScheduleTask {
    @Autowired
    GridFsTemplate gridFsTemplate;

    /**
     * 每天凌晨1点清理垃圾文件
     */
    @Scheduled(cron = "0 0 1 * * * *")
    public void deleteGarbageFiles() {
        this.gridFsTemplate.delete(Query.query(Criteria.where("use").is(null)));
    }
}
