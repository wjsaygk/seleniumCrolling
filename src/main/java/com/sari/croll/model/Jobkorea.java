package com.sari.croll.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Jobkorea {
private int id;
private String url;
private String CompanyName;
private String title;
private Timestamp createDate;
private int type;//3
}
