package com.ftn.EUprava.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ftn.EUprava.dao.VakcinaDAO;
import com.ftn.EUprava.model.ProizvodjacVakcine;
import com.ftn.EUprava.model.Vakcina;


@Repository
public class VakcinaDAOImpl implements VakcinaDAO{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private class VakcinaRowCallBackHandler implements RowCallbackHandler {

		private Map<Long, Vakcina> vakcine = new LinkedHashMap<>();
		
		@Override
		public void processRow(ResultSet resultSet) throws SQLException {
			int index = 1;
			Long id = resultSet.getLong(index++);
			String ime = resultSet.getString(index++);
			Integer dostupnaKolicina = resultSet.getInt(index++);
			ProizvodjacVakcine proizvodjac= new ProizvodjacVakcine();
			String proizvodjacString = resultSet.getString(index++);
			proizvodjac.setProizvodjac(proizvodjacString);

			Vakcina vakcina = vakcine.get(id);
			if (vakcina == null) {
				vakcina = new Vakcina(id, ime, dostupnaKolicina, proizvodjac);
				vakcine.put(vakcina.getId(), vakcina); // dodavanje u kolekciju
			}
		}

		public List<Vakcina> getVakcine() {
			return new ArrayList<>(vakcine.values());
		}

	}
	
	@Override
	public Vakcina findOne(Long id) {
		String sql = 
				"SELECT v.id, v.ime, v.dostupnaKolicina, v.ProizvodjacId FROM vakcine v " + 
				"WHERE v.id = ? " + 
				"ORDER BY v.id";

		VakcinaRowCallBackHandler rowCallbackHandler = new VakcinaRowCallBackHandler();
		jdbcTemplate.query(sql, rowCallbackHandler, id);

		return rowCallbackHandler.getVakcine().get(0);
	}

	@Override
	public List<Vakcina> findAll() {
		String sql = 
				"SELECT v.id, v.ime, v.dostupnaKolicina, v.ProizvodjacId FROM vakcine v " +  
						"ORDER BY v.id";

		VakcinaRowCallBackHandler rowCallbackHandler = new VakcinaRowCallBackHandler();
		jdbcTemplate.query(sql, rowCallbackHandler);

		return rowCallbackHandler.getVakcine();
	}
	
	@Transactional
	@Override
	public int save(Vakcina vakcina) {
		PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				String sql = "INSERT INTO vakcine (ime, dostupnaKolicina, ProizvodjacId) VALUES (?, ?)";

				PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				int index = 1;
				preparedStatement.setString(index++, vakcina.getIme());
				preparedStatement.setInt(index++, vakcina.getDostupnaKolicina());
				preparedStatement.setObject(index++, vakcina.getProizvodjac());


			
			
				
				return preparedStatement;
			}

		};
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		boolean uspeh = jdbcTemplate.update(preparedStatementCreator, keyHolder) == 1;
		return uspeh?1:0;
	}
	
	@Transactional
	@Override
	public int update(Vakcina vakcina) {
		String sql = "UPDATE vakcina SET ime = ?, dostupnaKolicina = ?, proizvodjacId = ? WHERE id = ?";	
		boolean uspeh = jdbcTemplate.update(sql, vakcina.getIme() , vakcina.getDostupnaKolicina(), vakcina.getProizvodjac(), vakcina.getId()) == 1;
		
		return uspeh?1:0;
	}
	
	@Transactional
	@Override
	public int delete(Long id) {
		String sql = "DELETE FROM vakcine WHERE id = ?";
		return jdbcTemplate.update(sql, id);
	}

}
