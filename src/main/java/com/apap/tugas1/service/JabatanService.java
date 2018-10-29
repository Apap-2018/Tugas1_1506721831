package com.apap.tugas1.service;

import com.apap.tugas1.model.JabatanModel;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface JabatanService {

	void add(JabatanModel jabatan);

	JabatanModel getDetailById(Long id);

	void update(JabatanModel jabatan);

	void delete(JabatanModel jabatan);

	List<JabatanModel> getAllJabatan();

}

