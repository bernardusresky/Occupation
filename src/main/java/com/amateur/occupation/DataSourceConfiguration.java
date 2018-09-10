package com.amateur.occupation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.sqlite.SQLiteDataSource;

import javax.sql.DataSource;


@Slf4j
@Configuration
public class DataSourceConfiguration {
    @Value("${occupation.runtime}")
    private boolean runtime;

    @Bean(destroyMethod = "", name = "EmbeddeddataSource")
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        dataSourceBuilder.driverClassName("org.sqlite.JDBC");
        String classPath = DataSourceConfiguration.class.getResource("/").toString();
        log.info("class .getResource:" + classPath);
//        deploy this path will be wrong
        String relativePath = DataSourceConfiguration.class.getResource("/").getPath();
        String finalPath = null;
        int beginIndex = 1;
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            beginIndex = 1;
        } else {
            beginIndex = 0;
        }
        if (runtime) {
//            if window substring(1),if mac substring(0)
            finalPath = relativePath.substring(beginIndex) + "../../";
        } else {
            finalPath = "src/main/resources/";
        }
        log.debug(relativePath);
        dataSourceBuilder.url("jdbc:sqlite:"
                + finalPath
                + "occupation.db3");
        dataSourceBuilder.type(SQLiteDataSource.class);
        return dataSourceBuilder.build();
    }

//    public static void main(String[] args) {
//        String relativePath = DataSourceConfiguration.class.getResource("/").getPath();
//        System.out.println(relativePath);
//        System.out.println(System.getProperty("os.name").toLowerCase().contains("windows"));
//    }

}