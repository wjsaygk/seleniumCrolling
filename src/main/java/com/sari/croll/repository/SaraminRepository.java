package com.sari.croll.repository;

import java.util.List;

import com.sari.croll.model.Saramin;

public interface SaraminRepository {
	void save(Saramin s);
	List<Saramin>findAll();
	void delete();
}
