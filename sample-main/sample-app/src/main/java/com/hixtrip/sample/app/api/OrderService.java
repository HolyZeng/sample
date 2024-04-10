package com.hixtrip.sample.app.api;

import com.hixtrip.sample.app.result.ResultVO;
import com.hixtrip.sample.client.order.dto.CommandOderCreateDTO;
import com.hixtrip.sample.client.order.dto.CommandPayDTO;
import com.hixtrip.sample.client.order.dto.UserDTO;

/**
 * 订单的service层
 */
public interface OrderService {


    ResultVO order(CommandOderCreateDTO commandOderCreateDTO, UserDTO userDTO);

    ResultVO payCallback(CommandPayDTO commandPayDTO);
}
