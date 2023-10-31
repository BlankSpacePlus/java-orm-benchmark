package com.blankspace.orm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.blankspace.orm.dao.PersonDaoInterface;
import com.blankspace.orm.dao.PersonHibernateDao;
import com.blankspace.orm.dao.PersonJdbcDao;
import com.blankspace.orm.dao.PersonMyBatisDao;
import com.blankspace.orm.pojo.Person;

public class UpdateUnitTest {

    @Test
    public void updateByJdbc() {
        PersonDaoInterface jdbcDaoInstance = PersonJdbcDao.getInstance();
        Person expectedPerson = new Person(501, "Hayes Manning","(349) 280-4621","sem.egestas.blandit@google.net","Enim Etiam Imperdiet LLC","6381 Semper Ave");
        jdbcDaoInstance.updateExistedPerson(expectedPerson);
        Person actualPerson = jdbcDaoInstance.queryPersonById(501);
        Assertions.assertNotNull(actualPerson);
        Assertions.assertEquals(expectedPerson, actualPerson);
    }

    @Test
    public void updateByMyBatis() {
        PersonDaoInterface mybatisDaoInstance = PersonMyBatisDao.getInstance();
        Person expectedPerson = new Person(501, "Hayes Manning","(349) 280-4621","sem.egestas.blandit@google.net","Enim Etiam Imperdiet LLC","6381 Semper Ave");
        mybatisDaoInstance.updateExistedPerson(expectedPerson);
        Person actualPerson = mybatisDaoInstance.queryPersonById(501);
        Assertions.assertNotNull(actualPerson);
        Assertions.assertEquals(expectedPerson, actualPerson);
    }

    @Test
    public void updateByHibernate() {
        PersonDaoInterface hibernateDaoInstance = PersonHibernateDao.getInstance();
        Person expectedPerson = new Person(501, "Hayes Manning","(349) 280-4621","sem.egestas.blandit@google.net","Enim Etiam Imperdiet LLC","6381 Semper Ave");
        hibernateDaoInstance.updateExistedPerson(expectedPerson);
        Person actualPerson = hibernateDaoInstance.queryPersonById(501);
        Assertions.assertNotNull(actualPerson);
        Assertions.assertEquals(expectedPerson, actualPerson);
    }

}
