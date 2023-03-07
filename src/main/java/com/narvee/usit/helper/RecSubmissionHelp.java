package com.narvee.usit.helper;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecSubmissionHelp {
	
	private long submissionId;
	private LocalDate createddate;
	private long requirement;
	private String endclient;
	private String consultantname;
	private double rate;
	private String recruitername;
	private String substatus;
	private String status;
}
