package com.example.dao;

import com.example.model.User;
import com.example.utils.XmlSessionFactoryProvider;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * @author Oleg Pavlyukov
 * on 29.01.2020
 * cpabox777@gmail.com
 */
public class UserDao {

    public List<User> getUserList() {
        Session session = null;
        List<User> userList = null;

        try {
            session = XmlSessionFactoryProvider.getSessionFactory().openSession();
            String sql = "SELECT * FROM User";

            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(User.class);

            userList = query.list();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (session != null) session.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return userList;
    }

    public User getUserById(Long id) {
        Session session = null;
        User user = null;

        try {
            session = XmlSessionFactoryProvider.getSessionFactory().openSession();
            String sql = "SELECT id, name, age FROM User WHERE id = :user_id";

            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(User.class);
            query.setParameter("user_id", id);

            user = (User) query.list().get(0);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
               if (session != null) session.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return user;
    }

    public Long addUser(User user) {
        Session session = null;
        Transaction transaction = null;
        Long id = null;

        try {
            session = XmlSessionFactoryProvider.getSessionFactory().openSession();
            transaction = session.beginTransaction();

//            SQLQuery query = session.createSQLQuery("INSERT INTO User VALUES (:user_name, :user_age");
//            query.setParameter("user_name", user.getName());
//            query.setParameter("user_age", user.getAge());
//            query.executeUpdate();
            id = (Long) session.save(user);
            transaction.commit();

        } catch (Exception ex) {
            ex.printStackTrace();
            if (transaction != null) transaction.rollback();
        } finally {
            try {
                if (session != null) session.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return id;
    }

    public void update(User user) {
        Session session = null;
        Transaction transaction = null;
//        User mergedUser = null;
        String sql = "UPDATE User SET name = :user_name, age = :user_age WHERE id = :user_id";

        try {
            session = XmlSessionFactoryProvider.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter("user_name", user.getName());
            query.setParameter("user_age", user.getAge());
            query.setParameter("user_id", user.getId());

            query.executeUpdate();
//            mergedUser = (User) session.merge(user);
            transaction.commit();

        } catch (Exception ex) {
            ex.printStackTrace();
            if (transaction != null) transaction.rollback();
        } finally {
            try {
                if (session != null) session.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
//        return mergedUser;
    }

    public void delete(Long id) {
        Session session = null;
        List<User> userList = null;

        try {
            session = XmlSessionFactoryProvider.getSessionFactory().openSession();
            String sql = "DELETE FROM User WHERE id = :user_id";

            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter("user_id", id);

            query.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (session != null) session.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public boolean isExist(Long id) {
        Session session = null;
        Object user = null;

        try {
            session = XmlSessionFactoryProvider.getSessionFactory().openSession();

            user = session.get(User.class, id);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (session != null) session.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return user == null;
    }
}
