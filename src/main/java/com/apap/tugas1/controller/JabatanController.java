//Sedangkan entitas JABATAN menggambarkan jabatan yang dipegang seorang pegawai di suatu instansi
package com.apap.tugas1.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.service.JabatanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JabatanController {
	@Autowired
	private JabatanService jabatanService;

	
	//Fitur No. 5
	@RequestMapping(value = "/jabatan/tambah", method = RequestMethod.GET)
	private String add(Model model) {
		model.addAttribute("jabatan", new JabatanModel());
		return "add-jabatan";
	}

	@RequestMapping(value = "/jabatan/tambah", method = RequestMethod.POST)
	private String addJabatanSubmit(@ModelAttribute JabatanModel jabatan) {
		jabatanService.add(jabatan);
		return "added";
	}
	
	//Fitur No. 6
	@RequestMapping(value = "/jabatan/view", method = RequestMethod.GET)
	private String viewJabatan(@RequestParam("id") Long id, Model model) {
		JabatanModel jabatan = jabatanService.getDetailById(id);
		model.addAttribute("jabatan", jabatan);
		return "detail-jabatan";
	}
	
	//Fitur No. 7 
	@RequestMapping(value = "/jabatan/ubah", method = RequestMethod.GET)
    private String update(@RequestParam("id") Long id, Model model) {
		JabatanModel jabatan = jabatanService.getDetailById(id);
		model.addAttribute("jabatan", jabatan);
        return "update-jabatan";
    }

    @RequestMapping(value = "/jabatan/ubah", method = RequestMethod.POST)
    private String updateSubmit(@ModelAttribute JabatanModel jabatan) {
    	jabatan.setId(jabatan.getId());
    	jabatanService.update(jabatan);
        return "updated";
    }
    
	//Fitur No.8
    @RequestMapping(value = "/jabatan/hapus", method = RequestMethod.POST)
	private String delete(@RequestParam(value = "id") Long id, Model model) {
    	JabatanModel jabatan = jabatanService.getDetailById(id);
    	if (jabatan.getJabatan_pegawai().isEmpty()) {
    		jabatanService.delete(jabatan);
    		return "deleted";
    	}
    	else {
    		return "error";
    	}	
	}
	
	//Fitur No. 9
	@RequestMapping(value = "/jabatan/viewall", method = RequestMethod.GET)
	 private String viewAllJabatan(Model model) {
		 List<JabatanModel> listJabatan = jabatanService.getAllJabatan(); 
		 model.addAttribute("listJabatan", listJabatan);
		 return "viewall-jabatan";
	 }
	
}
