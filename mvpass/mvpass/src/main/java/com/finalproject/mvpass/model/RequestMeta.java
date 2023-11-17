package com.finalproject.mvpass.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestMeta {

    private Object nic;
    private Object licenceno;
    private Object email;
    private Object adminname;

}
