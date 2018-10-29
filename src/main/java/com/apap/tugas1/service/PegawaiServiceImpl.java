package com.apap.tugas1.service;

import com.apap.tugas1.repository.PegawaiDB;
import com.apap.tugas1.model.PegawaiModel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PegawaiServiceImpl implements PegawaiService{
	@Autowired
	private PegawaiDB pegawaiDB;
	
	@Override
    public PegawaiModel getPegawaiDetailByNIP(String nip){
        return  pegawaiDB.findByNip(nip);
    }

	@Override
	public void add(PegawaiModel pegawai) {
		pegawaiDB.save(pegawai);
		
	}

	@Override
	public List<PegawaiModel> listPegawai() {
		return pegawaiDB.findAll();
	}

}

