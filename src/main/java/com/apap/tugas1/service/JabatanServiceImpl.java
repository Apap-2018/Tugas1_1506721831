package com.apap.tugas1.service;

import com.apap.tugas1.repository.JabatanDB;
import com.apap.tugas1.repository.PegawaiDB;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
@Transactional
public class JabatanServiceImpl implements JabatanService{
	@Autowired
	private JabatanDB jabatanDB;
	
	@Override
	public void add(JabatanModel jabatan) {
		jabatanDB.save(jabatan);
	}

	@Override
	public JabatanModel getDetailById(Long id) {
		return jabatanDB.getOne(id);
	}

	@Override
	public List<JabatanModel> getAllJabatan() {
		return jabatanDB.findAll();
	}

	@Override
	public void update(JabatanModel jabatan) {
		JabatanModel jabatanLama = jabatanDB.getOne(jabatan.getId());
		jabatanLama.setNama(jabatan.getNama());
		jabatanLama.setDeskripsi(jabatan.getDeskripsi());
		jabatanLama.setGaji_pokok(jabatan.getGaji_pokok());
		jabatanDB.save(jabatan);
	}

	@Override
	public void delete(JabatanModel jabatan) {
		jabatanDB.delete(jabatan);
	}
	
	
	
}
