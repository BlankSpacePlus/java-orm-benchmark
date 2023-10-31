package com.blankspace.orm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.blankspace.orm.dao.PersonDaoInterface;
import com.blankspace.orm.dao.PersonHibernateDao;
import com.blankspace.orm.dao.PersonJdbcDao;
import com.blankspace.orm.dao.PersonMyBatisDao;
import com.blankspace.orm.pojo.Person;

public class AddUnitTest {

    @Test
    public void addByJdbc() {
        PersonDaoInterface jdbcDaoInstance = PersonJdbcDao.getInstance();
        Person expectedPerson = new Person(501, "Hayes Manning","(819) 689-3550","sem.egestas.blandit@google.net","Mauris Sagittis Placerat LLC","Ap #549-2739 Eget Rd.");
        jdbcDaoInstance.addNewPerson(expectedPerson);
        Person actualPerson = jdbcDaoInstance.queryPersonById(501);
        Assertions.assertNotNull(actualPerson);
        Assertions.assertEquals(expectedPerson, actualPerson);
    }

    @Test
    public void addByMyBatis() {
        PersonDaoInterface mybatisDaoInstance = PersonMyBatisDao.getInstance();
        Person expectedPerson = new Person(501, "Hayes Manning","(819) 689-3550","sem.egestas.blandit@google.net","Mauris Sagittis Placerat LLC","Ap #549-2739 Eget Rd.");
        mybatisDaoInstance.addNewPerson(expectedPerson);
        Person actualPerson = mybatisDaoInstance.queryPersonById(501);
        Assertions.assertNotNull(actualPerson);
        Assertions.assertEquals(expectedPerson, actualPerson);
    }

    @Test
    public void addByHibernate() {
        PersonDaoInterface hibernateDaoInstance = PersonHibernateDao.getInstance();
        Person expectedPerson = new Person(501, "Hayes Manning","(819) 689-3550","sem.egestas.blandit@google.net","Mauris Sagittis Placerat LLC","Ap #549-2739 Eget Rd.");
        hibernateDaoInstance.addNewPerson(expectedPerson);
        Person actualPerson = hibernateDaoInstance.queryPersonById(501);
        Assertions.assertNotNull(actualPerson);
        Assertions.assertEquals(expectedPerson, actualPerson);
    }

}
