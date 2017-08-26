package com.oracle.connect;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class EmpDao {
	JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public int createEmployee(Emp p) {
		Random ran = new Random();
		int emp_id = ran.nextInt(6000) + 5;
		String query = "insert into Emp99 (id,name,salary,designation) values('"+ emp_id +"','" + p.getName() + "','" + p.getSalary()
				+ "','" + p.getDesignation() + "')";
		return jdbcTemplate.update(query);

	}

	public int updateEmployee(Emp p) {
		String query = "update Emp99 set name='" + p.getName() + "',salary='" + p.getSalary() + "',designation='"
				+ p.getDesignation() + "' where id ='" + p.getId() + "'";
		return jdbcTemplate.update(query);
	}

	public int deleteEmployee(int id) {
		String query = "delete from Emp99 where id='" + id + "'";
		return jdbcTemplate.update(query);

	}

	public Emp getEmployeeById(int id) {
		String query = "select * from Emp99 where id=?";
		return jdbcTemplate.queryForObject(query, new Object[] { id }, new BeanPropertyRowMapper<Emp>(Emp.class));

	}

	public List<Emp> getEmployees() {
		return jdbcTemplate.query("select * from Emp99", new RowMapper<Emp>() {
			public Emp mapRow(ResultSet rs, int row) throws SQLException {
				Emp e = new Emp();
				e.setId(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setSalary(rs.getFloat(3));
				e.setDesignation(rs.getString(4));
				return e;
			}
		});
	}
}
