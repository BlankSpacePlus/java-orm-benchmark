package com.blankspace.orm.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.blankspace.orm.pojo.Person;

@Mapper
public interface PersonMapper {

    @Insert("insert into `person` values (#{id}, #{name}, #{phone}, #{email}, #{company}, #{address});")
    void addNewPerson(Person person);

    @Delete("delete from `person` where id=#{personId};")
    void deletePersonById(int personId);

    @Update("update `person` set name=#{name}, phone=#{phone}, email=#{email}, company=#{company}, address=#{address} where id=#{id};")
    void updateExistedPerson(Person person);

    @Select("select * from `person` where id = #{personId};")
    Person queryPersonById(int personId);

}
