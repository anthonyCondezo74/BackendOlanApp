package com.olan.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.olan.model.Cliente;
import com.olan.util.Constantes;

public class RazonSocialValidator implements ConstraintValidator<RazonSocial, Cliente> {

	@Override
	public void initialize(RazonSocial constraintAnnotation) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isValid(Cliente value, ConstraintValidatorContext context) {
		if (value.getTipoDocumento().getId() == Constantes.ID_RUC
				&& (value.getRazonSocial() == null || value.getRazonSocial().trim().length() <= 0)) {
			return false;
		}else if ((value.getNombre() == null || value.getNombre().trim().length() <= 0)
						|| (value.getApellidoPaterno() == null || value.getApellidoPaterno().trim().length() <= 0)
						|| (value.getApellidoMaterno() == null || value.getApellidoMaterno().trim().length() <= 0)) {
			return false;
		}
		return true;
	}

}
