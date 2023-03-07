package com.narvee.usit.helper;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SalesSubmissionHelper {
	 
	private Long   subid;
	private LocalDate created_date;
	private String consultantname;
	private String positionTitle;
	private String endClient;
	private String vendor;
	private String rate;
	private String addedBy;
	private String status;
}
