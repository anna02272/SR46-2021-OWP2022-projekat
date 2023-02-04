package com.ftn.EUprava.dao.impl;

import java.sql.Connection;
import org.springframework.jdbc.core.RowMapper;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
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

import com.ftn.EUprava.dao.ProizvodjacDAO;
import com.ftn.EUprava.dao.VakcinaDAO;
import com.ftn.EUprava.model.ProizvodjacVakcine;
import com.ftn.EUprava.model.Vakcina;


@Repository
public class VakcinaDAOImpl implements VakcinaDAO{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private ProizvodjacDAO proizvodjacDAO;
	
	private class VakcinaRowCallBackHandler implements RowCallbackHandler {

		private Map<Long, Vakcina> vakcine = new LinkedHashMap<>();
		
		@Override
		public void processRow(ResultSet resultSet) throws SQLException {
			int index = 1;
			Long id = resultSet.getLong(index++);
			String ime = resultSet.getString(index++);
			Integer dostupnaKolicina = resultSet.getInt(index++);
			Long proizvodjacVakcineId = resultSet.getLong(index++);
			ProizvodjacVakcine proizvodjacVakcine = proizvodjacDAO.findOne(proizvodjacVakcineId);

			Vakcina vakcina = vakcine.get(id);
			if (vakcina == null) {
				vakcina = new Vakcina(id, ime, dostupnaKolicina, proizvodjacVakcine);
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
				String sql = "INSERT INTO vakcine (ime, ProizvodjacId) VALUES (?, ?)";

				PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				int index = 1;
				preparedStatement.setString(index++, vakcina.getIme());

				preparedStatement.setLong(index++, vakcina.getProizvodjac().getId());

				
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
		String sql = "UPDATE vakcine SET ime = ?, dostupnaKolicina = ?, proizvodjacId = ? WHERE id = ?";	
		boolean uspeh = jdbcTemplate.update(sql, vakcina.getIme() , vakcina.getDostupnaKolicina(), vakcina.getProizvodjac().getId(), vakcina.getId()) == 1;
		
		return uspeh?1:0;
	}
	
	@Transactional
	@Override
	public int delete(Long id) {
		String sql = "DELETE FROM vakcine WHERE id = ?";
		return jdbcTemplate.update(sql, id);
	}
	
	private class VakcinaRowMapper implements RowMapper<Vakcina> {

		@Override
		public Vakcina mapRow(ResultSet rs, int rowNum) throws SQLException {
			int index = 1;
			Long vakcinaId = rs.getLong(index++);
			String vakcinaIme = rs.getString(index++);
			Integer vakcinaKolicina = rs.getInt(index++);
			Long proizvodjacVakcineId = rs.getLong(index++);
			ProizvodjacVakcine vakcinaProizvodjac = proizvodjacDAO.findOne(proizvodjacVakcineId);

			Vakcina vakcina = new Vakcina(vakcinaId, vakcinaIme, vakcinaKolicina, vakcinaProizvodjac);
			return vakcina;
		}

	}
	
	public List<Vakcina> find(String ime, Integer dostupnaKolicinaMin, Integer dostupnaKolicinaMax, Long proizvodjacId) {
		StringBuilder sb = new StringBuilder();
		sb.append( "SELECT v.id, v.ime, v.dostupnaKolicina, p.id, p.proizvodjac FROM vakcine v " + 
				"LEFT JOIN proizvodjaciVakcine p ON v.proizvodjacId = p.id");
		List<Object> params = new ArrayList<>();
		boolean whereIncluded = false;
		
		if (ime != null && !ime.isEmpty()) {
			sb.append(" WHERE LOWER(v.ime) LIKE LOWER(?)");
			params.add("%" + ime + "%");
			whereIncluded = true;
		}
		
		if (dostupnaKolicinaMin != null) {
			if (!whereIncluded) {
				sb.append(" WHERE v.dostupnaKolicina >= ?");
			} else {
				sb.append(" AND v.dostupnaKolicina >= ?");
			}
			params.add(dostupnaKolicinaMin);
			whereIncluded = true;
		}
		
		if (dostupnaKolicinaMax != null) {
			if (!whereIncluded) {
				sb.append(" WHERE v.dostupnaKolicina <= ?");
			} else {
				sb.append(" AND v.dostupnaKolicina <= ?");
			}
			params.add(dostupnaKolicinaMax);
			whereIncluded = true;
		}
		
		if (proizvodjacId != null) {
			if (!whereIncluded) {
				sb.append(" WHERE p.id = ?");
			} else {
				sb.append(" AND p.id = ?");
			}
			params.add(proizvodjacId);
			whereIncluded = true;
		}
	
		String sql = sb.toString();
		VakcinaRowCallBackHandler rowCallbackHandler = new VakcinaRowCallBackHandler();
		jdbcTemplate.query(sql, params.toArray(), rowCallbackHandler);
		return rowCallbackHandler.getVakcine();
	}

	
//	@Override
//	public List<Vakcina> find(String ime, Integer dostupnaKolicinaMin, Integer dostupnaKolicinaMax, Long proizvodjacId) {
//
//		ArrayList<Object> listaArgumenata = new ArrayList<Object>();
//		
//		String sql = "SELECT v.id, v.ime, v.dostupnaKolicina, p.id, p.proizvodjac FROM vakcine v " + 
//				"LEFT JOIN proizvodjaciVakcine p ON v.proizvodjacId = p.id";
//		
//		StringBuffer whereSql = new StringBuffer(" WHERE ");
//		boolean imaArgumenata = false;
//		
//		if(ime!=null) {
//			ime = "%" + ime + "%";
//			if(imaArgumenata)
//				whereSql.append(" WHERE ");
//			whereSql.append("v.ime LIKE ?");
//			imaArgumenata = true;
//			listaArgumenata.add(ime);
//		}
//		
//		if(proizvodjacId!=null) {
//			if(imaArgumenata)
//				whereSql.append(" AND ");
//			whereSql.append(" v.proizvodjacId = ?");
//			imaArgumenata = true;
//			listaArgumenata.add(proizvodjacId);
//		}
//		
//		if(dostupnaKolicinaMin!=null) {
//			if(imaArgumenata)
//				whereSql.append(" AND ");
//			whereSql.append(" v.dostupnaKolicina >= ?");
//			imaArgumenata = true;
//			listaArgumenata.add(dostupnaKolicinaMax);
//		}
//		
//		if(dostupnaKolicinaMax!=null) {
//			if(imaArgumenata)
//				whereSql.append(" AND ");
//			whereSql.append("  v.dostupnaKolicina <= ?");
//			imaArgumenata = true;
//			listaArgumenata.add(dostupnaKolicinaMax);
//		}
//		
//		if(imaArgumenata)
//			sql=sql + whereSql.toString()+" ORDER BY v.id";
//		else
//			sql=sql + " ORDER BY v.id";
//		
//		return jdbcTemplate.query(sql, listaArgumenata.toArray(), new VakcinaRowMapper());
//	}
//	
	@Override
	public List<Vakcina> find(HashMap<String, Object> mapaArgumenata) {
		
		ArrayList<Object> listaArgumenata = new ArrayList<Object>();
		
		String sql = "SELECT v.id, v.ime, v.dostupnaKolicina, p.id, p.proizvodjac FROM vakcine v " + 
				"LEFT JOIN proizvodjaciVakcine p ON v.proizvodjacId = p.id";
		
		StringBuffer whereSql = new StringBuffer(" WHERE ");
		boolean imaArgumenata = false;
		
		if(mapaArgumenata.containsKey("ime")) {
			String ime = "%" + mapaArgumenata.get("ime") + "%";
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("v.ime LIKE ?");
			imaArgumenata = true;
			listaArgumenata.add(ime);
		}
		
		if(mapaArgumenata.containsKey("dostupnaKolicinaMin")) {
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("v.dostupnaKolicina >= ?");
			imaArgumenata = true;
			listaArgumenata.add(mapaArgumenata.get("dostupnaKolicinaMin"));
		}
		
		if(mapaArgumenata.containsKey("dostupnaKolicinaMax")) {
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("v.dostupnaKolicina <= ?");
			imaArgumenata = true;
			listaArgumenata.add(mapaArgumenata.get("dostupnaKolicinaMax"));
		}
		if(mapaArgumenata.containsKey("proizvodjacId")) {
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("v.proizvodjacId = ?");
			imaArgumenata = true;
			listaArgumenata.add(mapaArgumenata.get("proizvodjacId"));
		}
		
		
		if(imaArgumenata)
			sql=sql + whereSql.toString()+" ORDER BY v.id";
		else
			sql=sql + " ORDER BY v.id";
		
		
		return jdbcTemplate.query(sql, listaArgumenata.toArray(), new VakcinaRowMapper());
	}
}
