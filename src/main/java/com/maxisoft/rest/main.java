package com.maxisoft.rest;

import com.maxisoft.rest.repository.impHibernate.UserHibernateRepoImpl;

public class main {
    public static void main(String[] args) {
        UserHibernateRepoImpl repo = new UserHibernateRepoImpl();

        System.out.println(repo.getAll());
    }
}
