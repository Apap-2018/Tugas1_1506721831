package com.apap.tugas1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.ProvinsiModel;
import com.apap.tugas1.repository.InstansiDB;

@Service
@Transactional
public class InstansiServiceImpl implements InstansiService{
	@Autowired
	private InstansiDB instansiDB;
	
	@Override
	public List<InstansiModel> getAllInstansi() {
		return instansiDB.findAll();
	}

	@Override
	public InstansiModel getDetailById(long id) {
		return instansiDB.findById(id);
	}
	
	@Override
	public List<InstansiModel> findInstansiByProvinsi(ProvinsiModel provinsi) {
		return instansiDB.findByProvinsi(provinsi);
	}
}
