package Dao;

import util.Conexion;
import util.GenericDao;
import entities.User;

	public class UserDao extends Conexion<User> implements GenericDao<User>{
		public UserDao(){
			super(User.class);
		}
	
}
