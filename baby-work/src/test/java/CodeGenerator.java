import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
public class CodeGenerator {

    @Test
    public void genCode(){
        String moduleName="t";
        //1、创建代码生成器
        AutoGenerator mpg=new AutoGenerator();
        // 2、全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath +  "/src/main/java");
        gc.setAuthor("Adorez");
        gc.setOpen(false);//生成后是否打开资源管理器
         gc.setFileOverride(false);//重新生成时文件是否覆盖
         gc.setServiceName("%sService");//去掉Service接口的首字母I
         gc.setIdType(IdType.ID_WORKER_STR);//主键策略
         gc.setDateType(DateType.ONLY_DATE);//定义生成的实体类中日期类型
         gc.setSwagger2(true);//开启Swagger2模式
         mpg.setGlobalConfig(gc);
         // 3、数据源配置
         DataSourceConfig dsc = new DataSourceConfig();
         dsc.setUrl("jdbc:mysql://127.0.0.1:3306/babyp2p?characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8&rewriteBatchedStatements=true");
         dsc.setDriverName("com.mysql.cj.jdbc.Driver");
         dsc.setUsername("root");
        dsc.setPassword("123456");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);
        // 4、包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(moduleName); //模块名
        pc.setParent("com.baby");
        pc.setController("controller");
        pc.setEntity("pojo");
        pc.setService("service");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);
        // 5、策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude(moduleName + "_\\w*");//设置要映射的表名
        strategy.setNaming(NamingStrategy.underline_to_camel);//数据库表映射到实体的命名策略
        strategy.setTablePrefix(pc.getModuleName()+"_");//设置表前缀不生成
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//数据库表字段映射到实体的命名策略
        strategy.setEntityLombokModel(true);// lombok 模型 @Accessors(chain = true) setter链式操作
        //自动填充
        TableFill createTime = new TableFill("create_time",FieldFill.INSERT);
        ArrayList<TableFill> tableFills=new ArrayList<>();
        tableFills.add(createTime);
        strategy.setTableFillList(tableFills);
        strategy.setVersionFieldName("version");//乐观锁列
        strategy.setRestControllerStyle(true); //restful api风格控制器
        strategy.setControllerMappingHyphenStyle(true); //url中驼峰转连字符
        mpg.setStrategy(strategy);
        // 6、执行
        mpg.execute();
        }
 }
