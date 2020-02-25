package com.sari.croll.repository;

import java.util.List;

import com.sari.croll.model.Jobkorea;

public interface JobkoreaRepository {
	void save(Jobkorea j);

	List<Jobkorea> findAll();

	void delete();
}
