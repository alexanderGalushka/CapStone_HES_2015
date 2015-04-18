package edu.harvard.cscie99.adam.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.harvard.cscie99.adam.model.Measurement;
import edu.harvard.cscie99.adam.model.Plate;
import edu.harvard.cscie99.adam.model.Project;
import edu.harvard.cscie99.adam.model.ResultSnapshot;
import edu.harvard.cscie99.adam.model.Well;
import edu.harvard.cscie99.adam.model.WellLabel;
import edu.harvard.cscie99.adam.profile.User;

/**
 * 
 * @author Gerson
 *
 */
@Component
public class UserService {
	

	@Autowired
    private SessionFactory sessionFactory;
	
	public List<User> listUsers(String exception){
		Session session = sessionFactory.openSession();
		List<User> resultList = new ArrayList<User>();
		try
		{
			List<User> userList = session.createCriteria(User.class).list();
			
			if (exception != null){
				for (User user : userList){
					if (!exception.equals(user.getUsername()))
						resultList.add(user);
				}
			}
			else{
				resultList.addAll(userList);
			}
		}
		finally {
			session.close();
		}

		return resultList;
	}
	
	public User retrieveUser(String username){

		List<User> listUser = listUsers(null);
		
		for (User user : listUser){
			if (user.getUsername() != null &&
				user.getUsername().equals(username)){
				return user;
			}
		}
		return null;
	}
	
	public User createUser(User user){

		Session session = sessionFactory.openSession();
		try{
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
		}
		finally{
			session.close();	
		}
		
		return user;
	}
	
	public boolean removeUser(User user){

		Session session = sessionFactory.openSession();
		try{
			session.beginTransaction();
			session.delete(user);
			session.getTransaction().commit();
		}
		finally{
			session.close();	
		}
		
		return true;
	}
	
	public User editUser(User user){

		Session session = sessionFactory.openSession();
		try{
			session.beginTransaction();
			session.merge(user);
			session.getTransaction().commit();
		}
		finally{
			session.close();	
		}
		
		return user;
	}
}
