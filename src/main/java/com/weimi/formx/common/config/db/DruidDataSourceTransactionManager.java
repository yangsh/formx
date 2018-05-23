package com.weimi.formx.common.config.db;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * 自定义事务
 * Created by yangsh on 2017-08-28
 */
@Configuration
@EnableTransactionManagement
@AutoConfigureAfter({MyBatisConfig.class})
@Slf4j
public class DruidDataSourceTransactionManager extends DataSourceTransactionManagerAutoConfiguration {

    @Resource(name = "dataSource")
    private DataSource dataSource;

    /**
     * 自定义事务
     * MyBatis 自动参与到 spring 事务管理中, 无需额外配置, 只要 org.mybatis.spring.SqlSessionFactoryBean 引用的数据源与 DruidDataSourceTransactionManager 引用的数据源一致即可, 否则事务管理会不起作用.
     * @return
     */
    @Bean(name = "transactionManager")
    public DataSourceTransactionManager transactionManager() {
        log.info("druid transactionManager init ...");
        return new DataSourceTransactionManager(dataSource);
    }

}
