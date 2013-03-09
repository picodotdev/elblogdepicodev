package es.com.blogspot.elblogdepicodev.tapestry.skin.services;

public enum DominioAxis {
	
	COM_ES, NET;

	public static DominioAxis getByServerName(String serverName) {
		if (serverName == null) {
			return null;
		}
		
		for (DominioAxis dominio : values()) {
			String d = dominio.name().toLowerCase().replaceAll("_", ".");
			if (serverName.endsWith(d)) {
				return dominio;
			}
		}
		return null;
	}
}