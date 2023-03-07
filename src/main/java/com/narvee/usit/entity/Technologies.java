package com.narvee.usit.entity;							/* created By Swamy  */

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_technologies_tags")
public class Technologies {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "Technologies")
	@SequenceGenerator(name = "Technologies", sequenceName = "Technologies_seq")
	private Integer id;
	
	@Column(name = "technology_area")
	private String technology_area;
	
	@Column(name = "list_of_keyword")
	private String list_of_keyword;
	
	@Column(name = "comments", length = 255)
	private String comments;
	
	@Column(name="status") 
	private String status = "Active";
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name= "created_date", nullable = false, updatable = false)
	@CreationTimestamp()
    private LocalDate created_date;
	
	@Column(name= "added_by", nullable = false, updatable = false)
	private long added_by=1;
}
