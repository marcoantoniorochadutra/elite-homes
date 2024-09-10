package com.elitehomes.service.base;

import com.elitehomes.core.auth.LoginDto;
import com.elitehomes.core.config.ReturnMessage;
import com.elitehomes.core.constants.CoreReturnMessage;
import com.elitehomes.core.exceptions.BusinessException;
import com.elitehomes.core.exceptions.NotFoundException;
import com.elitehomes.domain.base.LifeCycleFields;
import com.elitehomes.core.entity.base.Versionable;
import com.elitehomes.core.entity.base.MessageDto;
import com.github.dozermapper.core.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Objects;

@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public abstract class AbstractCrudService<D extends LifeCycleFields, M> implements CrudService<M> {

	protected abstract <T extends JpaRepository<D, Long>> T getRepository();
	protected abstract Class<D> getDomainClass();
	protected abstract Class<M> getModelClass();

//	private final ModelMapper modelMapper;

	private final Mapper modelMapper;

	@Autowired
    protected AbstractCrudService(Mapper modelMapper) {
        this.modelMapper = modelMapper;
    }

	@Override
	public M create(M model, LoginDto login) {
		validateModel(model, login);
		D domain = convertToDomain(model);
		populateCreateFields(domain);
		domain = getRepository().saveAndFlush(domain);
		afterSave(domain);
		return convertToModel(domain);
	}

	protected void afterSave(D domain) {

	}

	@Override
	public M update(Long id, M model, LoginDto login) {
		validateModel(model, login);
		D domain = getRepository().getReferenceById(id);

		if (domain instanceof Versionable) {
			verifyVersion((Versionable) domain, (Versionable) model);
		}

		updateMapper(model, domain);

		domain = getRepository().saveAndFlush(domain);

		return convertToModel(domain);
	}

	private void verifyVersion(Versionable domain, Versionable model) {
		Short versionModel =  model.getVersion();
		Short versionDomain = domain.getVersion();

		if(!versionDomain.equals(versionModel)) {
			throw new BusinessException(ReturnMessage.getMessage(CoreReturnMessage.DATA_OUTDATED));
		}
	}

	private void updateMapper(M model, D domain) {
		modelMapper.map(model, domain);
	}


	@Override
	public MessageDto delete(Long id, LoginDto login) {
		try {
			D domain = getRepository().getReferenceById(id);

			getRepository().delete(domain);
		} catch (Exception e) { // Melhorar Erro
			return buildMessage(ReturnMessage.getMessage(CoreReturnMessage.INTERNAL_ERROR));
		}

		return buildMessage(ReturnMessage.getMessage(CoreReturnMessage.DELETED_SUCESS));
	}

	@Override
	public M findById(Long id, LoginDto login) {
		D domain = getRepository().getReferenceById(id);

		if (Objects.isNull(domain) || Objects.isNull(domain.getVersion())) {
			throw new NotFoundException(id.toString(), getDomainClass().getSimpleName());
		}

		return convertToModel(domain);
	}

	private D convertToDomain(M model) {
		return modelMapper.map(model, getDomainClass());
	}

	private M convertToModel(D domain) {
		return modelMapper.map(domain, getModelClass());
	}

	private void populateCreateFields(D domain) {
		domain.setCreatedAt(Instant.now());
	}



	private void validateModel(M model, LoginDto login) {
		if(Objects.isNull(model)) {
			throw new BusinessException(ReturnMessage.getMessageWithField(CoreReturnMessage.NOT_NULL_MESSAGE, getDomainClass().getSimpleName()));
		}
	}
	
	private MessageDto buildMessage(String message) {
		return new MessageDto(message);
	}
}
