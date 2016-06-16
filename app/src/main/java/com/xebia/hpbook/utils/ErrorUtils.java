package com.xebia.hpbook.utils;

import com.xebia.hpbook.rest.APIError;
import com.xebia.hpbook.rest.ApiClient;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

/**
 * ErrorUtils class
 */
public class ErrorUtils {
    /**
     * parseError(response) method
     * @param response
     * @return APIError
     */
    public static APIError parseError(Response<?> response) {
        Converter<ResponseBody, APIError> converter =
                ApiClient.getClient()
                        .responseBodyConverter(APIError.class, new Annotation[0]);

        APIError error;

        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new APIError();
        }

        return error;
    }
}
