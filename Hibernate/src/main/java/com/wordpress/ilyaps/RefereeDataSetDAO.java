package com.wordpress.ilyaps;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by ilyap on 21.11.2015.
 */
public class RefereeDataSetDAO {
    private Session session;

    public RefereeDataSetDAO(Session session) {
        this.session = session;
    }

    public RefereeDataSet read(long id) {
        return session.get(RefereeDataSet.class, id);
    }


    public List<RefereeDataSet> readRefereeByCity(CityDataSet city) {
        Criteria criteria = session.createCriteria(RefereeDataSet.class)
                .add(Restrictions.eq("city", city));


        return (List<RefereeDataSet>) criteria.list();
    }

}
