package com.hixtrip.sample.app.convertor;

import com.hixtrip.sample.client.order.dto.CommandOderCreateDTO;
import com.hixtrip.sample.client.order.dto.CommandPayDTO;
import com.hixtrip.sample.client.order.dto.UserDTO;
import com.hixtrip.sample.domain.order.model.Order;
import com.hixtrip.sample.domain.pay.model.CommandPay;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * DTO对像 -> 领域对象转换器
 * 转换器
 */
@Mapper
public interface OrderConvertor {

    OrderConvertor INSTANCE = Mappers.getMapper(OrderConvertor.class);

    @Mapping(target = "createBy", source = "userDTO.userId")
    @Mapping(target = "updateBy", source = "userDTO.userId")
    @Mapping(target = "userId", source = "userDTO.userId")
    Order commandOderCreateDTOToOrder(CommandOderCreateDTO commandOderCreateDTO, UserDTO userDTO);

    CommandPay CommandPayDTOToCommandPay(CommandPayDTO commandPayDTO);
}
