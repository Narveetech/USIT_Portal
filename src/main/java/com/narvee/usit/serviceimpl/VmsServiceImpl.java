package com.narvee.usit.serviceimpl;
import java.io.IOException;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.narvee.usit.entity.Vms;
import com.narvee.usit.helper.HelperVmsUpload;
import com.narvee.usit.repository.IVmsRepository;
import com.narvee.usit.service.IVmsService;

@Service
@Transactional
public class VmsServiceImpl implements IVmsService {
	@Autowired
	private IVmsRepository iVmsRepo;
	
	private Vms vms;

	@Override
	public boolean saveVms(Vms portalVms) {
		if(vms.getState()!= null ) {
		Vms save = iVmsRepo.save(portalVms);
		return true;
		}
		
			return false;
	}

	@Override
	public List<Vms> findAllVms() {
		if(vms.getState()=="Approve") {
			System.out.println(vms.getState()+"sdfghjjjjjjjjjjjjjjjjjjjjjj");
			return iVmsRepo.getallvms();
		}
		System.out.println("asdafdghgk");
		return null;
	}

	@Override
	public Vms getbyId(long id) {	
			return iVmsRepo.findById(id).get();
	}

	@Override
	public boolean deleteById(long id) {
		iVmsRepo.deleteById(id);
		return true;	
	}

	@Override
	public List<Vms> dupvendor(String email) {
		List<Vms> vms = iVmsRepo.findByCpemail(email);
		return vms;
	}

//	@Override
	/*
	 * public int changeStatus(String status, int id) { return
	 * iVmsRepo.toggleStatus(status, id); }
	 */

	@Override
	public Long changeStatus(String status, Long id,String rem) {
		return iVmsRepo.toggleStatus(status, id,rem);
	}

	@Override
	public List<Vms> findVmsbyid(int id) {
		return iVmsRepo.searchbyid(id);
	}
	@Override
	public List<Vms> filterVms(String keyword) {
		if(keyword != null) {
			return iVmsRepo.getVMSFilter(keyword);
		}
		return iVmsRepo.getallvms();
	}
	
	@Override
	public Page<Vms> findPaginated(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		Page<Vms> findAll = iVmsRepo.getallvms(pageable);
		return findAll;
	}

	@Override
	public void save(MultipartFile file) {
	}

	@Override
	public void saveVmsFileUpload(MultipartFile file) {
		 try {
	            List<Vms> products = HelperVmsUpload.convertExcelToListOfProduct(file.getInputStream());
	            this.iVmsRepo.saveAll(products);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		
	}

}
