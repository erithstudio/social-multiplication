package microservices.template.multiplication.mapper;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import microservices.template.multiplication.domain.DtoAccountBalanceRequest;
import microservices.template.multiplication.domain.DtoAccountBalanceResponse;
import microservices.template.multiplication.domain.DtoAccountRequest;
import microservices.template.multiplication.enumeration.DtoResponseStatusCodeEnum;

public class AccountMapper extends ABaseMapper {
    @Override
    public void configure(MapperFactory oricaMapperFactory) {
        oricaMapperFactory.classMap(DtoAccountRequest.class, DtoAccountBalanceRequest.class)
                .byDefault()
                .field("accountId", "accountRequest.accountId")
                .field("content", "accountRequest.content")
                .mapNulls(false)
                .register();

        oricaMapperFactory.classMap(DtoAccountBalanceRequest.class, DtoAccountBalanceResponse.class)
                .byDefault()
                .mapNulls(false)
                .customize(new CustomMapper<DtoAccountBalanceRequest, DtoAccountBalanceResponse>() {
                    @Override
                    public void mapAtoB(DtoAccountBalanceRequest dtoAccountBalanceRequest, DtoAccountBalanceResponse dtoAccountBalanceResponse, MappingContext context) {
                        dtoAccountBalanceResponse.setStatusCode(DtoResponseStatusCodeEnum.SUCCESSFUL);
                    }
                })
                .register();
    }
}
