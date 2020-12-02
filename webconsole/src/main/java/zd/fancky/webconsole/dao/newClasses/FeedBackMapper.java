package zd.fancky.webconsole.dao.newClasses;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import zd.fancky.webconsole.model.entity.newClasses.FeedBack;


/*
Mapper加@Repository注解Service层依赖注入ProductMapper就不会报错红丝波浪线（其实没有错）。
如果Mapper找不到看：application.yml文件MyBatis是否配置。
                    Mapper.xml文件包名称，函数名称对应是否对应，Mapper.xml内容是否错误。
 */
/*
 @Repository需要在Spring中配置扫描地址，然后生成Dao层的Bean才能被注入到Service层中。

 @Mapper不需要配置扫描地址，通过xml里面的namespace里面的接口地址，生成了Bean后注入到Service层中。
 */
//@Repository  //@MapperScan("com.example.demo.dao")
@Mapper
@Repository
public interface FeedBackMapper {
//    int deleteByPrimaryKey(Integer id);

    int insert(FeedBack record);

//    int insertSelective(FeedBack record);
//
//    FeedBack selectByPrimaryKey(Integer id);
//
//    int updateByPrimaryKeySelective(FeedBack record);
//
//    int updateByPrimaryKey(FeedBack record);
}