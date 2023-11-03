package com.blankspace.orm;

import static com.blankspace.orm.util.OrmHelper.DATA_SOURCE;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.blankspace.orm.dao.PersonDaoInterface;
import com.blankspace.orm.dao.PersonJdbcDao;
import com.blankspace.orm.pojo.Person;

@TestMethodOrder(OrderAnnotation.class)
public class JdbcUnitTest {

    @Test
    @Order(1)
    public void addByJdbc() {
        PersonDaoInterface jdbcDaoInstance = PersonJdbcDao.getInstance(DATA_SOURCE);
        Person expectedPerson = new Person(501, "Hayes Manning", "(819) 689-3550", "sem.egestas.blandit@google.net", "Mauris Sagittis Placerat LLC", "Ap #549-2739 Eget Rd.");
        jdbcDaoInstance.addNewPerson(expectedPerson);
        Person actualPerson = jdbcDaoInstance.queryPersonById(501);
        Assertions.assertNotNull(actualPerson);
        Assertions.assertEquals(expectedPerson, actualPerson);
    }

    @Test
    @Order(2)
    public void updateByJdbc() {
        PersonDaoInterface jdbcDaoInstance = PersonJdbcDao.getInstance(DATA_SOURCE);
        Person expectedPerson = new Person(501, "Hayes Manning", "(349) 280-4621", "sem.egestas.blandit@google.net", "Enim Etiam Imperdiet LLC", "6381 Semper Ave");
        jdbcDaoInstance.updateExistedPerson(expectedPerson);
        Person actualPerson = jdbcDaoInstance.queryPersonById(501);
        Assertions.assertNotNull(actualPerson);
        Assertions.assertEquals(expectedPerson, actualPerson);
    }

    @Test
    @Order(3)
    public void deleteByJdbc() {
        PersonDaoInterface jdbcDaoInstance = PersonJdbcDao.getInstance(DATA_SOURCE);
        Person originPerson = jdbcDaoInstance.queryPersonById(501);
        Assertions.assertNotNull(originPerson);
        jdbcDaoInstance.deletePersonById(501);
        Person actualPerson = jdbcDaoInstance.queryPersonById(501);
        Assertions.assertNull(actualPerson);
    }

    @Test
    @Order(4)
    public void queryByJdbc() {
        PersonDaoInterface jdbcDaoInstance = PersonJdbcDao.getInstance(DATA_SOURCE);
        Person expectedPerson = new Person(255, "Cassady Stephens", "1-695-201-2887", "aliquet.phasellus@outlook.net", "Integer Aliquam Adipiscing LLP", "820-2640 Tempus Av.");
        Person actualPerson = jdbcDaoInstance.queryPersonById(255);
        Assertions.assertEquals(expectedPerson, actualPerson);
    }

}
