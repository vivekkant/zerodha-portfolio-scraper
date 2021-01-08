package org.weekendsoft.zerodhascraper.model;

public class CoinEntry {
	
	private String name;
	private double unit;
	private double nav;
	private double investment;
	private double current;
	private double gain;
	private double gainper;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getUnit() {
		return unit;
	}
	public void setUnit(double unit) {
		this.unit = unit;
	}
	public double getNav() {
		return nav;
	}
	public void setNav(double nav) {
		this.nav = nav;
	}
	public double getInvestment() {
		return investment;
	}
	public void setInvestment(double investment) {
		this.investment = investment;
	}
	public double getCurrent() {
		return current;
	}
	public void setCurrent(double current) {
		this.current = current;
	}
	public double getGain() {
		return gain;
	}
	public void setGain(double gain) {
		this.gain = gain;
	}
	public double getGainper() {
		return gainper;
	}
	public void setGainper(double gainper) {
		this.gainper = gainper;
	}
	
	@Override
	public String toString() {
		return "PortfolioEntry [name=" + name + ", unit=" + unit + ", nav=" + nav + ", investment=" + investment
				+ ", current=" + current + ", gain=" + gain + ", gainper=" + gainper + "]";
	}

	
	
}
