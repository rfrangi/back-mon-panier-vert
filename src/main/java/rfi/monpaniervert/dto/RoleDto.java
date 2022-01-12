package rfi.monpaniervert.dto;

import rfi.monpaniervert.enums.ERole;

public class RoleDto {

	private Integer id;
	private ERole name;

	public RoleDto() {

	}

	public RoleDto(ERole name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ERole getName() {
		return name;
	}

	public void setName(ERole name) {
		this.name = name;
	}
}