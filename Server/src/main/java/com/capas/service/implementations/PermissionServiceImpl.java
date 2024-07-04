package com.capas.service.implementations;

<<<<<<< HEAD
import java.util.List;
=======
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;
>>>>>>> 5e83cf71db9efeda9f933091b4f919be4722c460

import org.springframework.stereotype.Service;

import com.capas.models.entities.House;
import com.capas.models.entities.Permission;
import com.capas.service.PermissionService;
import com.capas.service.UserService;

@Service
public class PermissionServiceImpl implements PermissionService{
<<<<<<< HEAD

	@Override
	public void savePermission() {
		// TODO Auto-generated method stub
=======
	@Autowired
	private PermissionRepository permissionRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private UserService userService;
	
	@Autowired
	private HouseRepository houseRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RolRepository rolRepository;

	@Override
	public void savePermission(SavePermissionDTO info) {
		//User userPetition = userRepository.findById(info.getUser_petition_id()).orElse(null);
		User userPetition = userService.getCurrentUser();
		
		User userPermitted = userRepository.findByDuiOrEmail(info.getUser_permitted_id(),info.getUser_permitted_id());
		User visitor = userRepository.findByDuiOrEmail(info.getVisitor_id(), info.getVisitor_id());

		Rol roleResidente = rolRepository.findById("RESI").orElseThrow(() -> new RuntimeException("Rol not found"));
		Rol roleResidenteEncargado = rolRepository.findById("REEN").orElseThrow(() -> new RuntimeException("Rol not found"));
		Rol roleVisitante = rolRepository.findById("VISI").orElseThrow(() -> new RuntimeException("Rol not found"));
		
		House house = houseRepository.findById(info.getHouse_id()).orElse(null);
		
		if (house == null) {
			throw new RuntimeException("No existe la casa deseada");
		}

		if (userPetition.getRoles().contains(roleResidente) && userPermitted.getRoles().contains(roleResidenteEncargado) && visitor.getRoles().contains(roleVisitante)) {
			//Permission permission = new Permission();

			// Aquí debes establecer los campos del objeto Permission
			// con la información del PermissionDTO.
			
			
			if (info.getUnique_entry() != null) {		//Logica para Permisos de Entrada Unica
				Permission permission = new Permission(
						new Date(), 
						info.getUnique_entry(), 
						stateRepository.findById("PEND").orElse(null), 
						house, 
						userPetition,
						userPermitted,
						visitor);
				
				permissionRepository.save(permission);
				
			}else if (info.getDate_time_start() != null && info.getDate_time_end()!=null) { //Logica para Permisos de Entrada con un rango de fechas
				
				if (info.getDate_time_end().before(info.getDate_time_start())) {
					throw new RuntimeException("Las fechas establecidas son invalidas");
				}
				
				Permission permission = new Permission(
						new Date(), 
						info.getDate_time_start(), 
						info.getDate_time_end(), 
						stateRepository.findById("PEND").orElse(null), 
						house, 
						userPetition, 
						userPermitted, 
						visitor);
				
				permissionRepository.save(permission);
				
			} else if (!info.getDays_week().isEmpty()) {
				//Implementar Logica
				
				Permission permission = new Permission(
						new Date(), 
						info.getDays_week(), 
						stateRepository.findById("PEND").orElse(null), 
						house, 
						userPetition,
						userPermitted,
						visitor);
				
				//permission.setDays_week(info.getDays_week());
				
				permissionRepository.save(permission);
				
			} else {
				throw new RuntimeException("No se puede crear la solicitud de permiso");
			}
			
			
		} else {
			throw new RuntimeException("No se puede crear la solicitud de permiso");
		}
	}

	@Override
	public void editPermission(EditPermissionDTO info) {
		Permission permission = permissionRepository.findById(info.getPermissionId()).orElse(null);

		if (permission == null) throw new RuntimeException("La solicitud de permiso no existe");

		permission.setState(stateRepository.findById(info.getNewStateId()).orElse(null));
		permissionRepository.save(permission);
	}

	@Override
	public void deletePermission(UUID id) {
		Permission permission = permissionRepository.findById(id).orElse(null);

		if (permission != null) {
			permissionRepository.delete(permission);
		} else {
			throw new RuntimeException("Permiso no encontrado");
		}
>>>>>>> 5e83cf71db9efeda9f933091b4f919be4722c460
		
	}

	@Override
	public void editPermission() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePermission() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Permission findPermission(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Permission> findAllPermissions() {
		// TODO Auto-generated method stub
		return null;
	}

}
