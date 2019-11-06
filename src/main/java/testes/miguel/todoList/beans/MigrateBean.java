//package testes.miguel.todoList.beans;
//
//import org.flywaydb.core.Flyway;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.env.Environment;
//import org.springframework.stereotype.Component;
//
//@Component
//public class MigrateBean implements InitializingBean {
//
//    @Autowired
//    private Environment environment;
//
//    @Value("${spring.datasource.url}")
//    private String dataSourceUrl;
//    @Value("${spring.datasource.username}")
//    private String dataSourceUsername;
//    @Value("${spring.datasource.password}")
//    private String dataSourcePassword;
//
//    private void migrateDatabase() {
//        System.out.print(dataSourceUrl);
//        Flyway flyway = Flyway.configure().dataSource(dataSourceUrl, dataSourceUsername , dataSourcePassword).load();
//        flyway.migrate();
//    }
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        migrateDatabase();
//    }
//}
