package com.apap.tugas1.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tugas1.model.ProvinsiModel;
import com.apap.tugas1.repository.ProvinsiDB;

@Service
@Transactional
public class ProvinsiServiceImpl implements ProvinsiService{
	@Autowired
	private ProvinsiDB ProvinsiDB;

	@Override
	public List<ProvinsiModel> getAllProvinsi() {
		return ProvinsiDB.findAll();
	}

	@Override
	public ProvinsiModel getProvinsiDetailById(long id) {
		return ProvinsiDB.findById(id);
	}
}
