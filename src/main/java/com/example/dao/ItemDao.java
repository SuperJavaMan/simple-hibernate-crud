package com.example.dao;

import com.example.model.Item;
import com.example.utils.AnnotationSessionFactoryProvider;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * @author Oleg Pavlyukov
 * on 29.01.2020
 * cpabox777@gmail.com
 */
public class ItemDao {

    public List<Item> getItemList() {
        Session session = null;
        List<Item> itemList = null;
        String hql = "FROM Item";

        try {
            session = AnnotationSessionFactoryProvider.getSessionFactory().openSession();
            Query query = session.createQuery(hql);

            itemList = query.list();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (session != null) session.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return itemList;
    }

    public Item getItemById(Long id) {
        Session session = null;
        Item item = null;
        String hql = "FROM Item I WHERE I.id = :id";

        try {
            session = AnnotationSessionFactoryProvider.getSessionFactory().openSession();
            Query query = session.createQuery(hql);
            query.setParameter("id", id);

            item = (Item) query.list().get(0);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (session != null) session.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return item;
    }

    public void addItem(Item item) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = AnnotationSessionFactoryProvider.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.persist(item);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) transaction.rollback();
        } finally {
            try {
                if (session != null) session.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void updateItem(Item item) {
        Session session = null;
        Transaction transaction = null;
        String hql = "UPDATE Item SET name = :name, countItem = :countItem WHERE id = :id";
        try {
            session = AnnotationSessionFactoryProvider.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            Query query = session.createQuery(hql);
            query.setParameter("name", item.getName());
            query.setParameter("countItem", item.getCountItem());
            query.setParameter("id", item.getId());

            query.executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            try {
                if (session != null) session.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteItem(Long id) {
        Session session = null;
        String hql = "DELETE FROM Item WHERE id = :id";

        try {
            session = AnnotationSessionFactoryProvider.getSessionFactory().openSession();
            Query query = session.createQuery(hql);
            query.setParameter("id", id);

            query.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (session != null) session.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
