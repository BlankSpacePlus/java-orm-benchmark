package com.blankspace.orm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.blankspace.orm.dao.PersonDaoInterface;
import com.blankspace.orm.dao.PersonHibernateDao;
import com.blankspace.orm.pojo.Person;

@TestMethodOrder(OrderAnnotation.class)
public class HibernateUnitTest {

    @Test
    @Order(1)
    public void addByHibernate() {
        PersonDaoInterface hibernateDaoInstance = PersonHibernateDao.getInstance();
        Person expectedPerson = new Person(501, "Hayes Manning", "(819) 689-3550", "sem.egestas.blandit@google.net", "Mauris Sagittis Placerat LLC", "Ap #549-2739 Eget Rd.");
        hibernateDaoInstance.addNewPerson(expectedPerson);
        Person actualPerson = hibernateDaoInstance.queryPersonById(501);
        Assertions.assertNotNull(actualPerson);
        Assertions.assertEquals(expectedPerson, actualPerson);
    }

    @Test
    @Order(2)
    public void updateByHibernate() {
        PersonDaoInterface hibernateDaoInstance = PersonHibernateDao.getInstance();
        Person expectedPerson = new Person(501, "Hayes Manning", "(349) 280-4621", "sem.egestas.blandit@google.net", "Enim Etiam Imperdiet LLC", "6381 Semper Ave");
        hibernateDaoInstance.updateExistedPerson(expectedPerson);
        Person actualPerson = hibernateDaoInstance.queryPersonById(501);
        Assertions.assertNotNull(actualPerson);
        Assertions.assertEquals(expectedPerson, actualPerson);
    }

    @Test
    @Order(3)
    public void deleteByHibernate() {
        PersonDaoInterface hibernateDaoInstance = PersonHibernateDao.getInstance();
        Person originPerson = hibernateDaoInstance.queryPersonById(501);
        Assertions.assertNotNull(originPerson);
        hibernateDaoInstance.deletePersonById(501);
        Person actualPerson = hibernateDaoInstance.queryPersonById(501);
        Assertions.assertNull(actualPerson);
    }

    @Test
    @Order(4)
    public void queryByHibernate() {
        PersonDaoInterface hibernateDaoInstance = PersonHibernateDao.getInstance();
        Person expectedPerson = new Person(255, "Cassady Stephens", "1-695-201-2887", "aliquet.phasellus@outlook.net", "Integer Aliquam Adipiscing LLP", "820-2640 Tempus Av.");
        Person actualPerson = hibernateDaoInstance.queryPersonById(255);
        Assertions.assertEquals(expectedPerson, actualPerson);
    }

}
