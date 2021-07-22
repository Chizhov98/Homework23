package Dao;

import Entity.Auto;
import Utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class AutomobileDao {


    public boolean createAuto(Auto auto) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(auto);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    public boolean editAuto(Auto auto) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql = "UPDATE Auto SET title = :title, price = :price, yearOfManufacture = :yearOfManufacture," +
                    "lastSellDate = :lastSellDate, typeOfGear = :typeOfGear, fuelVolume = :fuelVolume WHERE id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("title", auto.getTitle());
            query.setParameter("price", auto.getPrice());
            query.setParameter("yearOfManufacture", auto.getYearOfManufacture());
            query.setParameter("lastSellDate", auto.getLastSellDate());
            query.setParameter("typeOfGear", auto.getTypeOfGear());
            query.setParameter("fuelVolume", auto.getFuelVolume());
            query.setParameter("id", auto.getId());
            query.executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    public Auto getAutoById(int id) {
        List<Auto> autos = new ArrayList<>();
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql = "FROM Auto  WHERE id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            autos = query.list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        if (autos.get(0) != null) {
            return autos.get(0);
        } else {
            return null;
        }
    }

    public List<Auto> getAllAuto() {
        List<Auto> autos = new ArrayList<>();
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql = "FROM Auto";
            Query query = session.createQuery(hql);
            autos = query.list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return autos;
    }

    public List<Auto> getAutoByTitle(String title) {
        List<Auto> autos = new ArrayList<>();
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql = "FROM Auto  WHERE title = :title";
            Query query = session.createQuery(hql);
            query.setParameter("title", title);
            autos = query.list();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return autos;
    }

    public List<Auto> getAllAutoInPriceRange(int min, int max) {
        List autos = new ArrayList<>();
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql = "FROM Auto WHERE price > :min AND price < :max";
            Query query = session.createQuery(hql);
            query.setParameter("min", min - 1);
            query.setParameter("max", max + 1);
            autos = query.list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return autos;
    }

    public void deleteAuto(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Auto auto = session.get(Auto.class, id);
            if (auto != null) {
                transaction = session.beginTransaction();
                String hql = "DELETE FROM Auto  WHERE id = :id";
                Query query = session.createQuery(hql);
                query.setParameter("id", id);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteAllAuto() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql = "DELETE FROM Auto  WHERE id > 0";
            Query query = session.createQuery(hql);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

}


