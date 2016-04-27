package com.javdev.core.user.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.javdev.core.controller.BackingBean;
import com.javdev.core.controller.ValidationMessage;
import com.javdev.core.pojo.JavDevUser;
import com.javdev.core.pojo.Role;
import com.javdev.core.realm.model.JavDevToken;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

import lombok.Getter;
import lombok.Setter;

@ManagedBean
@ViewScoped
@URLMappings(mappings = {@URLMapping(id = "userList", pattern = "/portal/usuarios", viewId = "/pages/authenticated/user/userList.jsf"),
	@URLMapping(id = "user", pattern = "/portal/usuario", viewId = "/pages/authenticated/user/userAdd.jsf"),
	@URLMapping(id = "userDetail", pattern = "/portal/detalleUsuario", viewId = "/pages/authenticated/user/userDetail.jsf")})
public class UserBean extends BackingBean {

	// Primitives
	@Getter @Setter private boolean showUserAdd;
	@Getter @Setter private boolean showUserEdit;
	@Getter @Setter private boolean showUserDetail;
	
	@Getter @Setter private boolean documentExist;
	@Getter @Setter private boolean userEmailExist;

	// Java Basic Object
	@Getter @Setter private String labelRoleList;

	// Java Object List
	@Getter @Setter private List<String> roleUserList;

	// User Object List
	@Getter @Setter private List<JavDevUser> userList;


	// User Object
	@Getter @Setter private JavDevUser user;

	private UserController controller;

	public UserBean() throws Exception {
		try {
			this.controller = new UserController();
			this.user = new JavDevUser();
			init();
		} catch (Exception e) {
			throw e;
		}
	}

	/** @author MTorres 22 de abr. de 2016 13:27:53 */
	private void init() throws Exception {
		try {
			loadUserRoleList();
			loadUserList();
		} catch (Exception e) {
			throw e;
		}
	}

	/** @author MTorres 22 de abr. de 2016 13:29:33 */
	private void loadUserRoleList() throws Exception {
		try {
			if (getRoleList() != null && !getRoleList().isEmpty()) {
				this.roleUserList = new ArrayList<String>(getRoleList().size());
				for (Role r : getRoleList()) {
					this.roleUserList.add(r.getName());
				}
			}
		} catch (Exception e) {
			throw e;
		}
	}

	/** @author MTorres 7/01/2016 5:28:56 p. m. */
	private void loadUserList() {
		try {
			this.userList = this.controller.loadUserList();
		} catch (Exception e) {

		}
	}

	/** @author MTorres 22 de abr. de 2016 11:24:55 */
	public void showMessage() throws Exception {
		try {
			addWarnMessage("mostrando", "este mensaje");
		} catch (Exception e) {
			throw e;
		}

	}

	/** @author MTorres 6/01/2016 9:57:14 p. m. */
	public void saveUser() {
		try {
			ValidationMessage vm = this.controller.validSaveUser(this.user);
			if (!vm.isValid()) {
				addWarnMessage(vm.getSummary(), vm.getDetail());
				return;
			}
			if (this.controller.saveUser(user)) {
				addInfoMessage("Guardar Usuario", "El usuario se guardó exitosamente.");

			} else {
				addErrorMessage("Guardar Usuario", "Ocurrió un error al intentar almacenar el usuario, intente nuevamente.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** @author MTorres 12/01/2016 11:35:34 p. m. */
	public void loadFieldExist(int fieldType) {
		try {
			this.documentExist = false;
			this.userEmailExist = false;
			if (this.user != null) {
				switch (fieldType) {
					// Case document number
					case 1:
						if (this.user.getDocumentNumber() != null && !this.user.getDocumentNumber().trim().isEmpty())
							this.documentExist = this.controller.loadFieldExist(JavDevUser.class.getSimpleName(), "documentNumber",
								this.user.getDocumentNumber().trim());
					break;
					// Case email
					case 2:
						if (this.user.getEmail() != null && !this.user.getEmail().trim().isEmpty())
							this.userEmailExist =
								this.controller.loadFieldExist(JavDevToken.class.getSimpleName(), "email", this.user.getEmail().trim().toLowerCase());
					break;
					default:
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** @author MTorres 11/01/2016 10:15:16 p. m. */
	public void userDelete() {
		try {
			if (this.user != null) {
				if (this.controller.userDelete(this.user, getCurrentUser())) {
					this.userList.remove(user);
					user = null;
					user = new JavDevUser();
					addInfoMessage("Eliminar Usuario", "El usuario fue eliminado correctamente");
					execute("PF('dlgDeleteUserWV').hide()");
				}
			} else {
				addErrorMessage("Eliminar usuario", "Ocurrió un error, no fue posible eliminar el usuario; intente nuevamente");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** @author MTorres 22 de abr. de 2016 13:12:20 */
	public void handleRoleChange() {
		try {
			this.labelRoleList = new String();
			if (this.user != null && this.user.getIdRoleList() != null && !this.user.getIdRoleList().isEmpty()) {
				for (Role r : getRoleList()) {
					if (this.user.getIdRoleList().contains(r.getName()))
						this.labelRoleList += r.getName() + ", ";
				}
				this.labelRoleList = this.labelRoleList.substring(0, this.labelRoleList.lastIndexOf(","));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}