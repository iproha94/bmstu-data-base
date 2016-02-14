package com.wordpress.ilyaps;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by ilyap on 20.11.2015.
 */
public class DBService {
    private SessionFactory sessionFactory;
    private CityDataSetDAO cityDataSetDAO;

    public DBService() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(CityDataSet.class);
        configuration.addAnnotatedClass(RefereeDataSet.class);

        sessionFactory = configuration.configure(new File("hibernate.cfg.xml")).buildSessionFactory();
    }

    public String getLocalStatus() {
        String status;
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            status = transaction.getStatus().toString();
            transaction.commit();
        }
        return status;
    }

    public void saveCity(CityDataSet citySet) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            CityDataSetDAO dao = new CityDataSetDAO(session);
            dao.save(citySet);
            transaction.commit();
        }
    }

    public CityDataSet readCity(long id) {
        try (Session session = sessionFactory.openSession()) {
            CityDataSetDAO dao = new CityDataSetDAO(session);
            return dao.read(id);
        }
    }

    public RefereeDataSet readReferee(long id) {
        try (Session session = sessionFactory.openSession()) {
            RefereeDataSetDAO dao = new RefereeDataSetDAO(session);
            return dao.read(id);
        }
    }

    public CityDataSet readCityByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            CityDataSetDAO dao = new CityDataSetDAO(session);
            return dao.readByName(name);
        }
    }

    public List<CityDataSet> readAllCity() {
        try (Session session = sessionFactory.openSession()) {
            CityDataSetDAO dao = new CityDataSetDAO(session);
            return dao.readAll();
        }
    }

    public void updateCity(String oldCity, String newCity) {
        try (Session session = sessionFactory.openSession()) {
            CityDataSetDAO dao = new CityDataSetDAO(session);
            CityDataSet city = dao.readByName(oldCity);
            city.setName(newCity);

            Transaction transaction = session.beginTransaction();
            dao.update(city);
            transaction.commit();
        }
    }

    public void deleteCity(String cityName) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            CityDataSetDAO dao = new CityDataSetDAO(session);
            CityDataSet city = dao.readByName(cityName);
            dao.delete(city);
            transaction.commit();
        }
    }

    public List<RefereeDataSet> readCityByRefereeId(String cityName) {
        try (Session session = sessionFactory.openSession()) {
            CityDataSetDAO daoC = new CityDataSetDAO(session);
            RefereeDataSetDAO daoR = new RefereeDataSetDAO(session);

            CityDataSet city = daoC.readByName(cityName);

            return daoR.readRefereeByCity(city);
        }
    }

    public void callCityProc(int id, String name) {
        try (Session session = sessionFactory.openSession()) {
            CityDataSetDAO dao = new CityDataSetDAO(session);
            dao.callCityProc(id, name);
        }
    }

    public void shutdown() {
        sessionFactory.close();
    }

}
