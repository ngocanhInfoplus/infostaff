package infostaff.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import infostaff.common.CommonParam;
import infostaff.entity.TblMenuEntity;
import infostaff.exception.ResourceNotFoundException;
import infostaff.mapping.MenuMapping;
import infostaff.model.MenuModel;
import infostaff.repository.TblMenuRepository;
import infostaff.validation.MenuValidation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MenuServiceImpl implements IMenuService {

	@Autowired
	TblMenuRepository menuRepo;

	private final Long parentMenuCode = 0L;

	@Override
	public List<MenuModel> getMenuByRole(User user) {

		if (user == null)
			return null;
		log.info("User name: " + user.getUsername());

		// get main menu
		String roleName = StringUtils.EMPTY;
		Iterator<GrantedAuthority> grantedAuthority = user.getAuthorities().iterator();

		if (grantedAuthority.hasNext())
			roleName = grantedAuthority.next().toString();
		log.info("Role name: " + roleName);

		List<TblMenuEntity> entities = menuRepo.findMenusByRoleNameAndParentId(roleName, parentMenuCode);

		if (entities.isEmpty() || entities == null)
			return null;

		log.info("Total main menu: " + entities.size());
		MenuModel mainMenuModel = new MenuModel();
		MenuModel subMenuModel = new MenuModel();
		List<MenuModel> menus = new ArrayList<MenuModel>();
		List<MenuModel> subMenus;
		MenuMapping menuMapping = new MenuMapping();

		for (TblMenuEntity entity : entities) {
			log.info("Main menu: " + entity.getMenuName());
			mainMenuModel = menuMapping.entityToModel(entity);
			// get sub menu
			List<TblMenuEntity> subEntities = menuRepo.findMenusByRoleNameAndParentId(roleName, entity.getMenuId());

			if (!subEntities.isEmpty()) {

				log.info("Total sub menu: " + subEntities.size());
				subMenus = new ArrayList<MenuModel>();

				for (TblMenuEntity subEntity : subEntities) {

					subMenuModel = menuMapping.entityToModel(subEntity);

					if (subMenuModel != null)
						subMenus.add(subMenuModel);

				}
				mainMenuModel.setSubMenus(subMenus);
			}
			menus.add(mainMenuModel);
		}
		return menus;
	}

	public List<MenuModel> getAllMenu() {

		List<TblMenuEntity> menuEntities = menuRepo.findAllMenu(CommonParam.RC_OPEN);
		
		if(menuEntities == null || menuEntities.isEmpty())
			return null;
		
		MenuModel menuModel;
		List<MenuModel> lstMenuModel = new ArrayList<MenuModel>();
		MenuMapping mapping = new MenuMapping();
		
		for(TblMenuEntity entity: menuEntities) {
			menuModel = mapping.entityToModel(entity);
			
			if(menuModel != null)
				lstMenuModel.add(menuModel);
		}
		
		return lstMenuModel;
	}

	public MenuModel createMenu(User user, MenuModel menuModel) throws ResourceNotFoundException {

		MenuValidation validation = new MenuValidation();
		MenuMapping menuMapping = new MenuMapping();

		if (!validation.validate(menuModel))
			throw new ResourceNotFoundException(CommonParam.ERR_MSG_VAL);

		// parsing data
		TblMenuEntity entity = menuMapping.modelToEntity(menuModel);

		if (entity == null)
			throw new ResourceNotFoundException(CommonParam.ERR_MSG_PARSE);

		entity.setCreatedUser(user.getUsername());
		entity.setCreatedDate(new Date());

		try {
			// insert data to db
			final MenuModel insertedMenu = menuMapping.entityToModel(menuRepo.save(entity));
			return insertedMenu;
		} catch (Exception ex) {
			log.error("Error: " + ex.toString());
			throw new ResourceNotFoundException(CommonParam.ERR_MSG_SYS);
		}
	}

	public MenuModel updateMenu(User user, MenuModel menuModel, Long updateId) throws ResourceNotFoundException {

		MenuValidation validation = new MenuValidation();
		MenuMapping menuMapping = new MenuMapping();

		if (updateId == null || !validation.validate(menuModel))
			throw new ResourceNotFoundException(CommonParam.ERR_MSG_VAL);

		// parsing data
		TblMenuEntity newMenuEntity = menuMapping.modelToEntity(menuModel);

		if (newMenuEntity == null)
			throw new ResourceNotFoundException(CommonParam.ERR_MSG_PARSE);

		try {
			// find old data
			menuRepo.findById(updateId)
					.orElseThrow(() -> new ResourceNotFoundException("Menu not found for this id :: " + updateId));

			// set up date
			newMenuEntity.setMenuId(updateId);
			newMenuEntity.setChangedUser(user.getUsername());
			newMenuEntity.setChangedDate(new Date());

			final MenuModel updatedMenu = menuMapping.entityToModel(menuRepo.save(newMenuEntity));
			return updatedMenu;

		} catch (Exception ex) {
			log.error("Error: " + ex.toString());
			throw new ResourceNotFoundException(CommonParam.ERR_MSG_SYS);
		}
	}

	public MenuModel deleteMenu(User user, Long deleteId) throws ResourceNotFoundException {

		MenuMapping menuMapping = new MenuMapping();

		if (deleteId == null)
			throw new ResourceNotFoundException(CommonParam.ERR_MSG_VAL);

		try {
			// find old data
			TblMenuEntity deteteEntity = menuRepo.findById(deleteId)
					.orElseThrow(() -> new ResourceNotFoundException("Menu not found for this id :: " + deleteId));

			// set up date
			deteteEntity.setChangedUser(user.getUsername());
			deteteEntity.setChangedDate(new Date());
			deteteEntity.setRecordStatus(CommonParam.RC_CLOSE);

			final MenuModel resultModel = menuMapping.entityToModel(menuRepo.save(deteteEntity));
			return resultModel;
		} catch (Exception ex) {
			log.error("Error: " + ex.toString());
			throw new ResourceNotFoundException(CommonParam.ERR_MSG_SYS);
		}
	}
}
