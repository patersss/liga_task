package com.chelyapin.task.client;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "client_account")
public class ClientAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "full_name", nullable = false)
	private String fullName;

	@Enumerated(EnumType.STRING)
	@JdbcTypeCode(SqlTypes.VARCHAR)
	@Column(length = 1)
	private Gender gender;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private ClientStatus status = ClientStatus.ACTIVE;

	@CreationTimestamp
	@Column(name = "create_dttm", nullable = false, updatable = false)
	private Instant createDttm;

	@UpdateTimestamp
	@Column(name = "modify_dttm", nullable = false)
	private Instant modifyDttm;

	public Long getId() { return id; }
	public String getFullName() { return fullName; }
	public void setFullName(String fullName) { this.fullName = fullName; }
	public Gender getGender() { return gender; }
	public void setGender(Gender gender) { this.gender = gender; }
	public ClientStatus getStatus() { return status; }
	public void setStatus(ClientStatus status) { this.status = status; }
	public Instant getCreateDttm() { return createDttm; }
	public Instant getModifyDttm() { return modifyDttm; }
}
