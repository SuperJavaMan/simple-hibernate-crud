package com.example.model;

import javax.persistence.*;

/**
 * @author Oleg Pavlyukov
 * on 29.01.2020
 * cpabox777@gmail.com
 */
@Entity
@Table(name = "Item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int countItem;

    public Item() {
    }

    public Item(String name, int countItem) {
        this.name = name;
        this.countItem = countItem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountItem() {
        return countItem;
    }

    public void setCountItem(int countItem) {
        this.countItem = countItem;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", countItem=" + countItem +
                '}';
    }
}
