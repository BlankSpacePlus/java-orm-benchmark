package com.blankspace.orm.dao;

import java.io.File;
import java.io.FileInputStream;

import javax.sql.DataSource;

import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import com.blankspace.orm.pojo.Person;

public class PersonMyBatisDao implements PersonDaoInterface {

    private volatile static PersonDaoInterface daoInstance;

    private final SqlSessionFactory sqlSessionFactory;

    private PersonMyBatisDao(DataSource dataSource) {
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration conf = new Configuration(environment);
        String mapperLocation = "src/main/resources/";
        File mappersFile = new File(mapperLocation);
        File[] mappers = mappersFile.listFiles(pathname -> pathname.getName().endsWith("Mapper.xml"));
        if (mappers != null) {
            for (File mapper : mappers) {
                try {
                    FileInputStream is = new FileInputStream(mapper);
                    XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(is, conf, mapper.getAbsolutePath(), conf.getSqlFragments());
                    xmlMapperBuilder.parse();
                    is.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(conf);
    }

    public static PersonDaoInterface getInstance(DataSource dataSource) {
        if (daoInstance == null) {
            synchronized (PersonMyBatisDao.class) {
                if (daoInstance == null) {
                    daoInstance = new PersonMyBatisDao(dataSource);
                }
            }
        }
        return daoInstance;
    }

    @Override
    public void addNewPerson(Person person) {
        try (SqlSession session = this.sqlSessionFactory.openSession()) {
            // 插入新数据
            session.insert("com.blankspace.orm.pojo.Person.addNewPerson", person);
            // 提交事务
            session.commit();
        }
    }

    @Override
    public void deletePersonById(int personId) {
        try (SqlSession session = this.sqlSessionFactory.openSession()) {
            // 删除数据
            session.delete("com.blankspace.orm.pojo.Person.deletePersonById", personId);
            // 提交事务
            session.commit();
        }
    }

    @Override
    public void updateExistedPerson(Person person) {
        try (SqlSession session = this.sqlSessionFactory.openSession()) {
            // 删除数据
            session.delete("com.blankspace.orm.pojo.Person.updateExistedPerson", person);
            // 提交事务
            session.commit();
        }
    }

    @Override
    public Person queryPersonById(int personId) {
        Person person;
        // 创建Session实例
        try (SqlSession session = this.sqlSessionFactory.openSession()) {
            // 从数据库中检索一条数据
            person = session.selectOne("com.blankspace.orm.pojo.Person.queryPersonById", personId);
        }
        return person;
    }

}
