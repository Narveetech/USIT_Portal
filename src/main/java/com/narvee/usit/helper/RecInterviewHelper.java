package com.narvee.usit.helper;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecInterviewHelper {
	
	private int id;
	private String consultantname;
	private String dateandtimeofInterview;
	private String timeZone;
	private String round;
	private String mode;
	private String requirement;
	private LocalDate dateofsubmission;
	private long employername;
	private String interviewstatus;
	private String status;
	
}