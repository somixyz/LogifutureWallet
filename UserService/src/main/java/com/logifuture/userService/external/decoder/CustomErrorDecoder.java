package com.logifuture.userService.external.decoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logifuture.userService.exception.UserServiceCustomException;
import com.logifuture.userService.external.response.ErrorResponse;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Log4j2
public class CustomErrorDecoder implements ErrorDecoder {


    // deal with exception received from others services as response (for wallet service it will send data in ErrorResponse)
    @Override
    public Exception decode(String s, Response response) {

        ObjectMapper objectMapper =
                new ObjectMapper();

        log.info("::{}", response.request().url());
        log.info("::{}", response.request().headers());

        try {
            ErrorResponse errorResponse =
                    objectMapper.readValue(response.body().asInputStream(), ErrorResponse.class);

            //re-throw our custom exception given from service
            throw new UserServiceCustomException(errorResponse.getErrorMessage(),errorResponse.getErrorCode(),response.status());

        } catch (IOException e) {
            throw new UserServiceCustomException("Internal server error", "INTERNAL_SERVER_ERROR", 500);
        }
    }
}
