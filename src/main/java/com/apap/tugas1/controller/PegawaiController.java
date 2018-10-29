//PEGAWAI merepresentasikan pegawai yang ada di suatu instansi.
/* Setiap pegawai pasti berafiliasi dengan suatu instansi, sehingga setiap pegawai pasti 
tercatat dalam suatu instansi. Setiap pegawai juga pasti memiliki setidaknya satu jabatan.
Suatu jabatan bisa saja dimiliki oleh beberapa pegawai. Setiap instansi pasti terdapat di suatu
provinsi dan pasti memiliki pegawai, begitu juga dengan provinsi yang pasti memiliki suatu
instansi pemerintah */

package com.apap.tugas1.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.model.ProvinsiModel;
import com.apap.tugas1.service.PegawaiService;
import com.apap.tugas1.service.ProvinsiService;
import com.apap.tugas1.service.InstansiService;
import com.apap.tugas1.service.JabatanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PegawaiController {
	@Autowired
	private PegawaiService pegawaiService;
	
	@Autowired
	private JabatanService jabatanService;
	
	@Autowired
	private ProvinsiService provinsiService;
	
	@Autowired
	private InstansiService instansiService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	private String home(Model model) {
		List<JabatanModel> listJabatan = jabatanService.getAllJabatan();
		model.addAttribute("listJabatan", listJabatan);
		List<InstansiModel> listInstansi = instansiService.getAllInstansi();
		model.addAttribute("listInstansi", listInstansi);
		return "home";
	}
	
	//Fitur No. 1 
	@RequestMapping(value = "/pegawai", method = RequestMethod.GET)
	private String viewPegawai(@RequestParam("nip") String nip, Model model) {
		PegawaiModel pegawai = pegawaiService.getPegawaiDetailByNIP(nip);
		List<JabatanModel> jabatanPegawai = pegawai.getJabatan();
		int gaji = (int) (jabatanPegawai.get(0).getGaji_pokok() + 
					(jabatanPegawai.get(0).getGaji_pokok() * (pegawai.getInstansi().getProvinsi().getPresentase_tunjangan()/100)));
		
		model.addAttribute("pegawai", pegawai);
		model.addAttribute("jabatanPegawai", jabatanPegawai);
		model.addAttribute("gaji", gaji);
		return "view-pegawai";
	}
	
	//Fitur No. 2 
	@RequestMapping(value = "/pegawai/tambah", method = RequestMethod.GET)
	private String add(Model model) {
		model.addAttribute("pegawai", new PegawaiModel());
		return "add-pegawai";
	}
	
	@RequestMapping(value = "/pilot/tambah", method = RequestMethod.POST)
	private String addPegawaiSubmit(@ModelAttribute PegawaiModel pegawai) {
		pegawaiService.add(pegawai);
		return "add";
	}
	
	//Fitur No. 4 
	@RequestMapping(value = "/pegawai/cari", method = RequestMethod.GET)
	public String searchPegawai(@RequestParam(value="idProvinsi",required=false) String idProvinsi,
								@RequestParam(value="idInstansi",required=false) String idInstansi,
								@RequestParam(value="idJabatan",required=false) String idJabatan,Model model) {
		List<ProvinsiModel> listProvinsi = provinsiService.getAllProvinsi();
		model.addAttribute("listProvinsi", listProvinsi);
		
		List<InstansiModel> listInstansi = instansiService.getAllInstansi();
		model.addAttribute("listInstansi", listInstansi);
		
		List<JabatanModel> listJabatan = jabatanService.getAllJabatan();
		model.addAttribute("listJabatan", listJabatan);
		
		List<PegawaiModel> listPegawai = pegawaiService.listPegawai();
		
		if ((idProvinsi==null || idProvinsi.equals("")) && (idInstansi==null||idInstansi.equals("")) && (idJabatan==null||idJabatan.equals(""))) {
		}
		else {
			if (idProvinsi!=null && !idProvinsi.equals("")) {
				List<PegawaiModel> temp = new ArrayList<PegawaiModel>();
				for (PegawaiModel pegawai : listPegawai) {
					if(((Long)pegawai.getInstansi().getProvinsi().getId()).toString().equals(idProvinsi)) {
						temp.add(pegawai);
					}
				}
				listPegawai = temp;
				model.addAttribute("idProvinsi", idProvinsi);
			}
			else {
				model.addAttribute("idProvinsi", "");
			}
		}
			if (idInstansi!=null&&!idInstansi.equals("")) {
				List<PegawaiModel> temp = new ArrayList<PegawaiModel>();
				for (PegawaiModel pegawai : listPegawai) {
					if(((Long)pegawai.getInstansi().getId()).toString().equals(idInstansi)){
						temp.add(pegawai);
					}
				}
				listPegawai = temp;
				model.addAttribute("idInstansi", idInstansi);
			}
			else {
				model.addAttribute("idInstansi", "");
			}
			if (idJabatan!=null&&!idJabatan.equals("")) {
				List<PegawaiModel> temp = new ArrayList<PegawaiModel>();
				for (PegawaiModel pegawai : listPegawai) {
					for (JabatanModel jabatan : pegawai.getJabatan()) {
						if(((Long)jabatan.getId()).toString().equals(idJabatan)) {
							temp.add(pegawai);
							break;
						}
					}
					
				}
				listPegawai = temp;
				model.addAttribute("idJabatan", idJabatan);
			}
			else {
				model.addAttribute("idJabatan", "");
			}
			model.addAttribute("listPegawai", listPegawai);
			return "cari-pegawai";
		}
	
	//Fitur No. 9
	@RequestMapping(value = "/pegawai/termuda-tertua", method = RequestMethod.GET)
    public String viewTermudaTertua(@RequestParam("id") long id, Model model){
        InstansiModel instansi = instansiService.getDetailById(id);

        PegawaiModel pegawaiTermuda = instansi.getPegawaiTermuda();
        PegawaiModel pegawaiTertua = instansi.getPegawaiTertua();
        
        List<JabatanModel> jabatanPegawaiMuda = pegawaiTermuda.getJabatan();
        List<JabatanModel> jabatanPegawaiTua = pegawaiTertua.getJabatan();
        
        int gajiPegawaiTermuda = (int) (jabatanPegawaiMuda.get(0).getGaji_pokok() +
        					(jabatanPegawaiMuda.get(0).getGaji_pokok() * (pegawaiTermuda.getInstansi().getProvinsi().getPresentase_tunjangan()/100)));
        
        int gajiPegawaiTertua = (int) (jabatanPegawaiTua.get(0).getGaji_pokok() +
				(jabatanPegawaiTua.get(0).getGaji_pokok() * (pegawaiTertua.getInstansi().getProvinsi().getPresentase_tunjangan()/100)));

        model.addAttribute("pegawaiTermuda", pegawaiTermuda);
        model.addAttribute("jPegawaiTermuda", jabatanPegawaiMuda);
        model.addAttribute("gajiPegawaiTermuda", gajiPegawaiTermuda);

        model.addAttribute("pegawaiTertua", pegawaiTertua);
        model.addAttribute("jPegawaiTertua", jabatanPegawaiTua);
        model.addAttribute("gajiPegawaiTertua", gajiPegawaiTertua);
        return"view-termuda-tertua";
    }
	
}
