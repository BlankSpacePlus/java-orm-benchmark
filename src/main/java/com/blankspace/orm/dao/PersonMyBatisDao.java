package com.blankspace.orm.dao;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.blankspace.orm.pojo.Person;

public class PersonMyBatisDao implements PersonDaoInterface {

    private volatile static PersonDaoInterface daoInstance;

    private PersonMyBatisDao() {
    }

    public static PersonDaoInterface getInstance() {
        if (daoInstance == null) {
            synchronized (PersonMyBatisDao.class) {
                if (daoInstance == null) {
                    daoInstance = new PersonMyBatisDao();
                }
            }
        }
        return daoInstance;
    }

    @Override
    public void addNewPerson(Person person) {
        // 读取配置文件
        try (InputStream in = Resources.getResourceAsStream("mybatis-config.xml")) {
            // 初始化mybatis，创建SqlSessionFactory类实例
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
            // 创建Session实例
            try (SqlSession session = sqlSessionFactory.openSession()) {
                // 插入新数据
                session.insert("com.blankspace.orm.pojo.Person.add_new_person", person);
                // 提交事务
                session.commit();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePersonById(int personId) {
        // 读取配置文件
        try (InputStream in = Resources.getResourceAsStream("mybatis-config.xml")) {
            // 初始化mybatis，创建SqlSessionFactory类实例
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
            // 创建Session实例
            try (SqlSession session = sqlSessionFactory.openSession()) {
                // 删除数据
                session.delete("com.blankspace.orm.pojo.Person.delete_person_by_id", personId);
                // 提交事务
                session.commit();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateExistedPerson(Person person) {
        // 读取配置文件
        try (InputStream in = Resources.getResourceAsStream("mybatis-config.xml")) {
            // 初始化mybatis，创建SqlSessionFactory类实例
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
            // 创建Session实例
            try (SqlSession session = sqlSessionFactory.openSession()) {
                // 删除数据
                session.delete("com.blankspace.orm.pojo.Person.update_existed_person", person);
                // 提交事务
                session.commit();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Person queryPersonById(int personId) {
        Person person = null;
        // 读取配置文件
        try (InputStream in = Resources.getResourceAsStream("mybatis-config.xml")) {
            // 初始化mybatis，创建SqlSessionFactory类实例
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
            // 创建Session实例
            try (SqlSession session = sqlSessionFactory.openSession()) {
                // 从数据库中检索一条数据
                person = session.selectOne("com.blankspace.orm.pojo.Person.query_person_by_id", personId);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return person;
    }

}
