package service;

import java.sql.Connection;
import java.util.List;

import dao.CategoriesDao;
import entity.Categories;
import util.DbUtil;
public class CategoriesService {
	
    public List<Categories> selectCategory() {
        try (Connection conn = DbUtil.getConnection()) {
        	CategoriesDao cdDao = new CategoriesDao(conn);
        	List<Categories> cd = cdDao.selectCategory();
            return cd;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
