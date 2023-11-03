package com.blankspace.orm.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.blankspace.orm.pojo.Person;

public class PersonHibernateDao implements PersonDaoInterface {

    private volatile static PersonDaoInterface daoInstance;

    private Configuration config;

    private SessionFactory sessionFactory;

    private PersonHibernateDao() {
        // 创建Configuration对象并加载hibernate.cfg.xml配置文件
        this.config = new Configuration().configure("hibernate-config.xml");
        this.sessionFactory = config.buildSessionFactory();
    }

    public static PersonDaoInterface getInstance() {
        if (daoInstance == null) {
            synchronized (PersonHibernateDao.class) {
                if (daoInstance == null) {
                    daoInstance = new PersonHibernateDao();
                }
            }
        }
        return daoInstance;
    }

    public void close() {
        this.sessionFactory.close();
    }

    @Override
    public void addNewPerson(Person person) {
        // 获取SessionFactory和Session对象
        try (Session session = sessionFactory.openSession()) {
            // 开启事务
            Transaction transaction = session.beginTransaction();
            // 将创建的对象保存到表中
            session.persist(person);
            // 自动刷新缓存
            session.flush();
            // 提交事务
            transaction.commit();
        }
    }

    @Override
    public void deletePersonById(int personId) {
        // 获取SessionFactory和Session对象
        try (Session session = sessionFactory.openSession()) {
            // 开启事务
            Transaction transaction = session.beginTransaction();
            // 获取待删除的Person实例
            Person person = session.get(Person.class, personId);
            // 删除待删除的Person数据
            session.remove(person);
            // 自动刷新缓存
            session.flush();
            // 提交事务
            transaction.commit();
        }
    }

    @Override
    public void updateExistedPerson(Person person) {
        // 获取SessionFactory和Session对象
        try (Session session = sessionFactory.openSession()) {
            // 开启事务
            Transaction transaction = session.beginTransaction();
            // 将待更新的Person数据保存到表中
            session.merge(person);
            // 自动刷新缓存
            session.flush();
            // 提交事务
            transaction.commit();
        }
    }

    @Override
    public Person queryPersonById(int personId) {
        Person person = null;
        // 获取SessionFactory和Session对象
        try (Session session = sessionFactory.openSession()) {
            // 开启事务
            Transaction transaction = session.beginTransaction();
            // 获取目标Person实例
            person = session.get(Person.class, personId);
            // 自动刷新缓存
            session.flush();
            // 提交事务
            transaction.commit();
        }
        return person;
    }

}
