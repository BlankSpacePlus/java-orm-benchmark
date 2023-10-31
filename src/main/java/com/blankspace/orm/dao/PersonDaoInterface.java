package com.blankspace.orm.dao;

import com.blankspace.orm.pojo.Person;

public interface PersonDaoInterface {

    void addNewPerson(Person person);

    void deletePersonById(int personId);

    void updateExistedPerson(Person person);

    Person queryPersonById(int personId);

}
