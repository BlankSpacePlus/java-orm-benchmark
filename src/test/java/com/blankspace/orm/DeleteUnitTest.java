package com.blankspace.orm;

import static com.blankspace.orm.util.OrmHelper.DATA_SOURCE;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.blankspace.orm.dao.PersonDaoInterface;
import com.blankspace.orm.dao.PersonHibernateDao;
import com.blankspace.orm.dao.PersonJdbcDao;
import com.blankspace.orm.dao.PersonMyBatisDao;
import com.blankspace.orm.pojo.Person;

public class DeleteUnitTest {

    @Test
    public void deleteByJdbc() {
        PersonDaoInterface jdbcDaoInstance = PersonJdbcDao.getInstance(DATA_SOURCE);
        Person originPerson = jdbcDaoInstance.queryPersonById(501);
        Assertions.assertNotNull(originPerson);
        jdbcDaoInstance.deletePersonById(501);
        Person actualPerson = jdbcDaoInstance.queryPersonById(501);
        Assertions.assertNull(actualPerson);
    }

    @Test
    public void deleteByMyBatis() {
        PersonDaoInterface mybatisDaoInstance = PersonMyBatisDao.getInstance(DATA_SOURCE);
        Person originPerson = mybatisDaoInstance.queryPersonById(501);
        Assertions.assertNotNull(originPerson);
        mybatisDaoInstance.deletePersonById(501);
        Person actualPerson = mybatisDaoInstance.queryPersonById(501);
        Assertions.assertNull(actualPerson);
    }

    @Test
    public void deleteByHibernate() {
        PersonDaoInterface hibernateDaoInstance = PersonHibernateDao.getInstance();
        Person originPerson = hibernateDaoInstance.queryPersonById(501);
        Assertions.assertNotNull(originPerson);
        hibernateDaoInstance.deletePersonById(501);
        Person actualPerson = hibernateDaoInstance.queryPersonById(501);
        Assertions.assertNull(actualPerson);
    }

}
