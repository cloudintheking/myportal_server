package co.fatboa.backsystem.service.Impl;

import co.fatboa.backsystem.dao.IOptionsDao;
import co.fatboa.backsystem.domain.entity.Option;
import co.fatboa.backsystem.domain.params.OptionParam;
import co.fatboa.backsystem.service.IOptionService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;


/**
 * @Auther: hl
 * @Date: 2018/9/2 18:02
 * @Description: 其他配置服务实现类
 * @Modified By:
 * @Version 1.0
 */
@Service
public class OptionService implements IOptionService {
    @Autowired
    private IOptionsDao optionsDao;
    @Autowired
    private GridFsTemplate gridFsTemplate;
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 获取配置
     *
     * @return
     */
    @Override
    public Option getOption() {
        Option option = this.optionsDao.findOne(new Query());
        if (option == null) {
            option = this.optionsDao.save(new Option());
        }
        return option;
    }

    /**
     * 更新配置
     *
     * @param param
     */
    @Override
    public void update(OptionParam param) throws Exception {
        Query query = new Query();
        if (param.getId() == null || !param.getId().trim().isEmpty()) {
            throw new Exception("更新配置时，id不能为空");
        } else {
            if (this.optionsDao.findById(param.getId().trim()) == null) {
                throw new Exception("不存在id=" + param.getId() + "的配置");
            }
            query.addCriteria(Criteria.where("id").is(new ObjectId(param.getId().trim())));
        }
        Update update = new Update();
        if (param.getDeleteImg() != null) {
            update.pull("headimgs", param.getDeleteImg());//移除图片数组
            this.gridFsTemplate.delete(Query.query(Criteria.where("_id").is(new ObjectId(param.getDeleteImg().trim()))));//删除文件
        }
        if (param.getAddImg() != null) {
            update.push("headimgs", param.getAddImg());//加入图片数组
            this.mongoTemplate.upsert(Query.query(Criteria.where("_id").is(new ObjectId(param.getAddImg().trim()))), new Update().set("use", true), "fs.files");//添加文件引用
        }
        if (param.getAbout() != null && !param.getAbout().trim().isEmpty()) {
            update.set("about", param.getAbout());
        }
        if (param.getContact() != null && !param.getContact().trim().isEmpty()) {
            update.set("contact", param.getContact());
        }
        if (param.getCopyright() != null && !param.getCopyright().trim().isEmpty()) {
            update.set("copyright", param.getCopyright());
        }
        this.optionsDao.update(query, update);
    }
}
