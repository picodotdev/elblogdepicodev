package es.com.blogspot.elblogdepicodev.tapestry.security.pages;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.web.subject.support.WebDelegatingSubject;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Symbol;

@RequiresUser
public class Index {

	@Inject
	@Symbol(SymbolConstants.TAPESTRY_VERSION)
	@Property
	private String tapestryVersion;
	
	@Persist
	@Property
	private Long cuenta;
	
	void setupRender() {
		if (cuenta == null) {
			cuenta = 0l;			
		}
	}
	
	public WebDelegatingSubject getSubject() {
		return (WebDelegatingSubject) SecurityUtils.getSubject();
	}
	
	@RequiresUser
	public void onActionFromAdd1() {
		cuenta += 1;
	}
	
	public void onActionFromSubstract1() {
		// Esta operación es insegura porque aunque se oculta en la página,
		// el ejecutar la operación no tiene ninguna restricción de permisos
		// como en el caso de add2 o add3. Con el enlace adecuado cualquier usuario
		// puede ejecutarla aunque no vea el enlace (http://localhost:8080/TapestrySecurity/index.substract1).
		cuenta -= 1;
	}
	
	@RequiresPermissions("cuenta:add2")
	public void onActionFromAdd2() {
		cuenta += 2;
	}
	
	@RequiresRoles("root")
	public void onActionFromAdd3() {
		cuenta += 3;
	}
	
	public void onActionFromReset() {
		SecurityUtils.getSubject().checkPermission("cuenta:reset");
		cuenta = 0l;
	}
	
	@RequiresUser
	public void onActionFromCloseSesion() {
		SecurityUtils.getSubject().logout();
	}
}