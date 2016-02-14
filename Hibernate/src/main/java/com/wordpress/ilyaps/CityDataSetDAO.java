package com.wordpress.ilyaps;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;


/**
 * Created by ilyap on 20.11.2015.
 */
public class CityDataSetDAO {
    private Session session;

    public CityDataSetDAO(Session session) {
        this.session = session;
    }

    public void save(CityDataSet citySet) {
        session.save(citySet);
    }

    public CityDataSet read(long id) {
        return session.get(CityDataSet.class, id);
    }

    public CityDataSet readByName(String name) {
        Criteria criteria = session.createCriteria(CityDataSet.class);

        return (CityDataSet) criteria.add(Restrictions.eq("name", name)).uniqueResult();
    }

    public List<CityDataSet> readAll() {
        Criteria criteria = session.createCriteria(CityDataSet.class);
        return (List<CityDataSet>) criteria.list();
    }

    public void update(CityDataSet citySet) {
        session.update(citySet);
    }

    public void delete(CityDataSet citySet) {
        session.delete(citySet);
    }

    public void callCityProc(int id, String name) {
        Query query = session.createSQLQuery(
                "BEGIN UPDATE_NAME_CITY2(:1, :2); END;")
                .setParameter("2", name)
                .setParameter("1", id);

        query.executeUpdate();
    }
}
