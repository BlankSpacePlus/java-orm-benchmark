package com.blankspace.orm;

import static com.blankspace.orm.util.OrmHelper.DATA_SOURCE;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.blankspace.orm.dao.PersonDaoInterface;
import com.blankspace.orm.dao.PersonHibernateDao;
import com.blankspace.orm.dao.PersonJdbcDao;
import com.blankspace.orm.dao.PersonMyBatisDao;
import com.blankspace.orm.pojo.Person;

public class QueryUnitTest {

    @Test
    public void queryByJdbc() {
        PersonDaoInterface jdbcDaoInstance = PersonJdbcDao.getInstance(DATA_SOURCE);
        Person expectedPerson = new Person(255, "Cassady Stephens", "1-695-201-2887", "aliquet.phasellus@outlook.net", "Integer Aliquam Adipiscing LLP", "820-2640 Tempus Av.");
        Person actualPerson = jdbcDaoInstance.queryPersonById(255);
        Assertions.assertEquals(expectedPerson, actualPerson);
    }

    @Test
    public void queryByMyBatis() {
        PersonDaoInterface mybatisDaoInstance = PersonMyBatisDao.getInstance(DATA_SOURCE);
        Person expectedPerson = new Person(255, "Cassady Stephens", "1-695-201-2887", "aliquet.phasellus@outlook.net", "Integer Aliquam Adipiscing LLP", "820-2640 Tempus Av.");
        Person actualPerson = mybatisDaoInstance.queryPersonById(255);
        Assertions.assertEquals(expectedPerson, actualPerson);
    }

    @Test
    public void queryByHibernate() {
        PersonDaoInterface hibernateDaoInstance = PersonHibernateDao.getInstance();
        Person expectedPerson = new Person(255, "Cassady Stephens", "1-695-201-2887", "aliquet.phasellus@outlook.net", "Integer Aliquam Adipiscing LLP", "820-2640 Tempus Av.");
        Person actualPerson = hibernateDaoInstance.queryPersonById(255);
        Assertions.assertEquals(expectedPerson, actualPerson);
    }

}
