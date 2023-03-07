
package com.narvee.usit.commons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseSingleUpload {

	private String status;
	
	private String message;
	
	private String fileName;

	private String fileDownloadUri;

	private String fileType;

	private long size;

}
