package com.hanqian.pojo;
/*
 * 原始未有，CSS为了不报错新加的
 */
public class DbSermainRepair {
	private int seq;
	private int repairMan;
	private int repairTeam;
	private String repairLevel;

	public String getRepairLevel() {
		return repairLevel;
	}

	public void setRepairLevel(String repairLevel) {
		this.repairLevel = repairLevel;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getRepairMan() {
		return repairMan;
	}

	public void setRepairMan(int repairMan) {
		this.repairMan = repairMan;
	}

	public int getRepairTeam() {
		return repairTeam;
	}

	public void setRepairTeam(int repairTeam) {
		this.repairTeam = repairTeam;
	}

}
