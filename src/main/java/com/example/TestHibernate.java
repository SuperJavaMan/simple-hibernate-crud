package com.example;

import com.example.dao.CustomerDao;
import com.example.dao.ItemDao;
import com.example.dao.UserDao;
import com.example.model.Customer;
import com.example.model.Item;
import com.example.model.User;

import java.util.List;

/**
 * @author Oleg Pavlyukov
 * on 29.01.2020
 * cpabox777@gmail.com
 */
public class TestHibernate {
    public static void main(String[] args) {
//        userTest();
//        itemTest();
        customerTest();


    }

    // тестовый метод для круд с описанной сущностью в xml. Вроде как древний способ
    // работа с базой с помощью SQL запросов
    private static void userTest() {
        UserDao userDao = new UserDao();

        System.out.println("///////////////////////////////");
        System.out.println("Persist a new Entity");
        User user1 = new User("User 1", 20);
        Long id = userDao.addUser(user1);
        System.out.println("Saved user id = " + id);

        System.out.println("///////////////////////////////");
        System.out.println("Get by id an Entity");
        User user2 = userDao.getUserById(2L);
        System.out.println(user2);

        System.out.println("///////////////////////////////");
        System.out.println("Get list of Entities");
        List<User> userList = userDao.getUserList();
        userList.forEach(System.out::println);

        System.out.println("///////////////////////////////");
        System.out.println("Update the Entity");
        User userMergeTest = new User("Merge default", 21);
        System.out.println(userMergeTest);

        Long idMergeTest = userDao.addUser(userMergeTest);
        User updatedUser = userDao.getUserById(idMergeTest);

        updatedUser.setName("Merge updated");
        userDao.update(updatedUser);
        User resultUser = userDao.getUserById(idMergeTest);
        System.out.println(resultUser);

        System.out.println("///////////////////////////////");
        System.out.println("Delete an Entity");
        User userDeleteTest = new User("Delete user test", 21);
        Long idDeleteTest = userDao.addUser(user1);

        userDao.delete(idDeleteTest);
        System.out.println("Is user with id = "  + idDeleteTest + " deleted? - " + userDao.isExist(idDeleteTest));
    }

    // круд для сущности с аннотациями
    // работа с базой с помощью HQL запросов
    private static void itemTest() {
        ItemDao itemDao = new ItemDao();

        System.out.println("///////////////////////////////");
        System.out.println("Persist Entity");

        Item persItem = new Item("Pers Item", 10);
        itemDao.addItem(persItem);

        System.out.println("///////////////////////////////");
        System.out.println("Get Entity List");

        List<Item> itemList = itemDao.getItemList();
        itemList.forEach(System.out::println);

        System.out.println("///////////////////////////////");
        System.out.println("Get Entity by id");

        Long id = itemList.get(0).getId();
        Item item = itemDao.getItemById(id);

        System.out.println(item);

        System.out.println("///////////////////////////////");
        System.out.println("Update Entity");

        Long idUpdate = itemList.get(0).getId();
        Item itemForUpdate = itemDao.getItemById(id);
        System.out.println("Old item -> " + itemForUpdate);

        itemForUpdate.setName("Updated value");
        itemForUpdate.setCountItem(99);
        itemDao.updateItem(itemForUpdate);

        Item itemAfterUpdate = itemDao.getItemById(idUpdate);
        System.out.println("Updated Item -> " + itemAfterUpdate);

        System.out.println("///////////////////////////////");
        System.out.println("Delete Entity");

        Long idDelete = itemList.get(0).getId();
        Item itemForDelete = itemDao.getItemById(idDelete);
        System.out.println(item);
        itemDao.deleteItem(idDelete);

        List<Item> itemListAfterDelete = itemDao.getItemList();
        System.out.println("Is deleted Item exist in result set -> " + itemListAfterDelete.contains(itemForDelete));
        System.out.println(itemListAfterDelete);
    }

    // круд для сущности с аннотациями
    // работа с базой с помощью методов интерфейса Session
    private static void customerTest() {
        CustomerDao customerDao = new CustomerDao();

        System.out.println("///////////////////////////////");
        System.out.println("Persist Entity");

        Customer persCustomer = new Customer("Pers FirstName", "Pers LastName");
        customerDao.addCustomer(persCustomer);

        System.out.println("///////////////////////////////");
        System.out.println("Get Entity List");

        List<Customer> customerList = customerDao.getCustomerList();
        customerList.forEach(System.out::println);

        System.out.println("///////////////////////////////");
        System.out.println("Get Entity by id");

        Long id = customerList.get(0).getId();
        Customer customer = customerDao.getCustomerById(id);

        System.out.println(customer);

        System.out.println("///////////////////////////////");
        System.out.println("Update Entity");

        Long idUpdate = customerList.get(0).getId();
        Customer customerForUpdate = customerDao.getCustomerById(idUpdate);
        System.out.println("Old item -> " + customerForUpdate);

        customerForUpdate.setFirstName("Updated value");
        customerForUpdate.setLastName("Updated value");
        customerDao.updateCustomer(customerForUpdate);

        Customer customerAfterUpdate = customerDao.getCustomerById(idUpdate);
        System.out.println("Updated Item -> " + customerAfterUpdate);

        System.out.println("///////////////////////////////");
        System.out.println("Delete Entity");

        Long idDelete = customerList.get(0).getId();
        Customer customerForDelete = customerDao.getCustomerById(idDelete);
        System.out.println(customerForDelete);
        customerDao.deleteCustomer(idDelete);

        List<Customer> customerListAfterDelete = customerDao.getCustomerList();
        System.out.println("Is deleted Item exist in result set -> " + customerListAfterDelete.contains(customerForDelete));
        System.out.println(customerListAfterDelete);
    }
}
