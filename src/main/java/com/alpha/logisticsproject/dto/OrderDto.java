package com.alpha.logisticsproject.dto;

public class OrderDto {
	private String orderdate;
	private int cargoid;
	private String cargoName;
	private String cargoDescription;
	private int cargowt;
	private int cargocount;
	private int loadingid;
	private int unloadingid;
	
	// Fields supporting your dynamic single-page workflow requirements
	private String username;
	private int distanceKm;

	// --- Getters and Setters ---
	public String getOrderdate() { 
		return orderdate; 
	}
	
	public void setOrderdate(String orderdate) { 
		this.orderdate = orderdate; 
	}
	
	public int getCargoid() { 
		return cargoid; 
	}
	
	public void setCargoid(int cargoid) { 
		this.cargoid = cargoid; 
	}
	
	public String getCargoName() { 
		return cargoName; 
	}
	
	public void setCargoName(String cargoName) { 
		this.cargoName = cargoName; 
	}
	
	public String getCargoDescription() { 
		return cargoDescription; 
	}
	
	public void setCargoDescription(String cargoDescription) { 
		this.cargoDescription = cargoDescription; 
	}
	
	public int getCargowt() { 
		return cargowt; 
	}
	
	public void setCargowt(int cargowt) { 
		this.cargowt = cargowt; 
	}
	
	public int getCargocount() { 
		return cargocount; 
	}
	
	public void setCargocount(int cargocount) { 
		this.cargocount = cargocount; 
	}
	
	public int getLoadingid() { 
		return loadingid; 
	}
	
	public void setLoadingid(int loadingid) { 
		this.loadingid = loadingid; 
	}
	
	public int getUnloadingid() { 
		return unloadingid; 
	}
	
	public void setUnloadingid(int unloadingid) { 
		this.unloadingid = unloadingid; 
	}
	
	public String getUsername() { 
		return username; 
	}
	
	public void setUsername(String username) { 
		this.username = username; 
	}
	
	public int getDistanceKm() { 
		return distanceKm; 
	}
	
	public void setDistanceKm(int distanceKm) { 
		this.distanceKm = distanceKm; 
	}

	public OrderDto() { 
		super(); 
	}
}