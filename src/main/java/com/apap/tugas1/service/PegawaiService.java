package com.apap.tugas1.service;

import java.util.List;

import com.apap.tugas1.model.PegawaiModel;

public interface PegawaiService {

	PegawaiModel getPegawaiDetailByNIP(String nip);

	void add(PegawaiModel pegawai);
	
	List<PegawaiModel> listPegawai();
}

