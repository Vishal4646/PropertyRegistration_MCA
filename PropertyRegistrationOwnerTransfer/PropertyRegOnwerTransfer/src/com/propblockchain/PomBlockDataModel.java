package com.propblockchain;


import java.io.Serializable;
import java.util.Date;

public class PomBlockDataModel implements Serializable{
String pid,cid,timestamp,metadata;

public String getPid() {
	return pid;
}

public void setPid(String pid) {
	this.pid = pid;
}

public String getCid() {
	return cid;
}

public void setCid(String cid) {
	this.cid = cid;
}

public String getTimestamp() {
	return timestamp;
}

public void setTimestamp() {
	Date d=new Date();
	String timestamp=d.toLocaleString();
	this.timestamp = timestamp;
}

public String getMetadata() {
	return metadata;
}

public void setMetadata(String metadata) {
	this.metadata = metadata;
}
}
