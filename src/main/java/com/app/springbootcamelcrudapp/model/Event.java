package com.app.springbootcamelcrudapp.model;


import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "events")
public class Event {

	@Id
	@GeneratedValue
	private UUID eventId;

	@Column(name = "transId", nullable = false)
	private String transId;

	@Column(name = "transTms", nullable = true)
	private String transTms;

	@Column(name = "rcNum", nullable = true)
	private String rcNum;
	
	@Column(name = "clientId", nullable = true)
	private String clientId;

	@Column(name = "eventCnt", nullable = true)
	private int eventCnt;

	@Column(name = "locationCd", nullable = true)
	private String locationCd;
	
	@Column(name = "locationId1", nullable = true)
	private String locationId1;

	@Column(name = "locationId2", nullable = true)
	private String locationId2;
	
	@Column(name = "addrNbr", nullable = true)
	private String addrNbr;

	public Event() {
	}

	public Event(String transId, String transTms, String rcNum) {

		this.transId = transId;
		this.transTms = transTms;
		this.rcNum = rcNum;
	}

	public UUID getEventId() {
		return eventId;
	}

	public void setEventId(UUID eventId) {
		this.eventId = eventId;
	}

	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}

	public String getTransTms() {
		return transTms;
	}

	public void setTransTms(String transTms) {
		this.transTms = transTms;
	}

	public String getRcNum() {
		return rcNum;
	}

	public void setRcNum(String rcNum) {
		this.rcNum = rcNum;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public int getEventCnt() {
		return eventCnt;
	}

	public void setEventCnt(int eventCnt) {
		this.eventCnt = eventCnt;
	}

	public String getLocationCd() {
		return locationCd;
	}

	public void setLocationCd(String locationCd) {
		this.locationCd = locationCd;
	}

	public String getLocationId1() {
		return locationId1;
	}

	public void setLocationId1(String locationId1) {
		this.locationId1 = locationId1;
	}

	public String getLocationId2() {
		return locationId2;
	}

	public void setLocationId2(String locationId2) {
		this.locationId2 = locationId2;
	}

	public String getAddrNbr() {
		return addrNbr;
	}

	public void setAddrNbr(String addrNbr) {
		this.addrNbr = addrNbr;
	}

	@Override
	public String toString() {
		return "Event [eventId=" + eventId + ", transId=" + transId + ", transTms=" + transTms + ", rcNum=" + rcNum
				+ "]";
	}

}