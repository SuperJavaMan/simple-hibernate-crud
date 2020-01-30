package com.example.dao;

import com.example.model.Customer;
import com.example.utils.AnnotationSessionFactoryProvider;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * @author Oleg Pavlyukov
 * on 30.01.2020
 * cpabox777@gmail.com
 */
public class CustomerDao {

    public List<Customer> getCustomerList() {
        Session session = null;
        List<Customer> customerList = null;

        try {
            session = AnnotationSessionFactoryProvider.getSessionFactory().openSession();

            customerList = session.createQuery("From Customer").list();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (session != null) session.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return customerList;
    }

    public Customer getCustomerById(Long id) {
        Session session = null;
        Customer customer = null;

        try {
            session = AnnotationSessionFactoryProvider.getSessionFactory().openSession();

            customer = (Customer) session.get(Customer.class, id);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (session != null) session.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return customer;
    }

    public void addCustomer(Customer customer) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = AnnotationSessionFactoryProvider.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.persist(customer);

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

    public Customer updateCustomer(Customer customer) {
        Session session = null;
        Transaction transaction = null;
        Customer updatedCustomer = null;

        try {
            session = AnnotationSessionFactoryProvider.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            updatedCustomer = (Customer) session.merge(customer);

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
        return updatedCustomer;
    }

    public void deleteCustomer(Long id) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = AnnotationSessionFactoryProvider.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            Customer customer = (Customer) session.get(Customer.class, id);
            if (customer != null) session.delete(customer);

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
}
