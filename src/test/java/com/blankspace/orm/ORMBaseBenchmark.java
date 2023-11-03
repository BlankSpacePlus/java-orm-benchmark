package com.blankspace.orm;

import static com.blankspace.orm.util.OrmHelper.DATA_SOURCE;

import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.infra.BenchmarkParams;

import com.blankspace.orm.dao.PersonDaoInterface;
import com.blankspace.orm.dao.PersonHibernateDao;
import com.blankspace.orm.dao.PersonJdbcDao;
import com.blankspace.orm.dao.PersonMyBatisDao;

@State(Scope.Thread)
public class ORMBaseBenchmark {

    @Param({"jdbc", "mybatis", "hibernate"})
    protected String ormFramework;

    protected PersonDaoInterface daoInstance;

    @Setup(Level.Trial)
    public void setup(BenchmarkParams params) {
        switch (this.ormFramework) {
            case "jdbc":
                setupJdbc();
                break;
            case "mybatis":
                setupMyBatis();
                break;
            case "hibernate":
                setupHibernate();
                break;
        }
    }

    @TearDown(Level.Trial)
    public void teardown() {
        switch (this.ormFramework) {
            case "jdbc":
            case "mybatis":
            case "hibernate":
                DATA_SOURCE.close();
                break;
        }
    }

    private void setupJdbc() {
        this.daoInstance = PersonJdbcDao.getInstance(DATA_SOURCE);
    }

    private void setupMyBatis() {
        this.daoInstance = PersonMyBatisDao.getInstance(DATA_SOURCE);
    }

    private void setupHibernate() {
        this.daoInstance = PersonHibernateDao.getInstance();
    }

}
