package com.narvee.usit.helper;

import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RecSubmissionHelper {

	 private Long consultantid;
	 private Long subId;
	 private LocalDate created_date;
	 private String name;
	 private double rate;
	 private String recruiternName;
	 private String status;
	 
	public RecSubmissionHelper(Long consultantid, Long subId, LocalDate created_date, String name, double rate,
			String recruiternName, String status) {
		this.consultantid = consultantid;
		this.subId = subId;
		this.created_date = created_date;
		this.name = name;
		this.rate = rate;
		this.recruiternName = recruiternName;
		this.status = status;
	}
	 
	 
	 
}
