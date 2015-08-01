package com.claimacademy.claimazon.dao;

import junit.framework.Assert;


public class BookDAOImpTest {

//    @org.junit.Test
//    public void testGetAuthorID() throws Exception {
//
//        BookDAOImp DAO = new BookDAOImp();
//        String test = DAO.getAuthorID("Luke", "VanderHart");
//        Assert.assertEquals("1", test);
//    }
//    @org.junit.Test
//    public void testGetAuthor_NameDoesntExist() throws Exception {
//
//        BookDAOImp DAO = new BookDAOImp();
//        String test = DAO.getAuthorID("Joooooon", "VanderHart");
//        Assert.assertEquals(1, test);
//    }
    @org.junit.Test
    public void testGetCategoryName_NameDoesntExist() throws Exception {

        BookDAOImp DAO = new BookDAOImp();
        String category = DAO.getCategoryName("Joooooon");
        Assert.assertEquals(null, category);
    }

    @org.junit.Test
    public void testGetCategoryName_NameExists() throws Exception {

        BookDAOImp DAO = new BookDAOImp();
        String category = DAO.getCategoryName("Groovy");
        Assert.assertEquals("Groovy", category);
    }
}