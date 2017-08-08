package net.teacherdatabase.config;

import net.teacherdatabase.dao.LessonDaoImpl;
import net.teacherdatabase.dao.TeacherDaoImpl;
import net.teacherdatabase.model.Lesson;
import net.teacherdatabase.model.Teacher;
import net.teacherdatabase.service.LessonServiceImpl;
import net.teacherdatabase.service.TeacherServiceImpl;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * Class declares {@link Bean @Bean} methods
 *
 * @author Sekachkin Mikhail
 */
@Configuration
@ComponentScan("net.teacherdatabase")
@EnableWebMvc
@EnableTransactionManagement
public class WebConfig extends WebMvcConfigurerAdapter {

    @Bean(name = "viewResolver")
    public InternalResourceViewResolver getViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/pages/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/teacherstimetable");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }

    @Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean sessionBuilder = new LocalSessionFactoryBean();
        sessionBuilder.setDataSource(dataSource);
        sessionBuilder.setAnnotatedClasses(Teacher.class, Lesson.class);
        sessionBuilder.setHibernateProperties(getHibernateProperties());
        try {
            sessionBuilder.afterPropertiesSet();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sessionBuilder.getObject();
    }

    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        return properties;
    }

    @Autowired
    @Bean(name = "teacherDao")
    public TeacherDaoImpl teacherDao(SessionFactory sessionFactory) {
        TeacherDaoImpl teacherDaoImpl = new TeacherDaoImpl();
        teacherDaoImpl.setSessionFactory(sessionFactory);
        return teacherDaoImpl;
    }

    @Autowired
    @Bean(name = "teacherService")
    public TeacherServiceImpl teacherService(SessionFactory sessionFactory) {
        TeacherServiceImpl teacherServiceImpl = new TeacherServiceImpl();
        teacherServiceImpl.setTeacherDao(teacherDao(sessionFactory));
        return teacherServiceImpl;
    }

    @Autowired
    @Bean(name = "lessonDao")
    public LessonDaoImpl lessonDao(SessionFactory sessionFactory) {
        LessonDaoImpl lessonDao = new LessonDaoImpl();
        lessonDao.setSessionFactory(sessionFactory);
        return lessonDao;
    }

    @Autowired
    @Bean(name = "lessonService")
    public LessonServiceImpl lessonService(SessionFactory sessionFactory) {
        LessonServiceImpl lessonService = new LessonServiceImpl();
        lessonService.setLessonDao(lessonDao(sessionFactory));
        return lessonService;
    }

    @Autowired
    @Bean(name = "transactionManager")
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
        return transactionManager;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
