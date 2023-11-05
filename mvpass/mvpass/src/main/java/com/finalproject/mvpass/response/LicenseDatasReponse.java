package com.finalproject.mvpass.response;

import com.finalproject.mvpass.entity.LicenseData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LicenseDatasReponse {
    private List<LicenseData> licenseDataList;
    private String message;
}
